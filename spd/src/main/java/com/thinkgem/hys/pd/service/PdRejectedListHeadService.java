/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdStockLogDao;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.dao.PdProductStockDao;
import com.thinkgem.hys.pd.dao.PdRejectedListChildDao;
import com.thinkgem.hys.pd.dao.PdRejectedListHeadDao;
import com.thinkgem.hys.utils.CommonUtils;

/**
 * 退货单单头表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdRejectedListHeadService extends CrudService<PdRejectedListHeadDao, PdRejectedListHead> {
	@Autowired
	PdRejectedListHeadDao pdRejectedListHeadDao;
	@Autowired
	PdRejectedListChildDao pdRejectedListChildDao;
	@Autowired
	PdRejectedListChildService pdRejectedListChildService;
	@Autowired
	PdProductStockDao pdProductStockDao;
	@Autowired
	private PdStockLogDao pdStockLogDao;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;

	public PdRejectedListHead get(String id) {
		return super.get(id);
	}
	
	public List<PdRejectedListHead> findList(PdRejectedListHead pdRejectedListHead) {
		return super.findList(pdRejectedListHead);
	}
	
	public Page<PdRejectedListHead> findPage(Page<PdRejectedListHead> page, PdRejectedListHead pdRejectedListHead) {
		return super.findPage(page, pdRejectedListHead);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRejectedListHead pdRejectedListHead) {
		super.save(pdRejectedListHead);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRejectedListHead pdRejectedListHead) {
		super.delete(pdRejectedListHead);
	}
	
	/**
	 * 更新字段为已完结
	 * */
	public void isEnd(String id){
		pdRejectedListHeadDao.isEnd(id);
	}
	
	/**
	 * 院外退货明细查询
	 * @param pdStockRecord
	 * @return
	 */
	public Page<PdRejectedListHead> findHospitaloutsideReturnDetail(Page<PdRejectedListHead> page,
			PdRejectedListHead pdRejectedListHead) {
		pdRejectedListHead.setPage(page);
		page.setList(dao.findHospitaloutsideReturnDetail(pdRejectedListHead));
		return page;
	}
	
	/**
	 * 院外退货明细查询-非分页导出excel时使用
	 * @param pdRejectedListHead
	 * @return
	 */
	public List<PdRejectedListHead> findHospitaloutsideReturnDetail(PdRejectedListHead pdRejectedListHead) {
		return dao.findHospitaloutsideReturnDetail(pdRejectedListHead);
	}


	
	/**
	 * 
	 * */
	public List<PdConsumablesRt> returnPdConsumablesRtList(Date date){
		List<PdConsumablesRt> conRtList = new ArrayList<PdConsumablesRt>();	//最终返回容器
		
		String formatDate = CommonUtils.formatDate("SHORT",date);
		PdRejectedListHead pdRejectedListHead = new PdRejectedListHead();
		pdRejectedListHead.setRejectedDate(formatDate);
		
		List<PdRejectedListHead> headList = pdRejectedListHeadDao.findListByDate(pdRejectedListHead);//指定日期的退货单头
		
		for(int i = 0 ; i < headList.size() ; i ++){
			PdRejectedListChild child = new PdRejectedListChild();
			child.setHeadId(headList.get(i).getId());
			String rtDate = headList.get(i).getRejectedDate();
			String rtNumber = headList.get(i).getNumber();
			
			List<PdRejectedListChild> childList = pdRejectedListChildDao.findList(child);
			for(PdRejectedListChild c : childList){
				PdProductStock stock = pdProductStockDao.get(c.getProductStockId());
				Double sellingPrice = stock.getSellingPrice();
				Double amount = sellingPrice * c.getRejectedCount();
				String prodNumber = stock.getPdProduct().getNumber();
				
				PdConsumablesRt pdConsumablesRt = new PdConsumablesRt();
				pdConsumablesRt.setRtDate(rtDate);
				pdConsumablesRt.setRtNumber(rtNumber);
				pdConsumablesRt.setProductNumber(prodNumber);
				pdConsumablesRt.setRtQuantity(c.getRejectedCount());
				pdConsumablesRt.setPrice(sellingPrice);
				pdConsumablesRt.setAmount(amount);
				pdConsumablesRt.setSupplierName(headList.get(i).getSupplier());
				conRtList.add(pdConsumablesRt);
			}
		}
		
		
		return conRtList;
	}

	@Transactional(readOnly = false)
	public void saveNew(PdRejectedListHead pdRejectedListHead, List<PdRejectedListChild> childs) {
		//保存
		this.save(pdRejectedListHead);
		PdProductStock pps = new PdProductStock();
		pps.setStoreroomId(UserUtils.getUser().getStoreroomId());
		//产品物流
		List<PdStockLog> logList = new ArrayList<PdStockLog>();
		for(PdRejectedListChild plc :childs){
			pps.setBatchNo(plc.getBatchNo());
			pps.setProductId(plc.getProdId());
			pps.setProductNo(plc.getProdNo());
			pps.setProductBarCode(plc.getBarcode());
			PdProductStock tempPps = pdProductStockDao.searchOneStock(pps);
			if (null == tempPps || plc.getRejectedCount() > tempPps.getStockNum()) {
				continue;
			}
			plc.setHeadId(pdRejectedListHead.getId());

			//产品追踪信息
			PdStockLog prodLog = new PdStockLog();
			prodLog.setBatchNo(plc.getBatchNo());
			prodLog.setProductBarCode(plc.getBarcode());
			prodLog.setProductId(plc.getProdId());
			prodLog.setProductNum(String.valueOf(plc.getRejectedCount()));
			prodLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_7);
			prodLog.setInFrom(UserUtils.getUser().getStoreroomName());
			prodLog.setOutTo("供应商");
			prodLog.setRecordTime(DateUtils.getNowDate());
			prodLog.preInsert();
			logList.add(prodLog);
			pdRejectedListChildService.save(plc);
		}
		if(!logList.isEmpty())
			pdStockLogDao.batchInsert(logList);
		pdProductStockTotalService.updateRejectionStock(pdRejectedListHead.getRejectedRepoId(), pdRejectedListHead);
	}
}