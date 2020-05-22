/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.dao.PdDosageDao;
import com.thinkgem.hys.pd.dao.PdDosageDetailDao;
import com.thinkgem.hys.pd.dao.PdProductStockDao;
import com.thinkgem.hys.pd.dao.PdStockLogDao;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdStockLog;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.HisApiUtils;
import com.thinkgem.hys.utils.MD5Utils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 器械用量Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdDosageService extends CrudService<PdDosageDao, PdDosage> {

	private static Logger logger = Logger.getLogger(PdDosageService.class);

	@Autowired
	private PdDosageDetailDao pdDosageDetailDao;
	@Autowired
	private PdStockLogDao pdStockLogDao;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdProductStockDao pdProductStockDao;
	@Autowired
	private PdDosageDao pdDosageDao;

	public PdDosage get(String id) {
		return super.get(id);
	}
	
	public List<PdDosage> findList(PdDosage pdDosage) {
		return super.findList(pdDosage);
	}
	
	public Page<PdDosage> findPage(Page<PdDosage> page, PdDosage pdDosage) {
		return super.findPage(page, pdDosage);
	}
	
	@Transactional(readOnly = false)
	public void save(PdDosage pdDosage) {
		super.save(pdDosage);
	}
	/**
	 * 保存器械用量
	 * @param pdDosage
	 */
	@Transactional(readOnly = false, rollbackFor = {RuntimeException.class, NullPointerException.class})
	public CommonRspVo saveDosage(PdDosage pdDosage) {
		CommonRspVo vo = new CommonRspVo();
		List<PdDosageDetail> detailList = pdDosage.getDosageDetailList();
		//校验数据的合法性
		Iterator<PdDosageDetail> it = detailList.iterator();
		while(it.hasNext()){
			PdDosageDetail child = it.next();
			if(child.getDosageCount()==null || child.getProdId()==null){
				it.remove();
			}
		}
		if(detailList != null && detailList.size() > 0){
			//是否收费
			String chargeToken = "";
			String oprtPeople = "";
			String oprtDate = "";
			if (MinaGlobalConstants.IS_CHARGE.equals(pdDosage.getChargeFlag())) {//收费
				chargeToken = MD5Utils.getToken();
//				oprtPeople = UserUtils.getUser().getHisUserNo();
				oprtDate = DateUtils.formatDate(DateUtils.getNowDate(), "yyyy-MM-dd HH:mm:ss");
			}
			//用量总量
			int dosageTotal = 0;
			BigDecimal moneyTotal = new BigDecimal(0);
			List<PdDosageDetail> tempArray = new ArrayList<PdDosageDetail>();
			//产品物流
			List<PdStockLog> logList = new ArrayList<PdStockLog>();
			//合并相同的用量
			List<PdDosageDetail> afterDealList = dealRepeatData(detailList);
			PdProductStock pps = new PdProductStock();
			pps.setStoreroomId(UserUtils.getUser().getStoreroomId());
			JSONObject json = new JSONObject();
			int i = 0;
			boolean validFlag = true;
			for(PdDosageDetail pdd : afterDealList){
				//校验不合法数据和大于库存数据
				pps.setBatchNo(pdd.getBatchNo());
				pps.setProductId(pdd.getProdId());
				pps.setProductNo(pdd.getProdNo());
				pps.setProductBarCode(pdd.getProdBarcode());
				PdProductStock tempPps = pdProductStockDao.searchOneStock(pps);
				if( null == tempPps || pdd.getDosageCount() > tempPps.getStockNum()){
					validFlag = false;
					json.put(String.valueOf(i), pdd);
					i = i + 1;
					continue;
				}
				
				String sprice = pdd.getSinglePrice();
				if(StringUtils.isEmpty(sprice)){
					sprice = "0";
				}
				BigDecimal pdMoney = new BigDecimal(pdd.getDosageCount()).multiply(new BigDecimal(sprice));
				dosageTotal += pdd.getDosageCount();
				moneyTotal = pdMoney.add(moneyTotal);
				pdd.setDosageNo(pdDosage.getDosageNo());
				pdd.setIsCharge(pdDosage.getChargeFlag()==null?"0":"1");
				pdd.setAmountMoney(pdMoney.doubleValue());
				pdd.setLeftRefundNum(pdd.getDosageCount());
				pdd.preInsert();
				tempArray.add(pdd);
				//产品追踪信息
				PdStockLog prodLog = new PdStockLog();
				prodLog.setBatchNo(pdd.getBatchNo());
				prodLog.setProductBarCode(pdd.getProdBarcode());
				prodLog.setProductId(pdd.getProdId());
				prodLog.setProductNum(pdd.getDosageCount().toString());
				prodLog.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_3);
				prodLog.setInFrom(pdDosage.getWarehouseName());
				prodLog.setOutTo("病人");
				prodLog.setPatientInfo(pdDosage.getPatientDetailInfo());
				prodLog.setChargeDeptName(pdDosage.getExeDeptName());
				prodLog.setRecordTime(DateUtils.getNowDate());
				prodLog.preInsert();
				logList.add(prodLog);
			}

			pdDosage.setDosageBy(UserUtils.getUser().getId());
			pdDosage.setAmountCount(dosageTotal);
			pdDosage.setAmountMoney(moneyTotal.doubleValue());
			pdDosage.setDosageDate(DateUtils.getNowDate());

			if (!validFlag) {//数据校验没通过
				vo.setCode("-200");
				vo.setData(json);
				vo.setMessage("请检查数据合理性！");
				return vo;
			} else {
				//是否收费
				if (MinaGlobalConstants.IS_CHARGE.equals(pdDosage.getChargeFlag())) {//收费
					List<PdDosageDetail> chargeList = new ArrayList<>();
					for(PdDosageDetail pdd : tempArray){
						PdStockLog prodLog1 = new PdStockLog();
						prodLog1.setBatchNo(pdd.getBatchNo());
						prodLog1.setProductBarCode(pdd.getProdBarcode());
						prodLog1.setProductId(pdd.getProdId());
						prodLog1.setProductNum(pdd.getDosageCount().toString());
						prodLog1.setLogType(MinaGlobalConstants.PRODUCT_FLOW_TYPE_6);
						prodLog1.setInFrom(pdDosage.getWarehouseName());//暂时写死，接口还没有
						prodLog1.setOutTo("");
						prodLog1.setPatientInfo("");
						prodLog1.setChargeDeptName("");
						prodLog1.setRecordTime(DateUtils.getNowDate());
						prodLog1.preInsert();
						logList.add(prodLog1);
						//调取收费接口
						//收费的产品才执行收费
						if("1".equals(pdd.getIsCharges())){
							//筛选收费产品
							chargeList.add(pdd);
//							JSONObject result = AxisUtils.exeCharge(pdd.getChargeCode(), pdd.getProdNo(), String.valueOf(pdd.getDosageCount()),
//									pdDosage.getInHospitalNo(), "0", oprtPeople, oprtDate, "",chargeToken,pdDosage.getOperativeNumber());
//							System.out.println("################收费接口返回值："+result);
//							if ("-200".equals(result.get("code"))) {
//								throw new RuntimeException("执行HIS收费接口失败！");
//							}
						}
					}
					JSONObject result = HisApiUtils.exeCharge(pdDosage,chargeList);
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
						for(PdDosageDetail pdd : tempArray){
							if(pdd.getProdNo().equals(prodNo)){
								pdd.setHisChargeId(hisChargeId);
								pdd.setHisChargeItemId(hisChargeItemId);
							}
						}
					}
				}
			}
			
			if(!tempArray.isEmpty()){
				System.out.println("################修改状态：");
				pdDosageDetailDao.batchInsert(tempArray);
			}
			if(!logList.isEmpty()){
				pdStockLogDao.batchInsert(logList);
			}
			//保存用量
			super.save(pdDosage);
			//扣减当前库房的库存
			pdProductStockTotalService.updateUseStock(UserUtils.getUser().getStoreroomId(), afterDealList);
		}else{
			vo.setCode("-400");
			vo.setMessage("用量不能为空");
		}
		return vo;
	}
	
	@Transactional(readOnly = false)
	public void delete(PdDosage pdDosage) {
		super.delete(pdDosage);
	}
	
	//合并相同的用量
	private List<PdDosageDetail> dealRepeatData(final List<PdDosageDetail> list){
		List<PdDosageDetail> tempArray = new ArrayList<PdDosageDetail>();
		Set<String> pids = new HashSet<String>();
		if(list != null && list.size() > 0){
			for(PdDosageDetail temp : list){
				if (temp == null || StringUtils.isEmpty(temp.getProdId()) || StringUtils.isEmpty(temp.getProdNo())
						|| StringUtils.isEmpty(temp.getProdBarcode()) || StringUtils.isEmpty(temp.getBatchNo())) {
					continue;
				}
				StringBuilder sb = new StringBuilder();
				sb.append(temp.getProdId()).append(temp.getProdNo()).append(temp.getProdBarcode()).append(temp.getBatchNo());
				if(pids.contains(sb.toString())){
					continue;
				}
				int dosageTotal = 0;
				for(PdDosageDetail tp : list){
					if ( tp != null) {
						if(temp.getBatchNo().equals(tp.getBatchNo()) 
								&& temp.getProdNo().equals(tp.getProdNo()) 
								&& temp.getProdBarcode().equals(tp.getProdBarcode())
								&& temp.getProdId().equals(tp.getProdId())){
							pids.add(sb.toString());
							dosageTotal += tp.getDosageCount();
						}
					}
				}
				temp.setDosageCount(dosageTotal);
				tempArray.add(temp);
			}
		}
		return tempArray;
	}
	
	/**
	 * 统计查询-用量明细
	 * @param page
	 * @param pdDosage
	 * @return
	 */
	public Page<PdDosage> findDosageSubsidiary(Page<PdDosage> page, PdDosage pdDosage) {
		pdDosage.setPage(page);
		page.setList(dao.findDosageSubsidiary(pdDosage));
		return page;
	}
	
	
	/**
	 * 用量明细非分页-导出excel时使用
	 * @param
	 * @return
	 */
	public List<PdDosage> findDosageSubsidiary(PdDosage pdDosage) {
		return dao.findDosageSubsidiary(pdDosage);
	}
}