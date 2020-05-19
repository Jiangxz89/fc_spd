/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdDrugSupplier;
import com.thinkgem.hys.pd.dao.PdDrugSupplierDao;

/**
 * 药品供应商表Service
 * @author wg
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly = true)
public class PdDrugSupplierService extends CrudService<PdDrugSupplierDao, PdDrugSupplier> {

	public PdDrugSupplier get(String id) {
		return super.get(id);
	}
	
	public List<PdDrugSupplier> findList(PdDrugSupplier pdDrugSupplier) {
		return super.findList(pdDrugSupplier);
	}
	
	public Page<PdDrugSupplier> findPage(Page<PdDrugSupplier> page, PdDrugSupplier pdDrugSupplier) {
		return super.findPage(page, pdDrugSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(PdDrugSupplier pdDrugSupplier) {
		super.save(pdDrugSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdDrugSupplier pdDrugSupplier) {
		super.delete(pdDrugSupplier);
	}
	
	public PdDrugSupplier getOne(String ypdm, String type) {
		return dao.getOne(ypdm, type);
	}
}