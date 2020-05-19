/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpthmx;
import com.thinkgem.hys.pd.dao.MedstoYpthmxDao;

/**
 * 药品退货明细Service
 * @author wg
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpthmxService extends CrudService<MedstoYpthmxDao, MedstoYpthmx> {

	public MedstoYpthmx get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpthmx> findList(MedstoYpthmx medstoYpthmx) {
		return super.findList(medstoYpthmx);
	}
	
	public Page<MedstoYpthmx> findPage(Page<MedstoYpthmx> page, MedstoYpthmx medstoYpthmx) {
		return super.findPage(page, medstoYpthmx);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpthmx medstoYpthmx) {
		super.save(medstoYpthmx);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpthmx medstoYpthmx) {
		super.delete(medstoYpthmx);
	}
	
	@Transactional(readOnly = false)
	public int batchInsert(List<MedstoYpthmx> list) {
		return dao.batchInsert(list);
	}
	
}