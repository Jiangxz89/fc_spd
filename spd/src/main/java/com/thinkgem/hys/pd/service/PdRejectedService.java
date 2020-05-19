/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdRejected;
import com.thinkgem.hys.pd.entity.PdRejectedDetail;
import com.thinkgem.hys.pd.entity.PdStockLog;
import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdRejectedDao;
import com.thinkgem.hys.pd.dao.PdStockLogDao;

/**
 * 退货信息Service
 * @author yueguoyun
 * @version 2018-04-29
 */
@Service
@Transactional(readOnly = true)
public class PdRejectedService extends CrudService<PdRejectedDao, PdRejected> {

	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdStockLogDao pdStockLogDao;
	
	public PdRejected get(String id) {
		return super.get(id);
	}
	
	public List<PdRejected> findList(PdRejected pdRejected) {
		return super.findList(pdRejected);
	}
	
	public Page<PdRejected> findPage(Page<PdRejected> page, PdRejected pdRejected) {
		return super.findPage(page, pdRejected);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRejected pdRejected) {
		
		String storeroomId = pdRejected.getStoreroomId();
		super.save(pdRejected);
		
		List<PdRejectedDetail> rejectedDetails = pdRejected.getProductList();
		
		//处理库存
	//	Map rtMap = pdProductStockTotalService.updateRejectionStock(storeroomId, rejectedDetails);
	//	if("200".equals(rtMap.get("code"))){
			
	//	}else{
			
	//	}
		//记录日志
		List<PdStockLog> logList = new ArrayList<PdStockLog>();
		PdStockLog stockLog = null;
		String outName = pdRejected.getStoreroomName();
		//String inName = pdStockRecord.getOutName();
		for(PdRejectedDetail psd:rejectedDetails){
			stockLog = new PdStockLog();
			stockLog.preInsert();
			stockLog.setProductId(psd.getProductId());
			stockLog.setProductBarCode(psd.getProductBarCode());
			stockLog.setBatchNo(psd.getBatchNo());
			stockLog.setOutTo(outName);
			//stockLog.setInFrom(inName);
			stockLog.setProductNum(String.valueOf(psd.getProductNum()));
			stockLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_2);

			stockLog.preInsert();
			stockLog.setRecordTime(stockLog.getCreateDate());
			logList.add(stockLog);
		}
		pdStockLogDao.batchInsert(logList);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRejected pdRejected) {
		super.delete(pdRejected);
	}
	
}