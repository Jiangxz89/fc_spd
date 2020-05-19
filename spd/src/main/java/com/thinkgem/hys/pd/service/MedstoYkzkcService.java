/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYkzkc;
import com.thinkgem.hys.pd.dao.MedstoYkzkcDao;

/**
 * 药库总库存Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYkzkcService extends CrudService<MedstoYkzkcDao, MedstoYkzkc> {

	public MedstoYkzkc get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYkzkc> findList(MedstoYkzkc medstoYkzkc) {
		return super.findList(medstoYkzkc);
	}
	
	public Page<MedstoYkzkc> findPage(Page<MedstoYkzkc> page, MedstoYkzkc medstoYkzkc) {
		return super.findPage(page, medstoYkzkc);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYkzkc medstoYkzkc) {
		super.save(medstoYkzkc);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYkzkc medstoYkzkc) {
		super.delete(medstoYkzkc);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYkzkc> list) {
		return dao.batchInsert(list);
	}
	
	@Transactional(readOnly = false)
	public int clearData() {
		return dao.clearData();
	}
	
}