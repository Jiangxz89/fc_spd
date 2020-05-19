/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpml;
import com.thinkgem.hys.pd.dao.MedstoYpmlDao;

/**
 * 药品目录表Service
 * @author zxh
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpmlService extends CrudService<MedstoYpmlDao, MedstoYpml> {

	public MedstoYpml get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpml> findList(MedstoYpml medstoYpml) {
		return super.findList(medstoYpml);
	}
	
	public Page<MedstoYpml> findPage(Page<MedstoYpml> page, MedstoYpml medstoYpml) {
		return super.findPage(page, medstoYpml);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpml medstoYpml) {
		super.save(medstoYpml);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpml medstoYpml) {
		super.delete(medstoYpml);
	}
	
}