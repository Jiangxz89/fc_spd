package com.thinkgem.hys.pd.web.task;

import java.util.Date;
import java.util.List;

import com.thinkgem.hys.pd.entity.vo.TempEntity;
import com.thinkgem.hys.utils.CpApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdConsumablesOrder;
import com.thinkgem.hys.pd.entity.PdConsumablesOrderTime;
import com.thinkgem.hys.pd.entity.PdConsumablesRt;
import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.hys.pd.entity.PdStockRecord;
import com.thinkgem.hys.pd.entity.PdSyncState;
import com.thinkgem.hys.pd.service.PdPurchaseOrderMergeService;
import com.thinkgem.hys.pd.service.PdRejectedListHeadService;
import com.thinkgem.hys.pd.service.PdStockRecordService;
import com.thinkgem.hys.pd.service.PdSyncStateService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.HttpUtil;

/**
 * 中心平台数据同步定时任务
 * @author hys
 *
 */
//@Controller
//@RequestMapping(value = "${adminPath}/pd/inventoryStatement")
public class ConsumablesOrderTask {

	private static String BASE_URL = "";
	static {
		BASE_URL = Global.getConfig("SPD_CP_BASE_PRE_URL");
	}


	private Logger logger = LoggerFactory.getLogger(ConsumablesOrderTask.class);
	
	@Autowired
	private PdPurchaseOrderMergeService pdPurchaseOrderMergeService;
	
	@Autowired
	private PdStockRecordService pdStockRecordService;
	
	@Autowired
	private PdSyncStateService pdSyncStateService;
	
	@Autowired
	private PdRejectedListHeadService pdRejectedListHeadService;

