package com.thinkgem.hys.pd.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdRpInventoryService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosagert;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.entity.PdRejectedListHead;
import com.thinkgem.hys.pd.entity.PdRpInventory;
import com.thinkgem.hys.pd.entity.PdStockRecord;

/**
 * 进销存报表定时任务
 * @author hys
 *
 */
//@Controller
//@RequestMapping(value = "${adminPath}/pd/inventoryStatement")
public class QuartInventoryStatementTask {
	
	private Logger logger = LoggerFactory.getLogger(QuartInventoryStatementTask.class);
	
	@Autowired
	private PdRpInventoryService rpInventoryService;
	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	
	/**
	 * 开始
	 */
	//@RequestMapping(value = {"start", ""})
	public void start() {
		logger.info("**********************统计查询 (进销存报表) 定时任务开始*****************************");
		Long dateTime = new Date().getTime()-24*60*60*1000;
		Date date= new Date(dateTime);
		//查询有没有昨天的数据如果有  不执行定时任务
		PdRpInventory newpdRpInventory = new PdRpInventory();
		newpdRpInventory.setRecordingDate(date);
		List<PdRpInventory> pdRpInventoryList = rpInventoryService.findListByDate(newpdRpInventory);
		if(pdRpInventoryList==null || pdRpInventoryList.size()==0){
			//查询库存总表所有数据
			List<PdProductStockTotal> pdProductStockTotals = pdProductStockTotalService.findListTask(new PdProductStockTotal());
			if(null != pdProductStockTotals && !pdProductStockTotals.isEmpty()){
				List<PdRpInventory> pdRpInventorys = new ArrayList<PdRpInventory>();
				PdRpInventory pdRpInventory = null;
				for(PdProductStockTotal pdProductStockTotal :pdProductStockTotals){
					pdRpInventory = new PdRpInventory();
					pdRpInventory.setId(IdGen.uuid());
					pdRpInventory.setCreateDate(new Date());
					pdRpInventory.setUpdateDate(new Date());
					pdRpInventory.setStoreroomId(pdProductStockTotal.getStoreroomId());
					pdRpInventory.setProductId(pdProductStockTotal.getProductId());
					pdRpInventory.setProductNo(pdProductStockTotal.getPdProduct().getNumber());
					pdRpInventory.setCreateBy(pdProductStockTotal.getCreateBy());
					pdRpInventory.setUpdateBy(pdProductStockTotal.getUpdateBy());
					pdRpInventory.setRecordingDate(date);
					pdRpInventory.setStockNum("0");
					pdRpInventory.setRukNum("0");
					pdRpInventory.setZhengcNum("0");
					pdRpInventory.setDiaocNum("0");
					pdRpInventory.setDiaorNum("0");
					pdRpInventory.setTuihcNum("0");
					pdRpInventory.setTuihrNum("0");
					pdRpInventory.setShiyNum("0");
					pdRpInventory.setTuihNum("0");
					pdRpInventory.setYythNum("0");
					pdRpInventorys.add(pdRpInventory);
				}
				//批量保存到进销存明细表中
				rpInventoryService.batchInsert(pdRpInventorys);
				
				//根据进销存表中的数据查询期初库存
				PdProductStockTime pdProductStockTime = new PdProductStockTime();
				pdProductStockTime.setStartDate(DateUtils.getNeedTime(0,0,0,-2));
				pdProductStockTime.setEndDate(DateUtils.getNeedTime(23,59,59,-2));
				List<PdRpInventory> initialInventorys = rpInventoryService.initialInventoryQuery(pdProductStockTime);
				if(null != initialInventorys && !initialInventorys.isEmpty()){
					for(PdRpInventory initialInventory :initialInventorys){
						//更新期初库存
						initialInventory.setStockNum(initialInventory.getTotal());
						initialInventory.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(initialInventory);
					}
				}
				
				//根据进销存表中的数据查询入库明细
				PdStockRecord pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);//类型为入库
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> inStockRecords = rpInventoryService.inStockRecordQuery(pdStockRecord);
				if(null != inStockRecords && !inStockRecords.isEmpty()){
					for(PdRpInventory inStockRecord :inStockRecords){
						//更新入库数
						inStockRecord.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(inStockRecord);
					}
				}
				
				//查询产品的本期出库(正常出库)
				pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);//类型为出库的
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_0);//只查正常出库的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> outbounds = rpInventoryService.bringUpTheQuery(pdStockRecord);
				if(null != outbounds && !outbounds.isEmpty()){
					for(PdRpInventory outbound :outbounds){
						//更新本期出库数
						outbound.setZhengcNum(outbound.getTotal());
						outbound.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(outbound);
					}
				}
				
