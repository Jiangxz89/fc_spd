/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.*;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.SpdUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 出入库记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStockRecordService extends CrudService<PdStockRecordDao, PdStockRecord> {
	private Logger logger = LoggerFactory.getLogger(PdStockRecordService.class);
	@Autowired
	private PdStockRecordProductDao pdStockRecordProductDao;
	@Autowired
	private PdProductStockDao pdProductStockDao;
	@Autowired
	private PdStockRecordInvoiceDao pdStockRecordInvoiceDao;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdStockRecordDao pdStockRecordDao;
	@Autowired
	private PdApplyOrderDao pdApplyOrderDao;
	@Autowired
	private PdAllocationRecordDao pdAllocationRecordDao;
	@Autowired
	private PdProductStockTotalDao pdProductStockTotalDao;
	@Autowired
	private PdStockLogDao pdStockLogDao;
	@Autowired
	private PdStoreroomDao pdStoreroomDao;
	@Autowired
	private PdDosagertDao pdDosagertDao;
	@Autowired
	private PdPurchaseOrderMergeService pdPurchaseOrderMergeService;
	@Autowired
	private PdProductService pdProductService;
	
	public PdStockRecord get(String id) {
		return super.get(id);
	}
	
	public List<PdStockRecord> findList(PdStockRecord pdStockRecord) {
		return super.findList(pdStockRecord);
	}
	
	public Page<PdStockRecord> findPage(Page<PdStockRecord> page, PdStockRecord pdStockRecord) {
		return super.findPage(page, pdStockRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStockRecord pdStockRecord) {
		super.save(pdStockRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStockRecord pdStockRecord) {
		super.delete(pdStockRecord);
	}
	
	/**
	 * 保存入库记录
	 * @param pdStockRecord
	 * @param storeroomType
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveRecordIn(PdStockRecord pdStockRecord, String storeroomType) {
		
		/*String id = pdStockRecord.getId();//2019年9月18日11:22:02 zxh 优化保存入库记录
		if(StringUtils.isNotBlank(id)){
			//暂不支持修改
			//pdStockRecord.preUpdate();
			//pdStockRecordDao.update(pdStockRecord);
		}else{
			pdStockRecord.preInsert();
			pdStockRecordDao.insert(pdStockRecord);
		}*/
		//产品关联表  pdStockRecordProductDao
		if(pdStockRecord.getProductList() != null && pdStockRecord.getProductList().size()>0){
			/*PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
			pdStockRecordProduct.setRecordId(pdStockRecord.getId());*/
			List<PdStockRecordProduct> list = pdStockRecord.getProductList();
			//校验数据的合法性
			Iterator<PdStockRecordProduct> it = list.iterator();
			while(it.hasNext()){
				PdStockRecordProduct child = it.next();
				if(child.getProductNum()==null || child.getBatchNo()==null){
					it.remove();
				}
			}
			if(list!=null && list.size()>0){
				pdStockRecord.preInsert();
				pdStockRecordDao.insert(pdStockRecord);
				for (PdStockRecordProduct pdStockRecordProduct2 : list) {
					pdStockRecordProduct2.setRecordId(pdStockRecord.getId());
					pdStockRecordProduct2.preInsert();
				}
				pdStockRecordProductDao.saveGroupByRecordId(list);
			}else{
				//数据异常
				throw new RuntimeException("数据异常");
			}


		}
		
		String inType = pdStockRecord.getInType();  //入库类型
		if(MinaGlobalConstants.STOCK_IN_TYPE_0.equals(inType)){  //正常入库
			if(MinaGlobalConstants.STOREROOM_TYPE_0.equals(storeroomType)){  //一级库房
				//String orderNo = pdStockRecord.getOrderNo(); //采购单号
				//String status = MinaGlobalConstants.PURCHASE_ORDER_STATUS_STOCKED;
				//pdPurchaseOrderMergeService.updateMergeOrderStatus(orderNo, status);
			}else{
				String applyNo = pdStockRecord.getApplyNo(); //申领单号
				PdApplyOrder applyOrder = new PdApplyOrder();
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_IN_STOCK);	
				applyOrder.setApplyNo(applyNo);
				int i = pdApplyOrderDao.updateApplyState(applyOrder);
			}
		}else if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){  //退货入库
			String dosagertNo = pdStockRecord.getDosagertNo();
			if(StringUtils.isNotEmpty(dosagertNo)){
				PdStockRecord stockRecord = new PdStockRecord();
				stockRecord.setRecordNo(dosagertNo);
				stockRecord.setReturnState(MinaGlobalConstants.RETURN_STATE_1);
				stockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_1);
				stockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
				pdStockRecordDao.updateReturnState(stockRecord);
			}			
			
		}else if(MinaGlobalConstants.STOCK_IN_TYPE_2.equals(inType)){  //调拨入库
			String allocationNo = pdStockRecord.getAllocationNo();
			if(StringUtils.isNotEmpty(allocationNo)){
				PdAllocationRecord allocationRecord = new PdAllocationRecord();
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_IN_STOCK);
				allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
				pdAllocationRecordDao.updateAllocationState(allocationRecord);
			}
		}
				
		//保存发票记录 pdStockRecordInvoiceDao
		if(StringUtils.isNotBlank(pdStockRecord.getOrderNo())){
			if(pdStockRecord.getInvoiceList() != null && pdStockRecord.getInvoiceList().size()>0){
				PdStockRecordInvoice pdStockRecordInvoice = new PdStockRecordInvoice();
				pdStockRecordInvoice.setRecordId(pdStockRecord.getId());
				pdStockRecordInvoiceDao.deleteByRecordId(pdStockRecordInvoice);
				List<PdStockRecordInvoice> list2 = pdStockRecord.getInvoiceList();
				for (PdStockRecordInvoice pdStockRecordInvoice2 : list2) {
					//入库发票信息优化  2018年6月28日17:09:55
					if("".equals(pdStockRecordInvoice2.getInvoiceAmount())){
						BigDecimal dosagertMoney = new BigDecimal(0);
						pdStockRecordInvoice2.setInvoiceAmount(String.valueOf(dosagertMoney.doubleValue()));
					}
					pdStockRecordInvoice2.setRecordId(pdStockRecord.getId());
					pdStockRecordInvoice2.preInsert();
				}
				pdStockRecordInvoiceDao.saveGroupByRecordId(list2);
			}
		}
		
		return "";
	}
	
	//保存出入库记录
	@Transactional(readOnly = false)
	public void saveRecord(PdStockRecord pdStockRecord) {
		pdStockRecord.preInsert();
		pdStockRecordDao.insert(pdStockRecord);
		//产品关联表  pdStockRecordProductDao
		if(pdStockRecord.getProductList() != null && pdStockRecord.getProductList().size()>0){
			PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
			pdStockRecordProduct.setRecordId(pdStockRecord.getId());			
			List<PdStockRecordProduct> list = pdStockRecord.getProductList();
			for (PdStockRecordProduct pdStockRecordProduct2 : list) {
				pdStockRecordProduct2.setRecordId(pdStockRecord.getId());
				pdStockRecordProduct2.preInsert();
			}
			pdStockRecordProductDao.saveGroupByRecordId(list);
		}
		//更新导入单状态
		if(StringUtils.isNotEmpty(pdStockRecord.getAllocationNo())){
			PdAllocationRecord allocationRecord = new PdAllocationRecord();
			String recodeType = pdStockRecord.getRecodeType();
			if(recodeType.equals(MinaGlobalConstants.STOCK_RECORD_TYPE_IN)){
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_IN_STOCK);
			}else if(recodeType.equals(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT)){
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_OUT_STOCK);
			}			
			allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
			pdAllocationRecordDao.updateAllocationState(allocationRecord);
		}
		if(StringUtils.isNotEmpty(pdStockRecord.getApplyNo())){
			PdApplyOrder applyOrder = new PdApplyOrder();
			String recodeType = pdStockRecord.getRecodeType();
			if(recodeType.equals(MinaGlobalConstants.STOCK_RECORD_TYPE_IN)){
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_IN_STOCK);
			}else if(recodeType.equals(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT)){
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IMPORT_OUT_STOCK);
			}			
			applyOrder.setApplyNo(pdStockRecord.getApplyNo());
			int i=pdApplyOrderDao.updateApplyState(applyOrder);
		}
		/*if(StringUtils.isNotEmpty(pdStockRecord.getDosagertNo())){
			PdDosagert dosagert = new PdDosagert();
			dosagert.setDosagertNo(pdStockRecord.getDosagertNo());
			dosagert.setDosagertState(MinaGlobalConstants.DOSAGERT_STATE_1);
			pdDosagertDao.updateDosagertState(dosagert);
		}*/
		
		//保存发票记录 pdStockRecordInvoiceDao
		if(StringUtils.isNotBlank(pdStockRecord.getOrderNo())){
			if(pdStockRecord.getInvoiceList() != null && pdStockRecord.getInvoiceList().size()>0){
				PdStockRecordInvoice pdStockRecordInvoice = new PdStockRecordInvoice();
				pdStockRecordInvoice.setRecordId(pdStockRecord.getId());
				pdStockRecordInvoiceDao.deleteByRecordId(pdStockRecordInvoice);
				List<PdStockRecordInvoice> list2 = pdStockRecord.getInvoiceList();
				for (PdStockRecordInvoice pdStockRecordInvoice2 : list2) {
					pdStockRecordInvoice2.setRecordId(pdStockRecord.getId());
					pdStockRecordInvoice2.preInsert();
				}
				pdStockRecordInvoiceDao.saveGroupByRecordId(list2);
			}
		}
		
	}
	
	/**
	 * 入库审核
	 * @param pdStockRecord
	 * @return
	 */
	@Transactional(readOnly = false)
	public CommonRspVo examineIn(PdStockRecord pdStockRecord, String storeroomType) {
		CommonRspVo rsp = new CommonRspVo();
		
		String id = pdStockRecord.getId();
		String recordNo = pdStockRecord.getRecordNo();
		String inId = pdStockRecord.getInId();
		String supplierId = null;
		User user = UserUtils.getUser();
		
		//修改入库单状态为“审核通过”
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);
		dao.examineIn(pdStockRecord);
		
		PdStockRecordProduct stockRecordProduct = new PdStockRecordProduct();
		stockRecordProduct.setRecordId(id);
		List<PdStockRecordProduct> productList = pdStockRecordProductDao.findPdList(stockRecordProduct);
		
		String inType = pdStockRecord.getInType();  //入库类型
		if(MinaGlobalConstants.STOCK_IN_TYPE_0.equals(inType)){  //正常入库
			if(MinaGlobalConstants.STOREROOM_TYPE_0.equals(storeroomType)){  //一级库房
				supplierId = pdStockRecord.getSupplierId();
				//String orderNo = pdStockRecord.getOrderNo(); //采购单号
				//String status = MinaGlobalConstants.PURCHASE_ORDER_STATUS_STOCKED;
				//pdPurchaseOrderMergeService.updateMergeOrderStatus(orderNo, status);
				//紧急产品处理
				String productId = null;
				String isUrgent = null;
				Integer productNum = null;
				Integer surplusPurCount = null;
				PdProduct pdProduct = null;
				Map isUrgentRtMap = null;
				for(PdStockRecordProduct psd:productList){
					productId = psd.getProductId();  //产品ID
					isUrgent = psd.getIsUrgent();  //是否紧急产品
					productNum = psd.getProductNum();
					surplusPurCount = psd.getSurplusPurCount();   //紧急产品剩余数量
					if(MinaGlobalConstants.URGENT_TYPE_YES.equals(isUrgent)){
						pdProduct = new PdProduct();
						pdProduct.setId(productId);
						pdProduct.setSurplusPurCount(productNum);
						isUrgentRtMap = pdProductService.minusSurplusPurCount(pdProduct);
					}
				}
			}else{
				String applyNo = pdStockRecord.getApplyNo(); //申领单号
				PdApplyOrder applyOrder = new PdApplyOrder();
				//完结
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_STOCK);	
				applyOrder.setApplyNo(applyNo);
				int i = pdApplyOrderDao.updateApplyState(applyOrder);
				
				int factCount = 0;
				for(PdStockRecordProduct p:productList){
					factCount = factCount + p.getProductNum();
				}
				//更新入库量
				pdApplyOrderDao.updateFactCount(applyNo, factCount, user.getId());
			}
		}else if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){  //退货入库
			
			
		}else if(MinaGlobalConstants.STOCK_IN_TYPE_2.equals(inType)){  //调拨入库
			String allocationNo = pdStockRecord.getAllocationNo();
			if(StringUtils.isNotEmpty(allocationNo)){
				PdAllocationRecord allocationRecord = new PdAllocationRecord();
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_STOCK);
				allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
				pdAllocationRecordDao.updateAllocationState(allocationRecord);
			}
		}
				
		//处理库存
		String inStr = pdProductStockTotalService.updateInStock(
				null, inId, storeroomType, supplierId, productList);
		
		//日志
		List<PdStockLog> logList = new ArrayList<PdStockLog>();
		PdStockLog stockLog = null;
		String outName = pdStockRecord.getOutName();
		String inName = pdStockRecord.getInName();
		String supplierName = null;
		if(StringUtils.isNotEmpty(supplierId)){
			supplierName = SpdUtils.getSupplierName(supplierId);
		}
		for(PdStockRecordProduct psd:productList){
			stockLog = new PdStockLog();
			stockLog.preInsert();
			
			stockLog.setInvoiceNo(recordNo);
			stockLog.setProductId(psd.getProductId());
			stockLog.setProductBarCode(psd.getProductBarCode());
			stockLog.setBatchNo(psd.getBatchNo());
			stockLog.setProductNum(String.valueOf(psd.getProductNum()));
			if(StringUtils.isNotEmpty(supplierId)){
				stockLog.setInFrom(supplierName);
			}else{
				stockLog.setInFrom(outName);
			}
			stockLog.setOutTo(inName);
			if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){
				stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_5);
			}else{
				stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_1);
			}
			stockLog.preInsert();
			stockLog.setRecordTime(stockLog.getCreateDate());
			logList.add(stockLog);
		}
		pdStockLogDao.batchInsert(logList);
		
		rsp.setCode("200");
		
		return rsp;
	}
	
	//验证出库
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public CommonRspVo examineOut(PdStockRecord pdStockRecord) throws Exception {
		CommonRspVo vo = new CommonRspVo();
		//修改出库记录状态为拒绝或通过
		if(MinaGlobalConstants.STOCK_RECORD_STATE_1.equals(pdStockRecord.getRecordState())){
			//1.先更新库存
			List<PdStockRecordProduct> list = pdStockRecord.getProductList();	
			List<PdStockLog> logList=new ArrayList<PdStockLog>();
			if(list!=null&&!list.isEmpty()){
				
				for (PdStockRecordProduct psd : list) {
					PdProductStock pdProductStock = new PdProductStock();
					if(StringUtils.isBlank(pdStockRecord.getOutId()) ||
							StringUtils.isBlank(psd.getProductId()) ||
							StringUtils.isBlank(psd.getBatchNo()) ||
							psd.getProductNum()==null){
							throw new Exception("参数错误");
							}

					pdProductStock.setProductId(psd.getProductId());
					//pdProductStock.setProductBarCode(psd.getProductBarCode());
					pdProductStock.setBatchNo(psd.getBatchNo());
					pdProductStock.setProductNo(psd.getNumber());
					pdProductStock.setStoreroomId(pdStockRecord.getOutId());
					pdProductStock.setStockNum(psd.getProductNum());
					List<PdProductStock> pdList = pdProductStockDao.findForUpdate(pdProductStock);
					if(pdList!=null&&!pdList.isEmpty()){
						PdProductStock pdProductStock2=pdList.get(0);
						pdProductStock.setId(pdProductStock2.getId());
						if(pdProductStock2.getStockNum()>0&&(pdProductStock2.getStockNum()>=pdProductStock.getStockNum())){
							int i=pdProductStockDao.minusStock(pdProductStock);
							if(i==0){
								throw new Exception("扣减库存失败");
							}else{
								logger.info("库存数{},要扣减库存数{}", pdProductStock2.getStockNum(),psd.getProductNum());
							}
						}else{
							vo.setCode("500");
							vo.setMessage(psd.getProductName()+"库存不足");
							throw new Exception(psd.getProductName()+"库存不足");
						}
						PdProductStockTotal stockTotal = new PdProductStockTotal();
						stockTotal.setProductId(psd.getProductId());
						stockTotal.setStoreroomId(pdStockRecord.getOutId());
						stockTotal.setStockNum(psd.getProductNum());
                        stockTotal.setNumber(psd.getNumber());
						List<PdProductStockTotal> tList=pdProductStockTotalDao.findForUpdate(stockTotal);
						if(tList!=null&&!tList.isEmpty()&&tList.size()==1){
							PdProductStockTotal total = tList.get(0);
							stockTotal.setId(total.getId());
							if(total.getStockNum()>0&&(total.getStockNum()>=stockTotal.getStockNum())){
								int ii=pdProductStockTotalDao.minusStock(stockTotal);
								if(ii==0){
									throw new Exception("扣减总库存失败");
								}else{
									logger.info("总库存是{},要扣减库存数{}", total.getStockNum(),psd.getProductNum());
								}
							}else{
								vo.setCode("500");
								vo.setMessage(psd.getProductName()+"库存不足");
								throw new Exception(psd.getProductName()+"总库存不足");
							}
						}else{
							throw new Exception("没有查询到要扣减的总库存");
						}
					}else{
						throw new Exception("没有查询到要扣减的库存");
					}
					//构造物流实体
					PdStockLog stockLog = new PdStockLog();
					stockLog.setProductId(psd.getProductId());
					stockLog.setBatchNo(psd.getBatchNo());
					stockLog.setInFrom(SpdUtils.getStoreroomName(pdStockRecord.getOutId()));
					stockLog.setOutTo(SpdUtils.getStoreroomName(pdStockRecord.getInId()));
					stockLog.setProductNum(psd.getProductNum().toString());
					stockLog.setProductBarCode(psd.getProductBarCode());
					if(StringUtils.isNotEmpty(psd.getAllocationNo())){
						stockLog.setInvoiceNo(psd.getAllocationNo());
					}
					if(StringUtils.isNotEmpty(psd.getApplyNo())){
						stockLog.setInvoiceNo(psd.getApplyNo());
					}
					stockLog.setRecordTime(new Date());
					String outType = pdStockRecord.getOutType();
					if(MinaGlobalConstants.STOCK_OUT_TYPE_1.equals(outType)){
						stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_5);
					}else{
						stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_2);
					}
					stockLog.preInsert();
					logList.add(stockLog);
				}
							
			}
			//2.更新申领单，调拨单状态
			if(StringUtils.isNotBlank(pdStockRecord.getApplyNo())){	//申领
				//出申领单（申领单状态改为出库，假出库）
				PdApplyOrder applyOrder = new PdApplyOrder();
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_STOCK);
				applyOrder.setApplyNo(pdStockRecord.getApplyNo());
				int i=pdApplyOrderDao.updateApplyState(applyOrder);
				if(i==0){
					throw new Exception(".更新申领状态失败");
				}
			}else if(StringUtils.isNotBlank(pdStockRecord.getAllocationNo())){	//调拨
				//出调拨单（调拨单状态改为出库，假出库）
				PdAllocationRecord allocationRecord = new PdAllocationRecord();
				allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_STOCK);
				int i=pdAllocationRecordDao.updateAllocationState(allocationRecord);
				if(i==0){
					throw new Exception("更新调拨单状态失败");
				}
			}else if(StringUtils.isNotBlank(pdStockRecord.getDosagertNo())){	//退货出货
				/*PdDosagert dosagert = new PdDosagert();
				dosagert.setDosagertNo(pdStockRecord.getDosagertNo());
				dosagert.setDosagertState(MinaGlobalConstants.DOSAGERT_STATE_2);
				pdDosagertDao.updateDosagertState(dosagert);*/
			}
			//3.更新出库记录状态
			if(MinaGlobalConstants.STOCK_OUT_TYPE_1.equals(pdStockRecord.getOutType())){
				pdStockRecord.setReturnState(MinaGlobalConstants.RETURN_STATE_0);
			}
			pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);
			int i=dao.updateRecordState(pdStockRecord);
			if(i==0){
				throw new Exception();
			}
			//4物流追溯
			pdStockLogDao.batchInsert(logList);

			/**
			 * 免去入库操作,直接生成入库单
			 */
			PdStockRecord inStockRecord = new PdStockRecord();
			inStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);
			inStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
			inStockRecord.setInType(pdStockRecord.getOutType());
			String recordNo = CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_RK);
			inStockRecord.setRecordNo(recordNo);
			User user = UserUtils.getUser();
			Date nowDate  = DateUtils.getNowDate();
			inStockRecord.setRecordDate(nowDate);
			inStockRecord.setRecordPeople(user.getName());
			String inId = pdStockRecord.getInId();
			String inName = SpdUtils.getStoreroomName(inId);
			inStockRecord.setInId(inId);     //入库库房
			inStockRecord.setInName(inName);  //入库库房名称
			inStockRecord.setOutId(pdStockRecord.getOutId());//出库库房
			inStockRecord.setOutName(pdStockRecord.getOutName());//出库库房名称
			inStockRecord.setCheckTime(DateUtils.getNowDate());
			inStockRecord.preInsert();
			String inType = inStockRecord.getInType();  //入库类型
			String importNo = "";
			if(MinaGlobalConstants.STOCK_IN_TYPE_0.equals(inType)){
				importNo = pdStockRecord.getApplyNo(); //申领单号
				inStockRecord.setApplyNo(pdStockRecord.getApplyNo());
			}else if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){//退货入库
				importNo = pdStockRecord.getRecordNo();
				inStockRecord.setDosagertNo(pdStockRecord.getDosagertNo());
			}else if(MinaGlobalConstants.STOCK_IN_TYPE_2.equals(inType)){//调拨入库
				importNo = pdStockRecord.getAllocationNo();
				inStockRecord.setAllocationNo(pdStockRecord.getAllocationNo());
			}
			pdStockRecordDao.insert(inStockRecord);
			List<PdStockRecordProduct> arrayList = pdStockRecord.getProductList();
			for (PdStockRecordProduct pdStockRecordProduct2 : arrayList) {
				pdStockRecordProduct2.setRecordId(inStockRecord.getId());
				pdStockRecordProduct2.setImportNo(importNo);
				pdStockRecordProduct2.preInsert();
			}
			pdStockRecordProductDao.saveGroupByRecordId(arrayList);
			String supplierId = pdStockRecord.getSupplierId();
			if(MinaGlobalConstants.STOCK_IN_TYPE_0.equals(inType)){  //正常入库
				if(MinaGlobalConstants.STOREROOM_TYPE_0.equals(SpdUtils.getStoreroomType(inId))){  //一级库房
					//String orderNo = pdStockRecord.getOrderNo(); //采购单号
					//String status = MinaGlobalConstants.PURCHASE_ORDER_STATUS_STOCKED;
					//pdPurchaseOrderMergeService.updateMergeOrderStatus(orderNo, status);
					  //一级库房
					//String orderNo = pdStockRecord.getOrderNo(); //采购单号
					//String status = MinaGlobalConstants.PURCHASE_ORDER_STATUS_STOCKED;
					//pdPurchaseOrderMergeService.updateMergeOrderStatus(orderNo, status);
					//紧急产品处理
					String productId = null;
					String isUrgent = null;
					Integer productNum = null;
					Integer surplusPurCount = null;
					PdProduct pdProduct = null;
					Map isUrgentRtMap = null;
					for(PdStockRecordProduct psd:arrayList){
						productId = psd.getProductId();  //产品ID
						isUrgent = psd.getIsUrgent();  //是否紧急产品
						productNum = psd.getProductNum();
						surplusPurCount = psd.getSurplusPurCount();   //紧急产品剩余数量
						if(MinaGlobalConstants.URGENT_TYPE_YES.equals(isUrgent)){
							pdProduct = new PdProduct();
							pdProduct.setId(productId);
							pdProduct.setSurplusPurCount(productNum);
							isUrgentRtMap = pdProductService.minusSurplusPurCount(pdProduct);
						}
					}

				}else{
					String applyNo = pdStockRecord.getApplyNo(); //申领单号
					PdApplyOrder applyOrder = new PdApplyOrder();
					//完结
					applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_STOCK);
					applyOrder.setApplyNo(applyNo);
					pdApplyOrderDao.updateApplyState(applyOrder);

					int factCount = 0;
					for(PdStockRecordProduct p:arrayList){
						factCount = factCount + p.getProductNum();
					}
					//更新入库量
					pdApplyOrderDao.updateFactCount(applyNo, factCount, user.getId());

				}
			}else if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){  //退货入库
				String dosagertNo = pdStockRecord.getDosagertNo();
				if(StringUtils.isNotEmpty(dosagertNo)){
					PdStockRecord stockRecord = new PdStockRecord();
					stockRecord.setRecordNo(dosagertNo);
					stockRecord.setReturnState(MinaGlobalConstants.RETURN_STATE_1);
					stockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_1);
					stockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
					pdStockRecordDao.updateReturnState(stockRecord);
				}

			}else if(MinaGlobalConstants.STOCK_IN_TYPE_2.equals(inType)){  //调拨入库
				  //调拨入库
				String allocationNo = pdStockRecord.getAllocationNo();
				if(StringUtils.isNotEmpty(allocationNo)){
					PdAllocationRecord allocationRecord = new PdAllocationRecord();
					allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_STOCK);
					allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
					pdAllocationRecordDao.updateAllocationState(allocationRecord);
				}

			}

			//处理库存
			String inStr = pdProductStockTotalService.updateInStock(
					null, inId, SpdUtils.getStoreroomType(inId), supplierId, arrayList);

			//日志
			List<PdStockLog> loggerList = new ArrayList<PdStockLog>();
			PdStockLog stockLog = null;
			String outName = user.getStoreroomName();
			String supplierName = null;
			if(StringUtils.isNotEmpty(supplierId)){
				supplierName = SpdUtils.getSupplierName(supplierId);
			}
			for(PdStockRecordProduct psd:arrayList){
				stockLog = new PdStockLog();
				stockLog.preInsert();

				stockLog.setInvoiceNo(recordNo);
				stockLog.setProductId(psd.getProductId());
				stockLog.setProductBarCode(psd.getProductBarCode());
				stockLog.setBatchNo(psd.getBatchNo());
				stockLog.setProductNum(String.valueOf(psd.getProductNum()));
				if(StringUtils.isNotEmpty(supplierId)){
					stockLog.setInFrom(supplierName);
				}else{
					stockLog.setInFrom(outName);
				}
				stockLog.setOutTo(inName);
				if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(inType)){
					stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_5);
				}else{
					stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_1);
				}
				stockLog.preInsert();
				stockLog.setRecordTime(stockLog.getCreateDate());
				loggerList.add(stockLog);
			}
			pdStockLogDao.batchInsert(loggerList);
		}		
		return vo;
	}	
	//outStock 出库方法
	@Transactional(readOnly = false)
	public CommonRspVo outStock(PdStockRecord pdStockRecord) {
		CommonRspVo rsp = new CommonRspVo();
		//取出入库单中所有产品，遍历
		if(pdStockRecord.getProductList() != null && pdStockRecord.getProductList().size() >0 ){
			for (PdStockRecordProduct pdStockRecordProduct : pdStockRecord.getProductList()) {
				PdProductStock pdProductStock = new PdProductStock();
				//验证参数是否存在
				if(StringUtils.isBlank(pdStockRecord.getOutId()) || 
						StringUtils.isBlank(pdStockRecordProduct.getProductId()) ||
						StringUtils.isBlank(pdStockRecordProduct.getProductBarCode()) ||
						StringUtils.isBlank(pdStockRecordProduct.getBatchNo()) ||
						pdStockRecordProduct.getProductNum()==null ||
						pdStockRecordProduct.getLimitDate() == null){
							rsp.setMessage("参数不完整");
							rsp.setCode("400");
							return rsp;
						}
				pdProductStock.setProductId(pdStockRecordProduct.getProductId());
				pdProductStock.setProductBarCode(pdStockRecordProduct.getProductBarCode());
				pdProductStock.setBatchNo(pdStockRecordProduct.getBatchNo());
				pdProductStock.setStoreroomId(pdStockRecord.getInId());
				pdProductStock.setStockNum(pdStockRecordProduct.getProductNum());
				pdProductStock.setValidDate(pdStockRecordProduct.getLimitDate());
				PdProductStock pdProductStock2 = pdProductStockDao.getByBatchNo(pdProductStock);
				//判断批次是否存在
				if(pdProductStock2 != null && StringUtils.isNotBlank(pdProductStock2.getId())){
					//首先，区分高低值耗材 highLowSupplies
					if("0".equals(pdStockRecordProduct.getHighLowSupplies())){
						//低值
						if(pdProductStock2.getStockNum() >= pdProductStock.getStockNum()){
							pdProductStockDao.minusStock(pdProductStock2);
						}else{
							//库存不足
							rsp.setMessage("批次库存不足");
							rsp.setCode("400");
							return rsp;
						}
					}else{
						//高值---出库数量为1
						pdProductStockDao.delete(pdProductStock2);
					}
				}else{
					//批次不存在
					rsp.setMessage("批次不存在");
					rsp.setCode("400");
					return rsp;
				}
			}
		}
		return rsp;
	}
	
	//inSstock 入库方法
	@Transactional(readOnly = false)
	public CommonRspVo inStock(PdStockRecord pdStockRecord) {
		CommonRspVo rsp = new CommonRspVo();
		//取出入库单中所有产品，遍历
		if(pdStockRecord.getProductList() != null && pdStockRecord.getProductList().size() >0 ){
			for (PdStockRecordProduct pdStockRecordProduct : pdStockRecord.getProductList()) {
				PdProductStock pdProductStock = new PdProductStock();
				//验证参数是否存在
				if(StringUtils.isBlank(pdStockRecord.getInId()) || 
						StringUtils.isBlank(pdStockRecordProduct.getProductId()) ||
						StringUtils.isBlank(pdStockRecordProduct.getProductBarCode()) ||
						StringUtils.isBlank(pdStockRecordProduct.getBatchNo()) ||
						pdStockRecordProduct.getProductNum()==null ||
						pdStockRecordProduct.getLimitDate() == null){
					rsp.setMessage("参数不完整");
					rsp.setCode("400");
					return rsp;
				}
				
				pdProductStock.setProductId(pdStockRecordProduct.getProductId());
				pdProductStock.setProductBarCode(pdStockRecordProduct.getProductBarCode());
				pdProductStock.setBatchNo(pdStockRecordProduct.getBatchNo());
				pdProductStock.setStoreroomId(pdStockRecord.getInId());
				pdProductStock.setStockNum(pdStockRecordProduct.getProductNum());
				pdProductStock.setValidDate(pdStockRecordProduct.getLimitDate());
				//首先，区分高低值耗材 highLowSupplies
				if("0".equals(pdStockRecordProduct.getHighLowSupplies())){
					//低值
					//判断批次是否存在
					PdProductStock pdProductStock2 = pdProductStockDao.getByBatchNo(pdProductStock);
					if(pdProductStock2 != null && StringUtils.isNotBlank(pdProductStock2.getId())){
						//批次存在
						pdProductStock2.setStockNum(pdStockRecordProduct.getProductNum());
						pdProductStockDao.addStock(pdProductStock2);
					}else{
						//批次不存在
						pdProductStock.preInsert();
						pdProductStockDao.insert(pdProductStock);
					}
				}else{
					//高值---入库数量为1
					pdProductStock.setStockNum(1);
					pdProductStock.preInsert();
					pdProductStockDao.insert(pdProductStock);
				}				
			}
		}		
		return rsp;
	}
	
	//整单驳回
	@Transactional(readOnly = false)
	public void rejectAll(PdStockRecord pdStockRecord) {
		String type = pdStockRecord.getRecodeType();		
		if(MinaGlobalConstants.STOCK_RECORD_TYPE_IN.equals(type)){
			//更新导入单状态
			if(StringUtils.isNotEmpty(pdStockRecord.getAllocationNo())){
				PdAllocationRecord allocationRecord = new PdAllocationRecord();
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_REJECT);							
				allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
				pdAllocationRecordDao.updateAllocationState(allocationRecord);
			}
			if(StringUtils.isNotEmpty(pdStockRecord.getApplyNo())){
				PdApplyOrder applyOrder = new PdApplyOrder();
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_IN_REJECT);
				applyOrder.setApplyNo(pdStockRecord.getApplyNo());
				pdApplyOrderDao.updateApplyState(applyOrder);
			}
			//退货单驳回时修改出库单的returnState状态
			if(MinaGlobalConstants.STOCK_IN_TYPE_1.equals(pdStockRecord.getInType())){
				PdStockRecord newPdStockRecord = new PdStockRecord();
				newPdStockRecord.setRecordNo(pdStockRecord.getDosagertNo());
				newPdStockRecord.setReturnState(MinaGlobalConstants.RETURN_STATE_0);
				newPdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
				newPdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_1);
				pdStockRecordDao.updateReturnState(newPdStockRecord);
			}
		}else if(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT.equals(type)){
			//更新导入单状态
			if(StringUtils.isNotEmpty(pdStockRecord.getAllocationNo())){
				PdAllocationRecord allocationRecord = new PdAllocationRecord();
				allocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_REJECT);							
				allocationRecord.setAllocationCode(pdStockRecord.getAllocationNo());
				pdAllocationRecordDao.updateAllocationState(allocationRecord);
			}
			if(StringUtils.isNotEmpty(pdStockRecord.getApplyNo())){
				PdApplyOrder applyOrder = new PdApplyOrder();
				applyOrder.setApplyStatus(MinaGlobalConstants.APPLY_ORDER_STATUS_OUT_REJECT);
				applyOrder.setApplyNo(pdStockRecord.getApplyNo());
				pdApplyOrderDao.updateApplyState(applyOrder);
			}
		}				
		dao.rejectAll(pdStockRecord);
	}
	
	/**
	 * 入库明细查询
	 * @param page
	 * @param pdStockRecord
	 * @return
	 */
	public Page<PdStockRecord> findCensusQuery(Page<PdStockRecord> page, PdStockRecord pdStockRecord) {
		pdStockRecord.setPage(page);
		page.setList(dao.findCensusQuery(pdStockRecord));
		return page;
	}
	
	/**
	 * 出库明细查询
	 */
	public Page<PdStockRecord> findOutQuery(Page<PdStockRecord> page, PdStockRecord pdStockRecord) {
		pdStockRecord.setPage(page);
		page.setList(dao.findOutQuery(pdStockRecord));
		return page;
	}
	
	/**
	 * 调入明细查询
	 * @param page
	 * @param pdStockRecord
	 * @return
	 */
	public Page<PdStockRecord> findCallInQuery(Page<PdStockRecord> page, PdStockRecord pdStockRecord) {
		pdStockRecord.setPage(page);
		page.setList(dao.findCallInQuery(pdStockRecord));
		return page;
	}
	
	/**
	 * 入库明细非分页-导出excel时使用
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdStockRecord> findCensusQuery(PdStockRecord pdStockRecord) {
		return pdStockRecordDao.findCensusQuery(pdStockRecord);
	}
	
	/**
	 * 入库明细非分页-导出excel时使用
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdStockRecord> findOutQuery(PdStockRecord pdStockRecord) {
		return pdStockRecordDao.findOutQuery(pdStockRecord);
	}
	
	/**
	 * 调入明细非分页-导出excel时使用
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdStockRecord> findCallInQuery(PdStockRecord pdStockRecord) {
		return pdStockRecordDao.findCallInQuery(pdStockRecord);
	}
	
	
	/**
	 * 查询产品的配送订货量配送量等信息(给中心平台的数据)
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdConsumablesOrderTime> findPdConsumablesOrderTime(PdStockRecord pdStockRecord){
		return dao.findPdConsumablesOrderTime(pdStockRecord);
	}
}