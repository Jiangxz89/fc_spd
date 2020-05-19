/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdPurchaseDetailDao;
import com.thinkgem.hys.pd.dao.PdPurchaseOrderDao;
import com.thinkgem.hys.pd.dao.PdPurchaseOrderMergeDao;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.entity.vo.TempEntity;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 合并申购单Service
 * @author wg
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class PdPurchaseOrderMergeService extends CrudService<PdPurchaseOrderMergeDao, PdPurchaseOrderMerge> {

	private static String BASE_URL = "";
	static {
		BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
	}

	@Autowired
	private PdPurchaseOrderDao pdPurchaseOrderDao;
	@Autowired
	private PdPurchaseDetailDao pdPurchaseDetailDao;
	

	public PdPurchaseOrderMerge get(String id) {
		return super.get(id);
	}
	
	public List<PdPurchaseOrderMerge> findList(PdPurchaseOrderMerge pdPurchaseOrderMerge) {
		return super.findList(pdPurchaseOrderMerge);
	}
	
	public Page<PdPurchaseOrderMerge> findPage(Page<PdPurchaseOrderMerge> page, PdPurchaseOrderMerge pdPurchaseOrderMerge) {
		return super.findPage(page, pdPurchaseOrderMerge);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPurchaseOrderMerge pdPurchaseOrderMerge) {
		super.save(pdPurchaseOrderMerge);
	}
	
	@Transactional(readOnly = false)
	public Map<String,Object> saveMergeOrder(String orderNos) {
		Map<String,Object> retMap = new HashMap<String,Object>();

		PdPurchaseOrderMerge pdPurchaseOrderMerge = new PdPurchaseOrderMerge();
		pdPurchaseOrderMerge.setOrderNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_SG));
		pdPurchaseOrderMerge.setPurchaseDate(new Date());
		pdPurchaseOrderMerge.setPurchaseBy(UserUtils.getUser().getId());
		pdPurchaseOrderMerge.setDeptId(UserUtils.getUser().getStoreroomId());
		pdPurchaseOrderMerge.setDeptName(UserUtils.getUser().getStoreroomName());
		pdPurchaseOrderMerge.setOrderStatus(MinaGlobalConstants.PURCHASE_ORDER_STATUS_PASSED);
		//更新pd_purchase_order表中merge_order_no
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("mergeOrderNo", pdPurchaseOrderMerge.getOrderNo());
		map.put("updateBy", UserUtils.getUser());
		map.put("updateDate", new Date());
		map.put("orderNos", PdPurchaseOrder.dealStrData(orderNos));
		if(StringUtils.isEmpty(orderNos))
			throw new NullArgumentException("申购单号为空！！！");
		pdPurchaseOrderDao.batchUpdateMergeOrderNo(map);
		final String ifPlatform = Global.getConfig("IF_PLATFORM");//是否有中心平台标识1=没有，0=有
		if("0".equals(ifPlatform)){
			//同步到中心平台2018年9月25日17:25:50  //TODO
			PdPurchaseOrder order = new PdPurchaseOrder();
			order.setMergeOrderNo(pdPurchaseOrderMerge.getOrderNo());
			List<PdConsumablesOrder> pdConsumablesOrders = getConsumeOrder(order);
			//有数据再同步
			try{
				if(pdConsumablesOrders!=null && pdConsumablesOrders.size()>0){
					String jsonPar = JSONArray.toJSONString(pdConsumablesOrders);
					JSONObject json = HttpUtil.httpPost(BASE_URL + Global.getConfig("CONSUMABLES_ORDER_URL"), jsonPar);
					if(json.containsKey("statusCode")){
						retMap.put("statusCode",json.get("statusCode"));
						if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
							//成功
							pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_1);//已上传
						}else{
							//失败
							retMap.put("message",json.get("msg"));
							pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
						}
					}else{
						//失败
						retMap.put("message",json.get("msg"));
						pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
					}
				}else{
					//失败
					retMap.put("message","订单为空！");
					pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
				}
			}catch(Exception e){
				e.printStackTrace();
				retMap.put("message",e.getMessage());
				pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
			}
		}
		super.save(pdPurchaseOrderMerge);
		return retMap;
	}
	
	/**
	 *  此数据通过定时任务表同步到中心平台
	 */
	@Transactional(readOnly = false)
	public List<PdConsumablesOrder> getConsumeOrder(PdPurchaseOrder order) {
		//if (null == order || order.getAuditDate()==null)
		if (null == order)
			return null;
		order.setOrderStatus(MinaGlobalConstants.PURCHASE_ORDER_STATUS_PASSED);
		List<PdPurchaseOrder> poList = pdPurchaseOrderDao.findList(order);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		HashSet<String> oNos = new HashSet<String>();
		for (PdPurchaseOrder pdPurchaseOrder : poList) {
			Map<String, String> tempMap = new HashMap<String, String>();
			if (oNos.contains(pdPurchaseOrder.getMergeOrderNo())) {
				continue;
			}
			String tempOns = "";
			for (PdPurchaseOrder pdOrder : poList) {
				if (pdPurchaseOrder.getMergeOrderNo().equals(pdOrder.getMergeOrderNo())) {
					tempOns += "," + pdOrder.getOrderNo();//2018年9月26日14:20:44  
					oNos.add(pdPurchaseOrder.getMergeOrderNo());
				}
			}
			tempMap.put("orderNo", pdPurchaseOrder.getMergeOrderNo());
			tempMap.put("purchaseNo", tempOns);
			tempMap.put("orderDate", DateUtils.formatDateTime(pdPurchaseOrder.getAuditDate()));
			mapList.add(tempMap);
		}
		
		List<PdConsumablesOrder> pdConsumablesOrderList = new ArrayList<PdConsumablesOrder>();
		Iterator<Map<String, String>> ite = mapList.iterator();
		while (ite.hasNext()) {
			Map<String, String> wgMap = ite.next();
			//订单信息
			PdConsumablesOrder pdConsumablesOrder = new PdConsumablesOrder();
			pdConsumablesOrder.setNumber(wgMap.get("orderNo"));//订单号
			pdConsumablesOrder.setHospital(Global.getConfig("HOSPITAL_NUMBER"));
			List<String> qOrderNos = Arrays.asList(wgMap.get("purchaseNo").split(","));
			PdPurchaseOrder po = pdPurchaseOrderDao.getCountAndMoney(qOrderNos);
			pdConsumablesOrder.setOrderQuantity(po.getAmountCount());//订单总数量
			pdConsumablesOrder.setOrderAmount(po.getAmountMoney());//订单总金额
			pdConsumablesOrder.setOrderDate(wgMap.get("orderDate"));
			
			//查询订单下产品信息
			PdPurchaseDetail ppd = new PdPurchaseDetail();
			ppd.setOrderNos(qOrderNos);
			List<PdPurchaseDetail> ppdList = pdPurchaseDetailDao.findList(ppd);
			
			//处理订单下产品
			List<PdPurchaseDetail> finalProdList = dealRepeatProdInfo(ppdList);
			
			List<PdConsumablesOrderDetail> pdConsumablesOrderDetails = new ArrayList<PdConsumablesOrderDetail>();
			for (PdPurchaseDetail pdPurchaseDetail : finalProdList) {
				
				PdConsumablesOrderDetail pdConsumablesOrderDetail = new PdConsumablesOrderDetail();
				String sprice = pdPurchaseDetail.getProdPrice();
				if (!StringUtils.isEmpty(sprice)) {
					pdConsumablesOrderDetail.setPrice(Double.valueOf(sprice));
					BigDecimal bg = new BigDecimal(sprice);
					pdConsumablesOrderDetail.setAmount(bg.multiply(new BigDecimal(pdPurchaseDetail.getApplyCount())).doubleValue());
				}
				pdConsumablesOrderDetail.setProductId(pdPurchaseDetail.getProdId());
				pdConsumablesOrderDetail.setNumber(pdPurchaseDetail.getProdNo());
				pdConsumablesOrderDetail.setOrderQuantity(pdPurchaseDetail.getApplyCount());
				pdConsumablesOrderDetail.setSupplierName(pdPurchaseDetail.getSupplierName());
				pdConsumablesOrderDetail.setName(pdPurchaseDetail.getProdName());
				pdConsumablesOrderDetails.add(pdConsumablesOrderDetail);
			}
			pdConsumablesOrder.setPdConsumablesOrderDetails(pdConsumablesOrderDetails);
			pdConsumablesOrderList.add(pdConsumablesOrder);
		}
		
		return pdConsumablesOrderList;
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPurchaseOrderMerge pdPurchaseOrderMerge) {
		super.delete(pdPurchaseOrderMerge);
	}
	
	/**
	 * 采购单入库状态更新
	 * @param mergeOrderNo
	 * @param status
	 */
	@Transactional(readOnly = false)
	public void updateMergeOrderStatus(String mergeOrderNo , String status){
		dao.updateMergeOrderStatus(mergeOrderNo, status);
		pdPurchaseOrderDao.updateStatusByOrderNo(mergeOrderNo, status);
	}
	
	//重复产品处理
	public List<PdPurchaseDetail> dealRepeatProdInfo(List<PdPurchaseDetail> ppdList ) {
		List<PdPurchaseDetail> newArray = new ArrayList<PdPurchaseDetail>();
		HashSet<String> tempSet = new HashSet<String>();
		for(PdPurchaseDetail detail : ppdList){
			int total_count = 0;
			
			if(tempSet.contains(detail.getProdNo())){
				continue;
			}
			
			for(PdPurchaseDetail det : ppdList){
				if (detail.getProdNo().equals(det.getProdNo())) {
					tempSet.add(det.getProdNo());
					total_count += det.getApplyCount();
				}
			}
			
			detail.setApplyCount(total_count);
			newArray.add(detail);
		}
		return newArray;
	}
	
	/**
	 * 上传到中心平台
	 * @param pdPurchaseOrder
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String,Object> uploadPlatform(PdPurchaseOrder pdPurchaseOrder) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		PdPurchaseOrderMerge pdPurchaseOrderMerge = new PdPurchaseOrderMerge();
		pdPurchaseOrderMerge.setOrderNo(pdPurchaseOrder.getMergeOrderNo());
		List<PdConsumablesOrder> pdConsumablesOrders = getConsumeOrder(pdPurchaseOrder);
		//有数据再同步
		try{
			if(pdConsumablesOrders!=null && pdConsumablesOrders.size()>0){
				String jsonPar = JSONArray.toJSONString(pdConsumablesOrders);
				JSONObject json = HttpUtil.httpPost(BASE_URL + Global.getConfig("CONSUMABLES_ORDER_URL"), jsonPar);
				if(json.containsKey("statusCode")){
					retMap.put("statusCode",json.get("statusCode"));
					if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
						//成功
						pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_1);//已上传
					}else{
						//失败
						retMap.put("message",json.get("msg"));
						pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
					}
				}else{
					//失败
					retMap.put("message",json.get("msg"));
					pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
				}
			}else{
				//失败
				retMap.put("message","订单为空！");
				pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
			}
		}catch(Exception e){
			e.printStackTrace();
			retMap.put("message",e.getMessage());
			pdPurchaseOrderMerge.setSupplierStatus(MinaGlobalConstants.PURCHASE_ORDER_SUPPLIER_STATUS_0);//待上传
		}
		//更新采购单状态
		dao.updateMergeSupplierStatus(pdPurchaseOrderMerge);

		retMap.put("supplierStatus",pdPurchaseOrderMerge.getSupplierStatus());

		return retMap;
	}
	
	//更新采购单状态
	@Transactional(readOnly = false)
	public void batchUpdate(List<TempEntity> array) {
		dao.batchUpdate(array);
	}

    /**
     * 耗材入库查询采购单
     * @param page
     * @param pdPurchaseOrderMerge
     * @return
     */
    public Page<PdPurchaseOrderMerge> findChooseOrderList(Page<PdPurchaseOrderMerge> page, PdPurchaseOrderMerge pdPurchaseOrderMerge) {
        pdPurchaseOrderMerge.setPage(page);
        page.setList(dao.findChooseOrderList(pdPurchaseOrderMerge));
        return page;
    }
}