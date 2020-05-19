/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import com.thinkgem.hys.pd.dao.PdStockRecordDao;
import com.thinkgem.hys.pd.entity.PdStockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpkcmx;
import com.thinkgem.hys.pd.dao.MedstoYpkcmxDao;

/**
 * 药品库存明细信息Service
 * @author zengyanlong
 * @version 2019-04-19
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpkcmxService extends CrudService<MedstoYpkcmxDao, MedstoYpkcmx> {
	@Autowired
	private MedstoYpkcmxDao medstoYpkcmxDao;
	public MedstoYpkcmx get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpkcmx> findList(MedstoYpkcmx medstoYpkcmx) {
		return super.findList(medstoYpkcmx);
	}
	
	public Page<MedstoYpkcmx> findPage(Page<MedstoYpkcmx> page, MedstoYpkcmx medstoYpkcmx) {
		return super.findPage(page, medstoYpkcmx);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpkcmx medstoYpkcmx) {
		super.save(medstoYpkcmx);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpkcmx medstoYpkcmx) {
		super.delete(medstoYpkcmx);
	}

	public List<MedstoYpkcmx> findCensusQuery(MedstoYpkcmx medstoYpkcmx) {
		return medstoYpkcmxDao.findList(medstoYpkcmx);
	}
	
}