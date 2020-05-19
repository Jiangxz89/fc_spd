/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdStockRecordInvoice;
import com.thinkgem.hys.pd.dao.PdStockRecordInvoiceDao;

/**
 * 出入库发票记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStockRecordInvoiceService extends CrudService<PdStockRecordInvoiceDao, PdStockRecordInvoice> {

	public PdStockRecordInvoice get(String id) {
		return super.get(id);
	}
	
	public List<PdStockRecordInvoice> findList(PdStockRecordInvoice pdStockRecordInvoice) {
		return super.findList(pdStockRecordInvoice);
	}
	
	public Page<PdStockRecordInvoice> findPage(Page<PdStockRecordInvoice> page, PdStockRecordInvoice pdStockRecordInvoice) {
		return super.findPage(page, pdStockRecordInvoice);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStockRecordInvoice pdStockRecordInvoice) {
		super.save(pdStockRecordInvoice);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStockRecordInvoice pdStockRecordInvoice) {
		super.delete(pdStockRecordInvoice);
	}
	
}