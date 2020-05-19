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
import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.hys.pd.dao.PdStockRecordProductDao;

/**
 * 出入库记录关联产品记录Service
 * @author changjundong
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class PdStockRecordProductService extends CrudService<PdStockRecordProductDao, PdStockRecordProduct> {
	@Autowired
	PdStockRecordProductDao pdStockRecordProductDao;
	public PdStockRecordProduct get(String id) {
		return super.get(id);
	}
	
	public List<PdStockRecordProduct> findList(PdStockRecordProduct pdStockRecordProduct) {
		return super.findList(pdStockRecordProduct);
	}
	
	public Page<PdStockRecordProduct> findPage(Page<PdStockRecordProduct> page, PdStockRecordProduct pdStockRecordProduct) {
		return super.findPage(page, pdStockRecordProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStockRecordProduct pdStockRecordProduct) {
		super.save(pdStockRecordProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStockRecordProduct pdStockRecordProduct) {
		super.delete(pdStockRecordProduct);
	}

	public List<PdStockRecordProduct> findPdList(PdStockRecordProduct pdStockRecordProduct) {
		// TODO Auto-generated method stub
		return pdStockRecordProductDao.findPdList(pdStockRecordProduct);
	}

	public Page<PdStockRecordProduct> stockInAndOutRecordDetailQueryForPage(Page<PdStockRecordProduct> page, PdStockRecordProduct pdStockRecordProduct) {
		pdStockRecordProduct.setPage(page);
		page.setList(dao.stockInAndOutRecordDetailQuery(pdStockRecordProduct));
		return page;
	}

	public List<PdStockRecordProduct> stockInAndOutRecordDetailQuery(PdStockRecordProduct pdStockRecordProduct) {
		return dao.stockInAndOutRecordDetailQuery(pdStockRecordProduct);
	}
}