				//查询产品的调出记录
				pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);//类型为出库的
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_2);//只查调拨出库的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> callIns = rpInventoryService.bringUpTheQuery(pdStockRecord);
				if(null != callIns && !callIns.isEmpty()){
					for(PdRpInventory callIn :callIns){
						//更新调出数
						callIn.setDiaocNum(callIn.getTotal());
						callIn.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(callIn);
					}
				}
				
				//查询产品的调入记录
				pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);//类型为入库的
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setInType(MinaGlobalConstants.STOCK_IN_TYPE_2);//只查调拨入库的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> bringUpThes = rpInventoryService.bringUpTheQuery(pdStockRecord);
				if(null != bringUpThes && !bringUpThes.isEmpty()){
					for(PdRpInventory bringUpThe :bringUpThes){
						//更新调入数
						bringUpThe.setDiaorNum(bringUpThe.getTotal());
						bringUpThe.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(bringUpThe);
					}
				}
				
				//查询产品的退货出库记录
				pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);//类型为出库的
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_1);//只查退货出库的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> returnOfs  = rpInventoryService.bringUpTheQuery(pdStockRecord);
				if(null != returnOfs && !returnOfs.isEmpty()){
					for(PdRpInventory returnOf :returnOfs){
						//更新退货出库数
						returnOf.setTuihcNum(returnOf.getTotal());
						returnOf.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(returnOf);
					}
				}
				
				//查询产品的退货入库记录
				pdStockRecord = new PdStockRecord();
				pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);//类型为入库的
				pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
				pdStockRecord.setInType(MinaGlobalConstants.STOCK_IN_TYPE_1);//只查退货入库的明细
				pdStockRecord.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdStockRecord.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> treasurys  = rpInventoryService.bringUpTheQuery(pdStockRecord);
				if(null != treasurys && !treasurys.isEmpty()){
					for(PdRpInventory treasury :treasurys){
						//更新退货入库数
						treasury.setTuihrNum(treasury.getTotal());
						treasury.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(treasury);
					}
				}
				
				//查询产品的使用记录
				PdDosage pdDosage = new PdDosage();
				pdDosage.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdDosage.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> equipmentUses  = rpInventoryService.equipmentUseQuery(pdDosage);
				if(null != equipmentUses && !equipmentUses.isEmpty()){
					for(PdRpInventory equipmentUse :equipmentUses){
						//更新使用数
						equipmentUse.setShiyNum(equipmentUse.getTotal());
						equipmentUse.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(equipmentUse);
					}
				}
				
				//查询产品的退回记录
				PdDosagert pdDosagert = new PdDosagert();
				pdDosagert.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdDosagert.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> amountOfReturns  = rpInventoryService.amountOfReturnQuery(pdDosagert);
				if(null != amountOfReturns && !amountOfReturns.isEmpty()){
					for(PdRpInventory amountOfReturn :amountOfReturns){
						//更新退回数
						amountOfReturn.setTuihNum(amountOfReturn.getTotal());
						amountOfReturn.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(amountOfReturn);
					}
				}
				
				//查询产品的院外退货数据
				PdRejectedListHead pdRejectedListHead = new PdRejectedListHead();
				pdRejectedListHead.setStartDate(DateUtils.getNeedTime(0,0,0,-1));
				pdRejectedListHead.setEndDate(DateUtils.getNeedTime(23,59,59,-1));
				List<PdRpInventory> hospitalReturnTheGoods  = rpInventoryService.hospitalReturnTheGoodsQuery(pdRejectedListHead);
				if(null != hospitalReturnTheGoods && !hospitalReturnTheGoods.isEmpty()){
					for(PdRpInventory hospitalReturnTheGood :hospitalReturnTheGoods){
						//更新院外退货数
						hospitalReturnTheGood.setYythNum(hospitalReturnTheGood.getTotal());
						hospitalReturnTheGood.setRecordingDate(date);
						rpInventoryService.updateRpInventoryByData(hospitalReturnTheGood);
					}
				}
			}
		}
		logger.info("**********************统计查询 (进销存报表) 定时任务结束*****************************");
		
	}
	
	public static void main(String[] args) {
		Long dateTime = new Date().getTime()-24*60*60*1000;
		Date date= new Date(dateTime);
		 //Date end = DateUtils.getNeedTime(23,59,59,-1);
		 System.out.println(DateUtils.formatDateTime(date));
		// System.out.println(end);
	}
	

}
