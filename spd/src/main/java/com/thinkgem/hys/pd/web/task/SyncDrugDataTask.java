package com.thinkgem.hys.pd.web.task;

import java.util.Iterator;
import java.util.List;

import com.thinkgem.hys.pd.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.vo.MedstoYpcdmlkVo;
import com.thinkgem.hys.pd.entity.vo.TempEntity;
import com.thinkgem.hys.pd.service.MedstoYkzkcService;
import com.thinkgem.hys.pd.service.MedstoYpcdmlkService;
import com.thinkgem.hys.pd.service.MedstoYpcgzdService;
import com.thinkgem.hys.pd.service.MedstoYppckService;
import com.thinkgem.hys.pd.service.MedstoYprkmxService;
import com.thinkgem.hys.pd.service.MedstoYprkzdService;
import com.thinkgem.hys.pd.service.MedstoYpthmxService;
import com.thinkgem.hys.pd.service.MedstoYpthzdService;
import com.thinkgem.hys.pd.service.PdDrugSupplierService;
import com.thinkgem.hys.pd.service.PdHisSyncService;
import com.thinkgem.hys.pd.service.PdPurchaseOrderMergeService;
import com.thinkgem.hys.utils.AxisUtils;
import com.thinkgem.hys.utils.CpApiUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * @Date   2018-08-17
 * @author Mr.Wang
 * 每天同步his接口数据
 */

public class SyncDrugDataTask {
	
	private static Logger log = Logger.getLogger(SyncDrugDataTask.class);
	
	@Autowired
	private MedstoYpcdmlkService medstoYpcdmlkService;
	@Autowired
	private MedstoYprkzdService medstoYprkzdService;
	@Autowired
	private MedstoYprkmxService medstoYprkmxService;
	@Autowired
	private MedstoYpthzdService medstoYpthzdService;
	@Autowired
	private MedstoYpthmxService medstoYpthmxService;
	@Autowired
	private MedstoYkzkcService medstoYkzkcService;
	@Autowired
	private MedstoYppckService medstoYppckService;
	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	@Autowired
	private PdHisSyncService pdHisSyncService;
	@Autowired
	private PdDrugSupplierService pdDrugSupplierService;
	@Autowired
	private PdPurchaseOrderMergeService pdPurchaseOrderMergeService;

	
	/**
	 * 药库信息
	 */
	public void startDrugInfo() {
		log.info("**********************【药库目录同步】任务开始*****************************");
		JSONObject json = AxisUtils.getDrugInfo();
		if ("200".equals(json.get("code"))) {//成功返回数据
			//全量增加，先删除再插入
			medstoYpcdmlkService.clearData();
			Object o = json.get("data");
			List<MedstoYpcdmlkVo> list = JSONArray.parseArray(String.valueOf(o), MedstoYpcdmlkVo.class);
			Iterator<MedstoYpcdmlkVo> it = list.iterator();
			while (it.hasNext()) {//导入之前已修改的药品供应商
				MedstoYpcdmlkVo yp = it.next();
				yp.setGhdwId(null);
				yp.setGhdwMc(null);
				PdDrugSupplier pds = pdDrugSupplierService.getOne(yp.getYpdm(), MinaGlobalConstants.IS_DRUG_OR_CONSUMABLE_0);
				if (pds != null) {
					yp.setGhdwId(pds.getGhdwId());
					yp.setGhdwMc(pds.getGhdwMc());
				}
			}
			if (list != null && list.size()>0)
				medstoYpcdmlkService.batchInsert(list);
			
			//同步到中心平台
			JSONObject rst = CpApiUtils.syncDrugInfo(list);
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_0);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		}
		
