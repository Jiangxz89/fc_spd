/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import com.thinkgem.hys.pd.entity.MedstoYprkmx;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.dao.MedstoYprkmxDao;

/**
 * 药库入库明细Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYprkmxService extends CrudService<MedstoYprkmxDao, MedstoYprkmx> {

	public MedstoYprkmx get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYprkmx> findList(MedstoYprkmx medstoYprkmx) {
		return super.findList(medstoYprkmx);
	}
	
	public Page<MedstoYprkmx> findPage(Page<MedstoYprkmx> page, MedstoYprkmx medstoYprkmx) {
		return super.findPage(page, medstoYprkmx);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYprkmx medstoYprkmx) {
		super.save(medstoYprkmx);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYprkmx medstoYprkmx) {
		super.delete(medstoYprkmx);
	}
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYprkmx> list) {
		return dao.batchInsert(list);
	}
	
}