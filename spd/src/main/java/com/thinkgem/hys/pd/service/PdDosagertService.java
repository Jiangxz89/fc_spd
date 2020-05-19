/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.*;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.MD5Utils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用量退回信息Service
 * @author yueguoyun
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class PdDosagertService extends CrudService<PdDosagertDao, PdDosagert> {
	
	@Autowired
	PdDosagertDao pdDosagertDao;
	@Autowired
	PdDosagertDetailDao pdDosagertDetailDao;
	@Autowired
	PdProductStockDao pdProductStockDao;
	@Autowired
	PdProductDao pdProductDao;
	@Autowired
	PdProductStockTotalDao pdProductStockTotalDao;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdStockLogDao pdStockLogDao;
	@Autowired
	private PdDosageDetailDao pdDosageDetailDao;
	@Autowired
	private PdDosageDetailService pdDosageDetailService;

	public PdDosagert get(String id) {
		PdDosagert pdDosagert = super.get(id);
		
		PdDosagertDetail pdDosagertDetail = new PdDosagertDetail();
		pdDosagertDetail.setDosagertId(id);
		List<PdDosagertDetail> findList = pdDosagertDetailDao.findList(pdDosagertDetail);
		
		for(int i = 0 ; i < findList.size(); i ++){
			PdProduct product = pdProductDao.get(findList.get(i).getProdId());
			findList.get(i).setPdProduct(product);
		}
		
		pdDosagert.setDetailList(findList);
		
		return pdDosagert;
	}
	
	public List<PdDosagert> findList(PdDosagert pdDosagert) {
		return super.findList(pdDosagert);
	}
	
	public Page<PdDosagert> findPage(Page<PdDosagert> page, PdDosagert pdDosagert) {
		return super.findPage(page, pdDosagert);
	}
	
	@Transactional(readOnly = false)
	public void save(PdDosagert pdDosagert) {
		super.save(pdDosagert);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdDosagert pdDosagert) {
		super.delete(pdDosagert);
	}
	
	//保存主表和明细
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveAll(PdDosagert pdDosagert) {
		//当前操作人
		String oprtPeople = UserUtils.getUser().getName();
		//当前事件
		String oprtDate = DateUtils.formatDate(DateUtils.getNowDate(), "yyyy-MM-dd HH:mm:ss");
		//MD5加密的token
		String token = MD5Utils.getToken();
		List<PdDosagertDetail> detailList = pdDosagert.getDetailList(); //取明细列表
		String id = CommonUtils.getRandomString(32);
		pdDosagert.setId(id);
		pdDosagert.setOperaterName(oprtPeople);
		
		int dosagertCount = 0;	//总退回数量
		BigDecimal dosagertMoney = new BigDecimal(0);	//总退回金额
		//产品物流
		List<PdStockLog> logList = new ArrayList<PdStockLog>();
		//明细列表  批量保存用
		List<PdDosagertDetail> tempArray = new ArrayList<PdDosagertDetail>();
		for(int i = 0 ; i < detailList.size() ; i ++){
			PdDosagertDetail detail = detailList.get(i);
			
			if(detail.getProdId() == null || detail.getRtCount()-0<=0){
				detailList.remove(i);
				i -= 1;
			}else{
				
				//校验退回数量小于或等于可退回数
				PdDosageDetail pdDosageDetail = pdDosageDetailService.get(detail.getDosageDetailId());
				if(detail.getRtCount()>pdDosageDetail.getLeftRefundNum()){
					throw new RuntimeException("数据异常,退货数量不能大于可退货数");
				}
				//插入明细
				dosagertCount += detail.getRtCount();
				BigDecimal pdMoney = new BigDecimal(detail.getRtCount()).multiply(new BigDecimal(detail.getUnitPrice()));
				dosagertMoney = pdMoney.add(dosagertMoney);
				detail.preInsert();
				detail.setDosagertId(id);
				detail.setAmountMoney(pdMoney.doubleValue());
				tempArray.add(detail);
				
				//产品追踪信息
				PdStockLog prodLog = new PdStockLog();
				prodLog.setBatchNo(detail.getBatchNo());
				prodLog.setProductBarCode(detail.getProdBarcode());
				prodLog.setProductId(detail.getProdId());
				prodLog.setProductNum(detail.getRtCount().toString());
				prodLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_4);
				prodLog.setInFrom("病人");
				prodLog.setOutTo(UserUtils.getUser().getStoreroomName());
				prodLog.setPatientInfo("");
				prodLog.setChargeDeptName("");
				prodLog.setRecordTime(DateUtils.getNowDate());
				prodLog.preInsert();
				logList.add(prodLog);
			}
		}
		//增加库存操作
		pdProductStockTotalService.updateRetunuseStock(UserUtils.getUser().getStoreroomId(),detailList);
		//批量保存产品日志
		if(!logList.isEmpty()){
			pdStockLogDao.batchInsert(logList);
		}
		if(!tempArray.isEmpty()){
			//批量保存相信信息
			pdDosagertDetailDao.batchInsert(tempArray);
			//批量更新器械使用可退回数
			pdDosageDetailDao.updateLeftRefundNum(tempArray);
			if(!"".equals(Global.getConfig("HIS_DEFAULT_NAME_SPACE"))){
				//调用退费接口
				for(PdDosagertDetail pdDosagertDetail :tempArray){
					if(MinaGlobalConstants.IS_CHARGE.equals(pdDosagertDetail.getIsRefund())){
						/*JSONObject result = AxisUtils.exeRefund(pdDosagertDetail.getChargeCode(),String.valueOf(pdDosagertDetail.getRtCount()),
								pdDosagert.getInHospitalNo(), "0", oprtPeople, oprtDate, "", token);
						if ("-200".equals(result.get("code"))) {
							throw new RuntimeException("执行HIS退费接口失败！");
						}*/
					}
				}
			}
		}
		pdDosagert.setDosagertCount(dosagertCount);
		pdDosagert.setDosagertMoney(dosagertMoney);
		pdDosagert.setDosagertInroomId(UserUtils.getUser().getStoreroomId());
		pdDosagert.setCreateBy(UserUtils.getUser());
		pdDosagert.setUpdateBy(UserUtils.getUser());
		pdDosagert.setCreateDate(new Date());
		pdDosagert.setUpdateDate(new Date());
		pdDosagertDao.insertOwnId(pdDosagert);
		
	}

	/**
	 * 统计查询---用户退回明细
	 * @param page
	 * @param pdDosagert
	 * @return
	 */
	public Page<PdDosagert> detailList(Page<PdDosagert> page, PdDosagert pdDosagert) {
		pdDosagert.setPage(page);
		page.setList(dao.detailList(pdDosagert));
		return page;
	}
	
	/**
	 * 用户退回明细非分页-导出excel时使用
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdDosagert> detailList(PdDosagert pdDosagert) {
		return dao.detailList(pdDosagert);
	}
	
	
}