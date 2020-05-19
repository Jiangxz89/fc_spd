/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdRejectedListChild;
import com.thinkgem.hys.pd.dao.PdRejectedListChildDao;

/**
 * 退货单子单表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdRejectedListChildService extends CrudService<PdRejectedListChildDao, PdRejectedListChild> {

	public PdRejectedListChild get(String id) {
		return super.get(id);
	}
	
	public List<PdRejectedListChild> findList(PdRejectedListChild pdRejectedListChild) {
		return super.findList(pdRejectedListChild);
	}
	
	public Page<PdRejectedListChild> findPage(Page<PdRejectedListChild> page, PdRejectedListChild pdRejectedListChild) {
		return super.findPage(page, pdRejectedListChild);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRejectedListChild pdRejectedListChild) {
		super.save(pdRejectedListChild);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRejectedListChild pdRejectedListChild) {
		super.delete(pdRejectedListChild);
	}
	
}