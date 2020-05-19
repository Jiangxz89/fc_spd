/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdPackageEntityChild;
import com.thinkgem.hys.pd.dao.PdPackageEntityChildDao;

/**
 * 实体包实体产品表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdPackageEntityChildService extends CrudService<PdPackageEntityChildDao, PdPackageEntityChild> {

	public PdPackageEntityChild get(String id) {
		return super.get(id);
	}
	
	public List<PdPackageEntityChild> findList(PdPackageEntityChild pdPackageEntityChild) {
		return super.findList(pdPackageEntityChild);
	}
	
	public Page<PdPackageEntityChild> findPage(Page<PdPackageEntityChild> page, PdPackageEntityChild pdPackageEntityChild) {
		return super.findPage(page, pdPackageEntityChild);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPackageEntityChild pdPackageEntityChild) {
		super.save(pdPackageEntityChild);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPackageEntityChild pdPackageEntityChild) {
		super.delete(pdPackageEntityChild);
	}
	
}