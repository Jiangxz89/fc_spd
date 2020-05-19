/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpcgmx;
import com.thinkgem.hys.pd.dao.MedstoYpcgmxDao;

/**
 * 药库采购明细Service
 * @author sutianqi
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpcgmxService extends CrudService<MedstoYpcgmxDao, MedstoYpcgmx> {

	public MedstoYpcgmx get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpcgmx> findList(MedstoYpcgmx medstoYpcgmx) {
		return super.findList(medstoYpcgmx);
	}
	
	public Page<MedstoYpcgmx> findPage(Page<MedstoYpcgmx> page, MedstoYpcgmx medstoYpcgmx) {
		return super.findPage(page, medstoYpcgmx);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpcgmx medstoYpcgmx) {
		super.save(medstoYpcgmx);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpcgmx medstoYpcgmx) {
		super.delete(medstoYpcgmx);
	}
	
}