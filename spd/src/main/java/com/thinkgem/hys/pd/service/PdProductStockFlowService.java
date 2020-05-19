/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdProductStockFlow;
import com.thinkgem.hys.pd.dao.PdProductStockFlowDao;

/**
 * 库存流水Service
 * @author lww
 * @version 2018-03-28
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockFlowService extends CrudService<PdProductStockFlowDao, PdProductStockFlow> {

	public PdProductStockFlow get(String id) {
		return super.get(id);
	}
	
	public List<PdProductStockFlow> findList(PdProductStockFlow pdProductStockFlow) {
		return super.findList(pdProductStockFlow);
	}
	
	public Page<PdProductStockFlow> findPage(Page<PdProductStockFlow> page, PdProductStockFlow pdProductStockFlow) {
		return super.findPage(page, pdProductStockFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStockFlow pdProductStockFlow) {
		super.save(pdProductStockFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStockFlow pdProductStockFlow) {
		super.delete(pdProductStockFlow);
	}
	
}