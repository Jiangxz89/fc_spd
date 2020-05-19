/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYplcmlk;
import com.thinkgem.hys.pd.dao.MedstoYplcmlkDao;

/**
 * 药品临床目录库Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYplcmlkService extends CrudService<MedstoYplcmlkDao, MedstoYplcmlk> {

	public MedstoYplcmlk get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYplcmlk> findList(MedstoYplcmlk medstoYplcmlk) {
		return super.findList(medstoYplcmlk);
	}
	
	public Page<MedstoYplcmlk> findPage(Page<MedstoYplcmlk> page, MedstoYplcmlk medstoYplcmlk) {
		return super.findPage(page, medstoYplcmlk);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYplcmlk medstoYplcmlk) {
		super.save(medstoYplcmlk);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYplcmlk medstoYplcmlk) {
		super.delete(medstoYplcmlk);
	}
	
}