/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdStoreroomProduct;
import com.thinkgem.hys.pd.dao.PdStoreroomProductDao;

/**
 * 库房产品信息Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStoreroomProductService extends CrudService<PdStoreroomProductDao, PdStoreroomProduct> {

	public PdStoreroomProduct get(String id) {
		return super.get(id);
	}
	
	public List<PdStoreroomProduct> findList(PdStoreroomProduct pdStoreroomProduct) {
		return super.findList(pdStoreroomProduct);
	}
	
	public Page<PdStoreroomProduct> findPage(Page<PdStoreroomProduct> page, PdStoreroomProduct pdStoreroomProduct) {
		return super.findPage(page, pdStoreroomProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStoreroomProduct pdStoreroomProduct) {
		super.save(pdStoreroomProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStoreroomProduct pdStoreroomProduct) {
		super.delete(pdStoreroomProduct);
	}
	
}