	/**
	 * 开始  //采购订单  //{"msg":"success","statusCode":"200"}
	 */
	//@RequestMapping(value = {"start", ""})
	public void start() {
		logger.info("**********************提交耗材订单信息定时任务开始*****************************");
		//查询历史同步失败的数据到中心平台
		PdSyncState syncState = new PdSyncState();
		//syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_0); 2018年9月25日15:55:39
		syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_0);
		syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_0);
		List<PdSyncState> pdSyncStates = pdSyncStateService.findListByState(syncState);
		if(pdSyncStates!=null && pdSyncStates.size()>0){
			for(PdSyncState ss :pdSyncStates){
				//状态是否改变
				boolean state = false;
				//如果采购同步失败
/*				if(MinaGlobalConstants.SYNC_STATE_0.equals(ss.getPurchaseOrderState())){ //2018年9月25日15:55:25
					//重新同步
					ss = syncConsumablesOrder(ss);
					state = true;
				}*/
				//如果入库同步失败
				if(MinaGlobalConstants.SYNC_STATE_0.equals(ss.getStockRecordState())){
					//重新同步
					ss = syncPdConsumablesOrderTime(ss);
					state = true;
				}
				//如果退货同步失败
				if(MinaGlobalConstants.SYNC_STATE_0.equals(ss.getRtProductState())){
					ss = syncPdConsumablesRt(ss);
					state = true;
				}
				//更新同步表的状态
				if(state){
					pdSyncStateService.save(ss);
				}
			}
		}
		
		//查询当天的数据同步到中心平台
		//更新当天的数据
		Date date = DateUtils.getNowDate();
		//获得前一天的数据
		//Date date = DateUtils.getOneDayDate(new Date(), -1);

		PdSyncState newSyncState = new PdSyncState();
		newSyncState.setSyncDate(date);

		//查询是否已经插入当天数据(避免重复) 继续同步未成功的数据
		PdSyncState pdSyncState = pdSyncStateService.findByDate(newSyncState);
		if(pdSyncState!=null){
			//如果采购同步失败
			//状态是否改变
			boolean state = false;
/*			if(MinaGlobalConstants.SYNC_STATE_0.equals(pdSyncState.getPurchaseOrderState())){ //2018年9月25日15:55:21
				//重新同步
				pdSyncState = syncConsumablesOrder(pdSyncState);
				state = true;
			}*/
			//如果入库同步失败
			if(MinaGlobalConstants.SYNC_STATE_0.equals(pdSyncState.getStockRecordState())){
				//重新同步
				pdSyncState = syncPdConsumablesOrderTime(pdSyncState);
				state = true;
			}
			
			//如果退货同步失败
			if(MinaGlobalConstants.SYNC_STATE_0.equals(pdSyncState.getRtProductState())){
				pdSyncState = syncPdConsumablesRt(pdSyncState);
				state = true;
			}
			//改变则更新
			if(state){
				pdSyncStateService.save(pdSyncState);
			}
		}else{
			//同步采供单数据
			/*newSyncState = syncConsumablesOrder(newSyncState);*///2018年9月25日15:55:15
			//同步入库数据
			newSyncState = syncPdConsumablesOrderTime(newSyncState);
			//同步退货数据
			newSyncState = syncPdConsumablesRt(newSyncState);
			pdSyncStateService.save(newSyncState);
		}
		logger.info("**********************提交耗材订单信息定时任务结束*****************************");

		//调取中心平台同步spd耗材采购订单状态
		//syncPdoductStatus();
	}
	
	
	//采购订单同步任务
	public PdSyncState syncConsumablesOrder (PdSyncState syncState) {
		PdPurchaseOrder order = new PdPurchaseOrder();
		order.setAuditDate(syncState.getSyncDate());
		//采购订单集合
		try{
			List<PdConsumablesOrder> consumablesOrders = pdPurchaseOrderMergeService.getConsumeOrder(order);
			//有数据再同步
			if(consumablesOrders!=null && consumablesOrders.size()>0){
				String jsonPar = JSONArray.toJSONString(consumablesOrders);
				JSONObject json = HttpUtil.httpPost(BASE_URL + Global.getConfig("CONSUMABLES_ORDER_URL"), jsonPar);
				if(json.containsKey("statusCode")){
					if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
						syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
					}else{
						syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
					}
				}else{
					syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
				}
			}else{
				syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
			}
		}catch (Exception e){
			e.printStackTrace();
			syncState.setPurchaseOrderState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
		}
		return syncState;
	}

	/**
	 * 同步采购订单 供应商状态
	 */
	public void syncConsumablesOrderSupplierStatus() {
		logger.info("**********************【耗材采购订单状态同步】任务开始*****************************");
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
		logger.info("**********************【耗材采购订单状态同步】任务结束*****************************");
	}

	//入库(配送明细同步)
	public PdSyncState syncPdConsumablesOrderTime(PdSyncState syncState){
		PdStockRecord pdStockRecord = new PdStockRecord();
		pdStockRecord.setCheckTime(syncState.getSyncDate());
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);
		pdStockRecord.setInType(MinaGlobalConstants.STOCK_IN_TYPE_0);
		//耗材订单及其配送
		try{
			List<PdConsumablesOrderTime> consumablesOrderTimes = pdStockRecordService.findPdConsumablesOrderTime(pdStockRecord);
			//有数据再同步
			if(consumablesOrderTimes!=null && consumablesOrderTimes.size()>0){
				String consumablesOrderTimeJson = JSONArray.toJSONString(consumablesOrderTimes);
				JSONObject orderTimeJson = HttpUtil.httpPost(BASE_URL + Global.getConfig("CONSUMABLES_ORDER_TIME_URL"), consumablesOrderTimeJson);
				if(orderTimeJson.containsKey("statusCode")){
					if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(orderTimeJson.get("statusCode"))){
						syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
					}else{
						syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
					}
				}else{
					syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
				}
			}else{
				syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
			}
		}catch(Exception e){
			e.printStackTrace();
			syncState.setStockRecordState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
		}
		return syncState;
	}
	
	//退货
	public PdSyncState syncPdConsumablesRt(PdSyncState syncState){
		//退货单集合
		try{
			List<PdConsumablesRt> consumablesRts = pdRejectedListHeadService.returnPdConsumablesRtList(syncState.getSyncDate());
			if(consumablesRts!=null && consumablesRts.size()>0){
				String jsonPar = JSONArray.toJSONString(consumablesRts);
				JSONObject json = HttpUtil.httpPost(BASE_URL + Global.getConfig("CONSUMABLES_RT_URL"), jsonPar);
				if(json.containsKey("statusCode")){
					if(MinaGlobalConstants.SYNC_STATE_SUCCESS.equals(json.get("statusCode"))){
						syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
					}else{
						syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
					}
				}else{
					syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
				}
			}else{
				syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_1);//同步成功
			}
		}catch (Exception e){
			e.printStackTrace();
			syncState.setRtProductState(MinaGlobalConstants.SYNC_STATE_0);//同步失败
		}
		return syncState;
	}
}
