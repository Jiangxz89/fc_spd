/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.dao.PdProductStockTimeDao;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 库存时刻表Service
 * @author wg
 * @version 2018-04-25
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockTimeService extends CrudService<PdProductStockTimeDao, PdProductStockTime> {

	public PdProductStockTime get(String id) {
		return super.get(id);
	}
	
	public List<PdProductStockTime> findList(PdProductStockTime pdProductStockTime) {
		return super.findList(pdProductStockTime);
	}
	
	public Page<PdProductStockTime> findPage(Page<PdProductStockTime> page, PdProductStockTime pdProductStockTime) {
		return super.findPage(page, pdProductStockTime);
	}
	public List<PdProductStockTime> findTodayList(PdProductStockTime pdProductStockTime) {
		return dao.queryTodayStockInfo(pdProductStockTime);
	}
	
	public Page<PdProductStockTime> findTodayPage(Page<PdProductStockTime> page, PdProductStockTime pdProductStockTime) {
		pdProductStockTime.setPage(page);
		page.setList(dao.queryTodayStockInfo(pdProductStockTime));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStockTime pdProductStockTime) {
		super.save(pdProductStockTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStockTime pdProductStockTime) {
		super.delete(pdProductStockTime);
	}
	//产品库存改变时调用此方法
	@Transactional(readOnly = false)
	public void updateProductStockTime(PdProductStockTime pdProductStockTime) {
		PdProductStockTime pst = dao.getPrdStockTime(pdProductStockTime);
		if( pst == null ) {
			super.save(pdProductStockTime);
		} else {
			pst.setStockNum(pdProductStockTime.getStockNum());
			super.save(pst);
		}
	}
	
	/**
	 * 批量插入
	 */
	@Transactional(readOnly = false)
	public int batchInsert(List<PdProductStock> list, Date stockDate){
		return dao.batchInsert(list, stockDate);
	}
}