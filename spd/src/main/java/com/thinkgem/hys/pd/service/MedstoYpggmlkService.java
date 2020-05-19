/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpggmlk;
import com.thinkgem.hys.pd.dao.MedstoYpggmlkDao;

/**
 * 药品规格库Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpggmlkService extends CrudService<MedstoYpggmlkDao, MedstoYpggmlk> {

	public MedstoYpggmlk get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpggmlk> findList(MedstoYpggmlk medstoYpggmlk) {
		return super.findList(medstoYpggmlk);
	}
	
	public Page<MedstoYpggmlk> findPage(Page<MedstoYpggmlk> page, MedstoYpggmlk medstoYpggmlk) {
		return super.findPage(page, medstoYpggmlk);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpggmlk medstoYpggmlk) {
		super.save(medstoYpggmlk);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpggmlk medstoYpggmlk) {
		super.delete(medstoYpggmlk);
	}
	
}