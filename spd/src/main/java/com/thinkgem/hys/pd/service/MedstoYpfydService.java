/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpfyd;
import com.thinkgem.hys.pd.dao.MedstoYpfydDao;

/**
 * 药房发药单表Service
 * @author zxh
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpfydService extends CrudService<MedstoYpfydDao, MedstoYpfyd> {

	public MedstoYpfyd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpfyd> findList(MedstoYpfyd medstoYpfyd) {
		return super.findList(medstoYpfyd);
	}
	
	public Page<MedstoYpfyd> findPage(Page<MedstoYpfyd> page, MedstoYpfyd medstoYpfyd) {
		return super.findPage(page, medstoYpfyd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpfyd medstoYpfyd) {
		super.save(medstoYpfyd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpfyd medstoYpfyd) {
		super.delete(medstoYpfyd);
	}

	/**
	 * 发药单详情
	 * @param page
	 * @param medstoYpfyd
	 * @return
	 */
	public Page<MedstoYpfyd> findDispensingListDetail(Page<MedstoYpfyd> page, MedstoYpfyd medstoYpfyd) {
		medstoYpfyd.setPage(page);
		page.setList(dao.findDispensingListDetail(medstoYpfyd));
		return page;
	}


	public List<MedstoYpfyd> findDispensingListDetail(MedstoYpfyd medstoYpfyd) {
		return dao.findDispensingListDetail(medstoYpfyd);
	}
}