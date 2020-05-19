package com.thinkgem.hys.pd.web.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.thinkgem.hys.pd.entity.PdHisSync;
import com.thinkgem.hys.pd.service.PdHisSyncService;

/**
 * @Date   2018-08-17
 * @author Mr.Wang
 * 每天同步his接口数据
 */

public class DealSyncFailedTask {
	
	private static Logger log = Logger.getLogger(DealSyncFailedTask.class);
	
	@Autowired
	private PdHisSyncService pdHisSyncService;
	
	@Autowired
	@Qualifier("syncDrugDataFromHIS")
	private SyncDrugDataTask task;

	/**
	 * 检查同步失败的数据
	 */
	public void checkAndSync() {
		log.info("**********************【同步HIS接口失败数据】任务开始*****************************");
		List<PdHisSync> hisList = pdHisSyncService.findList(new PdHisSync());
		for (PdHisSync pdHisSync : hisList) {
			switch(pdHisSync.getDataType()) {
				case "0"://药品目录
					syncDrug(pdHisSync);
					break;
				case "1"://药品总库存
					syncDrugTotalStock(pdHisSync);
					break;
				case "3"://药品入库
					syncDrugInStock(pdHisSync);
					break;
				case "4"://药品入库明细
					syncDrugInStockDetail(pdHisSync);
					break;
				case "5"://药品退货
					syncDrugRefund(pdHisSync);
					break;
				case "6"://药品退货明细
					syncDrugRefundDetail(pdHisSync);
					break;
				default:
			}
		}
		log.info("**********************【同步HIS接口失败数据】任务结束*****************************");
	}
	
	// 同步药品目录
	public void syncDrug(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.startDrugInfo();
	}
	// 同步药品总库存
	public void syncDrugTotalStock(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.startDrugTotalStock();
	}
	// 同步药品入库记录
	public void syncDrugInStock(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.exeInStock(sync.getDayTime());
	}
	// 同步药品入库记录详情
	public void syncDrugInStockDetail(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.exeInStockDetail(sync.getDayTime());
	}
	// 同步药品退货记录
	public void syncDrugRefund(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.exeRefund(sync.getDayTime());
	}
	// 同步药品退货记录详情
	public void syncDrugRefundDetail(PdHisSync sync) {
		pdHisSyncService.delete(sync);
		task.exeRefundDetail(sync.getDayTime());
	}
}
