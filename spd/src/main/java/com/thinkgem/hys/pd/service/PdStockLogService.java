/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdStockLog;
import com.thinkgem.hys.pd.dao.PdStockLogDao;

/**
 * 物流追溯信息Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStockLogService extends CrudService<PdStockLogDao, PdStockLog> {

	public PdStockLog get(String id) {
		return super.get(id);
	}
	
	public List<PdStockLog> findList(PdStockLog pdStockLog) {
		return super.findList(pdStockLog);
	}
	
	public Page<PdStockLog> findPage(Page<PdStockLog> page, PdStockLog pdStockLog) {
		return super.findPage(page, pdStockLog);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStockLog pdStockLog) {
		super.save(pdStockLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStockLog pdStockLog) {
		super.delete(pdStockLog);
	}
	
}