		log.info("**********************【药库目录同步】任务结束*****************************");
	}
	
	
	/**
	 * 药库总库存信息
	 */
	public void startDrugTotalStock() {
		log.info("**********************【药库总库存同步】任务开始*****************************");
		JSONObject json = AxisUtils.getDrugStock();
		if ("200".equals(json.get("code"))) {//成功返回数据
			//全量更新，先删除再插入
			medstoYkzkcService.clearData();
			Object o = json.get("data");
			List<MedstoYkzkc> list = JSONArray.parseArray(String.valueOf(o), MedstoYkzkc.class);
			if (list != null && list.size()>0) {
				medstoYkzkcService.batchInsert(list);
			}
			
			//同步到中心平台
			JSONObject rst = CpApiUtils.syncDrugTotalStock(String.valueOf(o));
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_1);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		}
		
		log.info("**********************【药库总库存同步】任务结束*****************************");
	}
	
	
	/**
	 * 药库入库账单信息
	 */
	public void startDrugInStockInfo() {
		log.info("**********************【药库入库账单同步】任务开始*****************************");
		//昨天时间
		String yestoday = DateUtils.formatDate(DateUtils.getOneDayDate(DateUtils.getNowDate(), -1), "yyyyMMdd");
		exeInStock(yestoday);
		log.info("**********************【药库入库账单同步】任务结束*****************************");
	}
	
	public void exeInStock(String dayTime) {
		JSONObject json = AxisUtils.getDrugStorage(dayTime + "00:00:00", dayTime + "23:59:59");
		if ("200".equals(json.get("code"))) {//成功返回数据
			Object o = json.get("data");
			List<MedstoYprkzd> list = JSONArray.parseArray(String.valueOf(o), MedstoYprkzd.class);
			medstoYprkzdService.batchInsert(list);
			
			//同步到中心平台
			JSONObject rst = CpApiUtils.syncDrugInStock(String.valueOf(o));
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDayTime(dayTime);
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_3);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		} else if ("-200".equals(json.get("code")) || !"-1".equals(json.get("code"))) {//"-1"没有查询到数据
			PdHisSync pdc = new PdHisSync();
			pdc.setDayTime(dayTime);
			pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_3);
			pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
		}
	}
	
	
	/**
	 * 药库入库账单明细信息
	 */
	public void startDrugInStockDetail() {
		log.info("**********************【药库入库账单明细同步】任务开始*****************************");
		//昨天时间
		String yestoday = DateUtils.formatDate(DateUtils.getOneDayDate(DateUtils.getNowDate(), -1), "yyyyMMdd");
		exeInStockDetail(yestoday);
		
		log.info("**********************【药库入库账单明细同步】任务结束*****************************");
	}
	
	public void exeInStockDetail(String dayTime) {
		JSONObject json = AxisUtils.getDrugStorageDetail(dayTime + "00:00:00", dayTime + "23:59:59");
		if ("200".equals(json.get("code"))) {//成功返回数据
			Object o = json.get("data");
			List<MedstoYprkmx> list = JSONArray.parseArray(String.valueOf(o), MedstoYprkmx.class);
			medstoYprkmxService.batchInsert(list);
			
			//同步到中心平台
			JSONObject rst = CpApiUtils.syncDrugInStockDetail(String.valueOf(o));
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDayTime(dayTime);
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_4);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		} else if ("-200".equals(json.get("code")) || !"-1".equals(json.get("code"))) {//"-1"没有查询到数据
			PdHisSync pdc = new PdHisSync();
			pdc.setDayTime(dayTime);
			pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_4);
			pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
		}
	}
	
	
	/**
	 * 药库退货账单信息
	 */
	public void startDrugRefundInfo() {
		log.info("**********************【药库退货账单同步】任务开始*****************************");
		//昨天时间
		String yestoday = DateUtils.formatDate(DateUtils.getOneDayDate(DateUtils.getNowDate(), -1), "yyyyMMdd");
		exeRefund(yestoday);
		
		log.info("**********************【药库退货账单同步】任务结束*****************************");
	}
	
	public void exeRefund(String dayTime) {
		JSONObject json = AxisUtils.getDrugRefund(dayTime + "00:00:00", dayTime + "23:59:59");
		if ("200".equals(json.get("code"))) {//成功返回数据
			Object o = json.get("data");
			List<MedstoYpthzd> list = JSONArray.parseArray(String.valueOf(o), MedstoYpthzd.class);
			medstoYpthzdService.batchInsert(list);
			
			//同步到中心平台
			String hospCode = Global.getConfig("HOSPITAL_NUMBER");
			String hospName = Global.getConfig("HOSPITAL_NAME");
			JSONArray jarray = (JSONArray)o;
			for (int i=0;i<jarray.size();i++) {
				JSONObject jb = (JSONObject)jarray.get(i);
				jb.put("hospitalCode", hospCode);
				jb.put("hospitalName", hospName);
			}
			JSONObject rst = CpApiUtils.syncDrugRefund(jarray.toJSONString());
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDayTime(dayTime);
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_5);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		} else if ("-200".equals(json.get("code")) || !"-1".equals(json.get("code"))) {//"-1"没有查询到数据
			PdHisSync pdc = new PdHisSync();
			pdc.setDayTime(dayTime);
			pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_5);
			pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
		}
	}
	
	
	/**
	 * 药库退货账单明细信息
	 */
	public void startDrugRefundDetail() {
		log.info("**********************【药库退货账单明细同步】任务开始*****************************");
		//昨天时间
		String yestoday = DateUtils.formatDate(DateUtils.getOneDayDate(DateUtils.getNowDate(), -1), "yyyyMMdd");
		exeRefundDetail(yestoday);
		
		log.info("**********************【药库退货账单明细同步】任务结束*****************************");
	}
	
	public void exeRefundDetail(String dayTime) {
		JSONObject json = AxisUtils.getDrugRefundDetail(dayTime + "00:00:00", dayTime + "23:59:59");
		if ("200".equals(json.get("code"))) {//成功返回数据
			Object o = json.get("data");
			List<MedstoYpthmx> list = JSONArray.parseArray(String.valueOf(o), MedstoYpthmx.class);
			medstoYpthmxService.batchInsert(list);
			
			//同步到中心平台
			JSONObject rst = CpApiUtils.syncDrugRefundDetail(String.valueOf(o));
			if (!"200".equals(rst.get("statusCode"))) {//同步失败
				PdHisSync pdc = new PdHisSync();
				pdc.setDayTime(dayTime);
				pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_6);
				pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
			}
		} else if ("-200".equals(json.get("code")) || !"-1".equals(json.get("code"))) {//"-1"没有查询到数据
			PdHisSync pdc = new PdHisSync();
			pdc.setDayTime(dayTime);
			pdc.setDataType(MinaGlobalConstants.HIS_SYNC_DATA_TYPE_6);
			pdHisSyncService.insertRecord(pdc);//保存同步失败信息，以便下次同步
		}
	}
	
	//调取中心平台同步spd药品采购订单状态
	public void syncDrugCgzdStatus() {
		log.info("**********************【药品采购订单状态同步】任务开始*****************************");
		String today = DateUtils.getDate();
		JSONObject json = CpApiUtils.syncDrugCgzdStatus(today);
		if ("200".equals(json.get("statusCode"))){
			if (json.containsKey("data")) {
				
				String rst = json.getString("data");
				
				List<TempEntity> array = JSONArray.parseArray(rst, TempEntity.class);
				
				if (array != null && array.size() > 0) {
					medstoYpcgzdService.batchUpdate(array);
				}
			}
		}
		log.info("**********************【药品采购订单状态同步】任务结束*****************************");	
	}
	
	
	//调取中心平台同步spd耗材采购订单状态
	/*public void syncPdoductStatus() {
		log.info("**********************【药品采购订单状态同步】任务开始*****************************");
		String today = DateUtils.getDate();
		JSONObject json = CpApiUtils.syncPdoductStatus(today);
		if ("200".equals(json.get("statusCode"))){
			if (json.containsKey("data")) {
				
				String rst = json.getString("data");
				
				List<TempEntity> array = JSONArray.parseArray(rst, TempEntity.class);
				
				if (array != null && array.size() > 0) {
					pdPurchaseOrderMergeService.batchUpdate(array);
				}
			}
		}
		log.info("**********************【药品采购订单状态同步】任务结束*****************************");	
	}*/
	
}
