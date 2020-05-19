/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdSupplierCard;
import com.thinkgem.hys.pd.dao.PdSupplierCardDao;

/**
 * 供应商证件信息Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdSupplierCardService extends CrudService<PdSupplierCardDao, PdSupplierCard> {

	public PdSupplierCard get(String id) {
		return super.get(id);
	}
	
	public List<PdSupplierCard> findList(PdSupplierCard pdSupplierCard) {
		return super.findList(pdSupplierCard);
	}
	
	public Page<PdSupplierCard> findPage(Page<PdSupplierCard> page, PdSupplierCard pdSupplierCard) {
		return super.findPage(page, pdSupplierCard);
	}
	
	@Transactional(readOnly = false)
	public void save(PdSupplierCard pdSupplierCard) {
		super.save(pdSupplierCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdSupplierCard pdSupplierCard) {
		super.delete(pdSupplierCard);
	}
	
}