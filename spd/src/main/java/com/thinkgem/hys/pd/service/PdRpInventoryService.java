/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosagert;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.hys.pd.entity.PdRejectedListHead;
import com.thinkgem.hys.pd.entity.PdRpInventory;
import com.thinkgem.hys.pd.entity.PdStockRecord;
import com.thinkgem.hys.pd.dao.PdRpInventoryDao;

/**
 * 进销存明细Service
 * @author zxh
 * @version 2018-05-10
 */
@Service
@Transactional(readOnly = true)
public class PdRpInventoryService extends CrudService<PdRpInventoryDao, PdRpInventory> {

	public PdRpInventory get(String id) {
		return super.get(id);
	}
	
	public List<PdRpInventory> findList(PdRpInventory pdRpInventory) {
		return super.findList(pdRpInventory);
	}
	
	public Page<PdRpInventory> findPage(Page<PdRpInventory> page, PdRpInventory pdRpInventory) {
		return super.findPage(page, pdRpInventory);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRpInventory pdRpInventory) {
		super.save(pdRpInventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRpInventory pdRpInventory) {
		super.delete(pdRpInventory);
	}
	
	/**
	 * 批量保存到进销存明细表中
	 * @param pdRpInventory
	 */
	@Transactional(readOnly = false)
	public void batchInsert(List<PdRpInventory> pdRpInventorys) {
		dao.batchInsert(pdRpInventorys);
		
	}
	
	/**
	 * 进销存明细表查询入库数
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdRpInventory> inStockRecordQuery(PdStockRecord pdStockRecord) {
		return dao.inStockRecordQuery(pdStockRecord);
	}
	
	/**
	 * 根据进销存表中的数据查询期初库存
	 * @param pdProductStockTime
	 * @return
	 */
	public List<PdRpInventory> initialInventoryQuery(PdProductStockTime pdProductStockTime) {
		return dao.initialInventoryQuery(pdProductStockTime);
	}
	
	/**
	 * 进销存明细表查询出入库记录
	 * @param pdStockRecord
	 * @return
	 */
	public List<PdRpInventory> bringUpTheQuery(PdStockRecord pdStockRecord) {
		return dao.bringUpTheQuery(pdStockRecord);
	}
	
	/**
	 * 进销存明细表查询器械使用记录
	 * @param pdDosage
	 * @return
	 */
	public List<PdRpInventory> equipmentUseQuery(PdDosage pdDosage) {
		return dao.equipmentUseQuery(pdDosage);
	}
	
	/**
	 * 进销存明细表查询用量退回记录
	 * @param pdDosagert
	 * @return
	 */
	public List<PdRpInventory> amountOfReturnQuery(PdDosagert pdDosagert) {
		return dao.amountOfReturnQuery(pdDosagert);
	}
	
	/**
	 * 进销存明细表查询院外退货记录
	 * @param pdRejectedListHead
	 * @return
	 */
	public List<PdRpInventory> hospitalReturnTheGoodsQuery(PdRejectedListHead pdRejectedListHead) {
		return dao.hospitalReturnTheGoodsQuery(pdRejectedListHead);
	}
	
	/**
	 * 更新各类数据
	 * @param pdRpInventory
	 */
	@Transactional(readOnly = false)
	public void updateRpInventoryByData(PdRpInventory pdRpInventory) {
		dao.updateRpInventoryByData(pdRpInventory);
	}
	
	/**
	 * 进销存报表统计
	 * @param page
	 * @param pdRpInventory
	 * @return
	 */
	public Page<PdRpInventory> findEntersSellsQuery(Page<PdRpInventory> page, PdRpInventory pdRpInventory) {
		pdRpInventory.setPage(page);
		page.setList(dao.findEntersSellsQuery(pdRpInventory));
		return page;
	}
	
	/**
	 * 进销存报表统计不分页
	 * @param pdRpInventory
	 * @return
	 */
	public List<PdRpInventory> findEntersSellsQuery(PdRpInventory pdRpInventory) {
		return dao.findEntersSellsQuery(pdRpInventory);
	}
	
	/**
	 * 查询有没有昨天的数据(避免定时任务重复跑)
	 * @param needTime
	 * @return
	 */
	public List<PdRpInventory> findListByDate(PdRpInventory newpdRpInventory) {
		return dao.findListByDate(newpdRpInventory);
	}
}