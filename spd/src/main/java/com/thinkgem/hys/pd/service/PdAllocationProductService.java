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
import com.thinkgem.hys.pd.entity.PdAllocationProduct;
import com.thinkgem.hys.pd.dao.PdAllocationProductDao;

/**
 * 调拨产品中间表Service
 * @author zhengjinlei
 * @version 2018-03-24
 */
@Service
@Transactional(readOnly = true)
public class PdAllocationProductService extends CrudService<PdAllocationProductDao, PdAllocationProduct> {
	
	public PdAllocationProduct get(String id) {
		return super.get(id);
	}
	
	public List<PdAllocationProduct> findList(PdAllocationProduct pdAllocationProduct) {
		return super.findList(pdAllocationProduct);
	}
	
	public Page<PdAllocationProduct> findPage(Page<PdAllocationProduct> page, PdAllocationProduct pdAllocationProduct) {
		return super.findPage(page, pdAllocationProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PdAllocationProduct pdAllocationProduct) {
		super.save(pdAllocationProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdAllocationProduct pdAllocationProduct) {
		super.delete(pdAllocationProduct);
	}

	@Transactional(readOnly = false)
	public void addAllocationProductBatch(List<PdAllocationProduct> pdAllocationProductList) {
		dao.addAllocationProductBatch(pdAllocationProductList);
	}
	
}