/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiguang.common.utils.StringUtils;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdPurchaseDetailDao;
import com.thinkgem.hys.pd.dao.PdPurchaseOrderDao;
import com.thinkgem.hys.pd.entity.PdPurchaseDetail;
import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 申购单Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdPurchaseOrderService extends CrudService<PdPurchaseOrderDao, PdPurchaseOrder> {
	
	@Autowired
	private PdPurchaseDetailDao pdPurchaseDetialDao;

	public PdPurchaseOrder get(String id) {
		return super.get(id);
	}
	
	public List<PdPurchaseOrder> findList(PdPurchaseOrder pdPurchaseOrder) {
		return super.findList(pdPurchaseOrder);
	}
	
	public Page<PdPurchaseOrder> findPage(Page<PdPurchaseOrder> page, PdPurchaseOrder pdPurchaseOrder) {
		return super.findPage(page, pdPurchaseOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPurchaseOrder pdPurchaseOrder) {
		super.save(pdPurchaseOrder);
	}
	/**
	 * 保存申购单
	 * @param pdPurchaseOrder
	 */
	@Transactional(readOnly = false)
	public void savePurchaseOrder(PdPurchaseOrder pdPurchaseOrder) {
		//保存明细
		List<PdPurchaseDetail> detailList = pdPurchaseOrder.getPurchaseDetailList();
		if(detailList != null && !detailList.isEmpty()){
			List<PdPurchaseDetail> newArray = new ArrayList<PdPurchaseDetail>();
			int total_num = 0;
			BigDecimal total_money = new BigDecimal(0);
			for(PdPurchaseDetail pd : detailList){
				if(StringUtils.isEmpty(pd.getProdId()))
					continue;
				total_num += Integer.valueOf(pd.getApplyCount());
				//total_money += Double.valueOf(pd.getApplyCount()) * Double.valueOf(pd.getProdPrice());
				String ssprice = "0.00";
				if (!StringUtils.isEmpty(pd.getProdPrice())) {
					ssprice = pd.getProdPrice();
				}
				total_money = total_money.add(new BigDecimal(ssprice).multiply(BigDecimal.valueOf(pd.getApplyCount())));
				pd.setOrderNo(pdPurchaseOrder.getOrderNo());
				//pd.setAmountMoney(Double.valueOf(pd.getProdPrice()) * pd.getApplyCount());
				pd.preInsert();
				newArray.add(pd);
			}
			if(!newArray.isEmpty())
				pdPurchaseDetialDao.batchInsert(newArray);
			pdPurchaseOrder.setOrderDate(new Date());
			pdPurchaseOrder.setOrderStatus(MinaGlobalConstants.PURCHASE_ORDER_STATUS_SENDED);//初始化状态：已申请
			pdPurchaseOrder.setPurchaseBy(UserUtils.getUser().getId());
			pdPurchaseOrder.setAmountCount(total_num);
			pdPurchaseOrder.setAmountMoney(total_money.doubleValue());
			super.save(pdPurchaseOrder);
		}else{
			throw new NullPointerException("申购单产品不能为空");
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPurchaseOrder pdPurchaseOrder) {
		super.delete(pdPurchaseOrder);
	}
	
	/**
	 * 审核采购单
	 */
	@Transactional(readOnly = false)
	public int audit(String orderNos, String orderStatus, String refuseReason) {
		if(StringUtils.isEmpty(orderNos) || StringUtils.isEmpty(orderStatus)){
			return 0;
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orderStatus",orderStatus);
		map.put("orderNos",PdPurchaseOrder.dealStrData(orderNos));
		map.put("refuseReason",refuseReason);
		map.put("auditBy", UserUtils.getUser());
		map.put("auditDate", new Date());
		return dao.batchUpdateOrderStatus(map);
	}
	
}