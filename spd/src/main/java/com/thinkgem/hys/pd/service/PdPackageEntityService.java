/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdPackageEntity;
import com.thinkgem.hys.pd.dao.PdPackageEntityDao;

/**
 * 实体包表Service
 * @author sutianqi
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdPackageEntityService extends CrudService<PdPackageEntityDao, PdPackageEntity> {

	public PdPackageEntity get(String id) {
		return super.get(id);
	}
	
	public List<PdPackageEntity> findList(PdPackageEntity pdPackageEntity) {
		return super.findList(pdPackageEntity);
	}
	
	public Page<PdPackageEntity> findPage(Page<PdPackageEntity> page, PdPackageEntity pdPackageEntity) {
		return super.findPage(page, pdPackageEntity);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPackageEntity pdPackageEntity) {
		super.save(pdPackageEntity);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdPackageEntity pdPackageEntity) {
		super.delete(pdPackageEntity);
	}
	
}