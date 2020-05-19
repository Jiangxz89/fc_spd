/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.dao.PdProductStockDao;

/**
 * 库存记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdProductStockService extends CrudService<PdProductStockDao, PdProductStock> {

	@Autowired
	PdProductStockDao pdProductStockDao;
	
	public PdProductStock get(String id) {
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public List<PdProductStock> findList(PdProductStock pdProductStock) {
		return super.findList(pdProductStock);
	}
	
	public Page<PdProductStock> findPage(Page<PdProductStock> page, PdProductStock pdProductStock) {
		return super.findPage(page, pdProductStock);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductStock pdProductStock) {
		super.save(pdProductStock);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductStock pdProductStock) {
		super.delete(pdProductStock);
	}
	
	public List<PdProductStock> getByProductBarCode(PdProductStock pdProductStock){
		return pdProductStockDao.getByProductBarCode(pdProductStock);
	}
	
	//更新库存
	@Transactional(readOnly = false)
	public void updateStockNum(PdProductStock pdProductStock){
		pdProductStockDao.updateStockNum(pdProductStock);
	}
	
	/**
	 * 库存查询
	 * @param page
	 * @param pdProductStock
	 * @return
	 */
	public Page<PdProductStock> findListForQuery(Page<PdProductStock> page, PdProductStock pdProductStock) {
		pdProductStock.setPage(page);
		page.setList(dao.findListForQuery(pdProductStock));
		return page;
	}
	public List<PdProductStock> findListForQuery(PdProductStock pdProductStock) {
		return dao.findListForQuery(pdProductStock);
	}
	
	public Map<String, Object> findPdCount(PdProductStock productStock){
		return pdProductStockDao.findPdCount(productStock);
	}
	/**
	 * 物流追溯产品列表
	 * @param pdProductStock
	 * @return
	 */
	public List<PdProductStock> getByOriginalProduct(PdProductStock pdProductStock){
		return dao.getByOriginalProduct(pdProductStock);
	}
	
	@Transactional(readOnly = false)
	public void updatePdState(PdProductStock pdProductStock) {
		
		pdProductStockDao.updatePdState(pdProductStock);
	}
	
	public PdProductStock getOneStock(PdProductStock pdProductStock){
		return pdProductStockDao.getOneStock(pdProductStock);
	}
	
	/**
	 * 模糊查询(关联产品表)
	 * */
	public Page<PdProductStock> findListAndProduct(Page<PdProductStock> page, PdProductStock pdProductStock) {
		pdProductStock.setPage(page);
		page.setList(pdProductStockDao.findListAndProduct(pdProductStock));
		return page;
	}
	
	/**
	 * 耗材出库根据产品信息查询产品
	 * @param pdProductStock
	 * @return
	 */
	public List<PdProductStock> getProductInfoByOther(PdProductStock pdProductStock) {
		return pdProductStockDao.getProductInfoByOther(pdProductStock);
	}

	public List<PdProductStock> getByProductBarCodeNew(PdProductStock pdProductStock) {
		return pdProductStockDao.getByProductBarCodeNew(pdProductStock);
	}
}