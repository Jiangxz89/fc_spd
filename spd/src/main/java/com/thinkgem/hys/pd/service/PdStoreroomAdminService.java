/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.dao.PdStoreroomAdminDao;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 库房管理员信息Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStoreroomAdminService extends CrudService<PdStoreroomAdminDao, PdStoreroomAdmin> {
	@Autowired
	private PdStoreroomAdminDao pdStoreroomAdminDao;
	
	public PdStoreroomAdmin get(String id) {
		return super.get(id);
	}
	
	public List<PdStoreroomAdmin> findList(PdStoreroomAdmin pdStoreroomAdmin) {
		return super.findList(pdStoreroomAdmin);
	}
	
	public Page<PdStoreroomAdmin> findPage(Page<PdStoreroomAdmin> page, PdStoreroomAdmin pdStoreroomAdmin) {
		return super.findPage(page, pdStoreroomAdmin);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStoreroomAdmin pdStoreroomAdmin) {
		super.save(pdStoreroomAdmin);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStoreroomAdmin pdStoreroomAdmin) {
		super.delete(pdStoreroomAdmin);
	}
	
	//查询仓库管理员信息
	public List<PdStoreroomAdmin> findAdminList(PdStoreroomAdmin pdStoreroomAdmin) {
		return pdStoreroomAdminDao.findAdminList(pdStoreroomAdmin);
	}
}