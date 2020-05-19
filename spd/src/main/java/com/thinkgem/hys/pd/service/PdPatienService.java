/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.PdPatienDao;
import com.thinkgem.hys.pd.entity.PdPatien;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * pdPatienService
 * @author jiangxz
 * @version 2019-10-08
 */
@Service
@Transactional(readOnly = true)
public class PdPatienService extends CrudService<PdPatienDao, PdPatien> {

	@Autowired
	PdPatienDao pdPatienDao;

	public PdPatien get(String id) {
		return super.get(id);
	}
	
	public List<PdPatien> findList(PdPatien pdPatien) {
		return super.findList(pdPatien);
	}
	
	public Page<PdPatien> findPage(Page<PdPatien> page, PdPatien pdPatien) {
		return super.findPage(page, pdPatien);
	}
	
	@Transactional(readOnly = false)
	public void save(PdPatien pdPatien) {
		super.save(pdPatien);
	}

	@Transactional(readOnly = false)
	public void update(PdPatien pdPatien) {
		pdPatien.preUpdate();
		pdPatienDao.update(pdPatien);
	}

	@Transactional(readOnly = false)
	public void delete(PdPatien pdPatien) {
		super.delete(pdPatien);
	}
	
}