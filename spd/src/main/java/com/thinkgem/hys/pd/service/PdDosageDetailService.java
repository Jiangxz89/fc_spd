/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.*;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.MD5Utils;
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
import java.util.HashMap;
import java.util.List;

/**
 * 器械用量详情Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdDosageDetailService extends CrudService<PdDosageDetailDao, PdDosageDetail> {
	
	@Autowired
	private PdDosageDao pdDosageDao;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdDosagertDetailDao pdDosagertDetailDao;
	@Autowired
	private PdDosagertDao pdDosagertDao;
	@Autowired
	private PdStockLogDao pdStockLogDao;


	public PdDosageDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdDosageDetail> findList(PdDosageDetail pdDosageDetail) {
		return super.findList(pdDosageDetail);
	}
	
	public Page<PdDosageDetail> findPage(Page<PdDosageDetail> page, PdDosageDetail pdDosageDetail) {
		return super.findPage(page, pdDosageDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdDosageDetail pdDosageDetail) {
		super.save(pdDosageDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdDosageDetail pdDosageDetail) {
		super.delete(pdDosageDetail);
	}
	
	/**
	 * 批量更新收费产品
	 */
	@Transactional(readOnly = false)
	public int updateIsCharge(String isCharge, String ids){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("isCharge", isCharge);
		map.put("ids", ids);
		return dao.updateIsCharge(map);
	}

	//收退费
	@Transactional(readOnly = false, rollbackFor=RuntimeException.class)
	public void exeChargeOrRefund(String ids, String isCharge, String dosageNo) {
		updateIsCharge(isCharge, ids);
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("ids", ids);
		List<PdDosageDetail> pdDosageDetail = dao.findPdDosageDetailByIds(map);
		if (pdDosageDetail != null && pdDosageDetail.size() > 0) {
			String token = MD5Utils.getToken();
			String oprtPeople = UserUtils.getUser().getHisUserNo();
			String oprtDate = DateUtils.formatDate(DateUtils.getNowDate(), "yyyy-MM-dd HH:mm:ss");
			String inHospitalNo = "";
			PdDosage pdo = pdDosageDao.get(dosageNo);
			if (pdo != null)
				inHospitalNo = pdo.getInHospitalNo();
			if (MinaGlobalConstants.IS_CHARGE.equals(isCharge)){
				for (PdDosageDetail pd : pdDosageDetail) {
					if("1".equals(pd.getIsCharges())){
						JSONObject result = AxisUtils.exeCharge(pd.getChargeCode(), pd.getProdNo(), String.valueOf(pd.getDosageCount()),
								inHospitalNo, "1", oprtPeople, oprtDate, "", token,pdo.getOperativeNumber());
					if ("-200".equals(result.get("code"))) {
						throw new RuntimeException("执行HIS收费接口失败！(补计费)");
					}
						System.out.println("执行收费了");
					}
				}
			}else if(MinaGlobalConstants.IS_CHARGE_2.equals(isCharge)){
				//退回库存生成用量退回单
				savePdDosagert(pdDosageDetail,inHospitalNo);
				for (PdDosageDetail pd : pdDosageDetail) {
					//取消收费并且把库存退回
					if("1".equals(pd.getIsCharges())){
						JSONObject result = AxisUtils.exeRefund(pd.getChargeCode(),pd.getProdNo(), String.valueOf(pd.getDosageCount()),
								inHospitalNo, "0", oprtPeople, oprtDate, "", token,pdo.getOperativeNumber());
					if ("-200".equals(result.get("code"))) {
						throw new RuntimeException("执行HIS退费接口失败！");
					}
						System.out.println("执行退费了");
					}
				}
			}else if(MinaGlobalConstants.IS_CHARGE_3.equals(isCharge)){
				//只做库存扣减
				savePdDosagert(pdDosageDetail,inHospitalNo);
			}
		}
	}

	public void savePdDosagert(List<PdDosageDetail> pdDosageDetails,String inHospitalNo){
		String number = CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_TYL);//生成编号
		PdDosagert pdDosagert = new PdDosagert();
		//当前操作人
		String oprtPeople = UserUtils.getUser().getName();
		String id = CommonUtils.getRandomString(32);
		Date date = CommonUtils.getAppointedDate("today");	//当前日期
		String storeroomId = UserUtils.getUser().getStoreroomId();
		pdDosagert.setId(id);
		pdDosagert.setOperaterName(oprtPeople);//当前操作人
		pdDosagert.setDosagertDate(date);//退回时间
		pdDosagert.setDosagertInroomId(storeroomId);//退回库房
		pdDosagert.setDosagertRoomId(storeroomId);//退回库房
		pdDosagert.setDosageNumber(pdDosageDetails.get(0).getDosageNo());
		pdDosagert.setDosagertNo(number);
		pdDosagert.setInHospitalNo(inHospitalNo);
		int dosagertCount = 0;	//总退回数量
		BigDecimal dosagertMoney = new BigDecimal(0);	//总退回金额
		//产品物流
		List<PdStockLog> logList = new ArrayList<PdStockLog>();
		//明细列表  批量保存用
		List<PdDosagertDetail> tempArray = new ArrayList<PdDosagertDetail>();
		for(int i = 0 ; i < pdDosageDetails.size() ; i ++){
			PdDosageDetail detail = pdDosageDetails.get(i);
			PdDosagertDetail pdDosagertDetail = new PdDosagertDetail();
			if(detail.getProdId() == null || detail.getDosageCount()-0<=0){
				pdDosageDetails.remove(i);
				i -= 1;
			}else{
				//插入明细
				dosagertCount += detail.getDosageCount();
				BigDecimal pdMoney = new BigDecimal(detail.getDosageCount()).multiply(new BigDecimal(detail.getPdProduct().getSellingPrice()));
				dosagertMoney = pdMoney.add(dosagertMoney);
				pdDosagertDetail.preInsert();
				pdDosagertDetail.setProdId(detail.getProdId());
				pdDosagertDetail.setProdNo(detail.getProdNo());
				pdDosagertDetail.setProdBarcode(detail.getProdBarcode());
				pdDosagertDetail.setBatchNo(detail.getBatchNo());
				pdDosagertDetail.setRtCount(detail.getLeftRefundNum());
				pdDosagertDetail.setUnitPrice(detail.getPdProduct().getSellingPrice());
				pdDosagertDetail.setDosagertId(id);
				pdDosagertDetail.setAmountMoney(pdMoney.doubleValue());
				pdDosagertDetail.setProdName(detail.getPdProduct().getName());
				pdDosagertDetail.setExpireDate(detail.getExpireDate());
				tempArray.add(pdDosagertDetail);

				//产品追踪信息
				PdStockLog prodLog = new PdStockLog();
				prodLog.setBatchNo(detail.getBatchNo());
				prodLog.setProductBarCode(detail.getProdBarcode());
				prodLog.setProductId(detail.getProdId());
				prodLog.setProductNum(detail.getLeftRefundNum().toString());
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
		pdProductStockTotalService.updateRetunuseStock(UserUtils.getUser().getStoreroomId(),tempArray);
		if(!tempArray.isEmpty()){
			//批量保存相信信息
			pdDosagertDetailDao.batchInsert(tempArray);
		}
		//批量保存产品日志
		if(!logList.isEmpty()){
			pdStockLogDao.batchInsert(logList);
		}
		pdDosagert.setDosagertCount(dosagertCount);
		pdDosagert.setDosagertMoney(dosagertMoney);
		pdDosagert.setCreateBy(UserUtils.getUser());
		pdDosagert.setUpdateBy(UserUtils.getUser());
		pdDosagert.setCreateDate(new Date());
		pdDosagert.setUpdateDate(new Date());
		pdDosagertDao.insertOwnId(pdDosagert);
	}

	/**
	 * 查询所有可退回数为0的用户使用
	 * @param pdDosageDetail
	 * @return
	 */
	public List<PdDosageDetail> findListByLeftRefundNum(PdDosageDetail pdDosageDetail) {
		return dao.findListByLeftRefundNum(pdDosageDetail);
		}
	
}