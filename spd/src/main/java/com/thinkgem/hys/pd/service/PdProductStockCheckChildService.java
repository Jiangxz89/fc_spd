/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdProductStockCheckChild;
import com.thinkgem.hys.pd.dao.PdProductStockCheckChildDao;

/**
 * 盘点详细表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockCheckChildService extends CrudService<PdProductStockCheckChildDao, PdProductStockCheckChild> {

	@Autowired
	PdProductStockCheckChildDao pdProductStockCheckChildDao;
	
	public PdProductStockCheckChild get(String id) {
		return super.get(id);
	}
	
	public List<PdProductStockCheckChild> findList(PdProductStockCheckChild pdProductStockCheckChild) {
		return super.findList(pdProductStockCheckChild);
	}
	
	public Page<PdProductStockCheckChild> findPage(Page<PdProductStockCheckChild> page, PdProductStockCheckChild pdProductStockCheckChild) {
		return super.findPage(page, pdProductStockCheckChild);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStockCheckChild pdProductStockCheckChild) {
		super.save(pdProductStockCheckChild);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStockCheckChild pdProductStockCheckChild) {
		super.delete(pdProductStockCheckChild);
	}
	
	@Transactional(readOnly = false)
	public void update(PdProductStockCheckChild pdProductStockCheckChild) {
		pdProductStockCheckChildDao.update(pdProductStockCheckChild);
	}
	
}