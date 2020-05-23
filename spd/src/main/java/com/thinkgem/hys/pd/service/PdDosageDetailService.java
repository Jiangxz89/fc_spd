/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.*;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.HisApiUtils;
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
	@Autowired
	private PdDosageService pdDosageService;


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
			String oprtPeople = "";//UserUtils.getUser().getHisUserNo();
			String oprtDate = DateUtils.formatDate(DateUtils.getNowDate(), "yyyy-MM-dd HH:mm:ss");
			String inHospitalNo = "";
			PdDosage pdo = pdDosageDao.get(dosageNo);
			if (pdo != null)
				inHospitalNo = pdo.getInHospitalNo();
			if (MinaGlobalConstants.IS_CHARGE.equals(isCharge)){ // 收费

				List<PdDosageDetail> chargeList = new ArrayList<>();

				for (PdDosageDetail pd : pdDosageDetail) {
					if("1".equals(pd.getIsCharges())){
						chargeList.add(pd);
					}
				}

				JSONObject result = HisApiUtils.exeCharge(pdo,chargeList);
				if(result == null || result.getJSONArray("data") == null || result.getJSONArray("data").size() <= 0){
					logger.error("HIS返回数据为空，请重新计费或联系管理员！！");
					throw new RuntimeException("HIS返回数据为空，请重新计费或联系管理员！！");
				}

				if(!MinaGlobalConstants.SUCCESS.equals(result.getString("statusCode"))){
					logger.error("执行HIS收费接口失败！HIS返回："+result.getString("msg"));
					throw new RuntimeException("执行HIS收费接口失败！HIS返回："+result.getString("msg"));
				}

				JSONArray array = result.getJSONArray("data");
				for(int k = 0; k < array.size(); k++){
					JSONObject obj = array.getJSONObject(k);   // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					String prodNo = obj.getString("prodNo");//产品编码
					String visitNo = obj.getString("vaa07");//就诊流水号
					String hisChargeId = obj.getString("vai01");//计费单据id
					String hisChargeItemId = obj.getString("vaj01");//计费单据明细id (退费用)
					for(PdDosageDetail pdd : pdDosageDetail){
						if(pdd.getProdNo().equals(prodNo)){
							PdDosageDetail update = new PdDosageDetail();
							update.setHisChargeId(hisChargeId);
							update.setHisChargeItemId(hisChargeItemId);
							update.setId(pdd.getId());
							update.setUpdateDate(DateUtils.getNowDate());
							dao.updateHisChargeId(update); // 更新HIS计费ID
						}
					}
				}
			}else if(MinaGlobalConstants.IS_CHARGE_2.equals(isCharge)){ //取消收费
				//退回库存生成用量退回单
				savePdDosagert(pdDosageDetail,inHospitalNo);

				List<PdDosageDetail> chargeList = new ArrayList<>();

				for (PdDosageDetail pd : pdDosageDetail) {
					//取消收费并且把库存退回
					if("1".equals(pd.getIsCharges())){
						chargeList.add(pd);
					}
				}

				JSONObject result = HisApiUtils.exeRefund(pdo,chargeList);
				if(!MinaGlobalConstants.SUCCESS.equals(result.getString("statusCode"))){
					logger.error("执行HIS退费接口失败！HIS返回："+result.getString("msg"));
					throw new RuntimeException("执行HIS退费接口失败！HIS返回："+result.getString("msg"));
				}

			}else if(MinaGlobalConstants.IS_CHARGE_3.equals(isCharge)){//退回库存
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