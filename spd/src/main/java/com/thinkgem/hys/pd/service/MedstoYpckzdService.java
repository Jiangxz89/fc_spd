/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpckzd;
import com.thinkgem.hys.pd.dao.MedstoYpckzdDao;

/**
 * 药品出库单Service
 * @author zxh
 * @version 2019-05-07
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpckzdService extends CrudService<MedstoYpckzdDao, MedstoYpckzd> {

	public MedstoYpckzd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpckzd> findList(MedstoYpckzd medstoYpckzd) {
		return super.findList(medstoYpckzd);
	}
	
	public Page<MedstoYpckzd> findPage(Page<MedstoYpckzd> page, MedstoYpckzd medstoYpckzd) {
		return super.findPage(page, medstoYpckzd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpckzd medstoYpckzd) {
		super.save(medstoYpckzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpckzd medstoYpckzd) {
		super.delete(medstoYpckzd);
	}

	/**
	 * 药品出库明细查询
	 * @param medstoYpckzd
	 * @return
	 */
    public List<MedstoYpckzd> findDrugOutDetaile(MedstoYpckzd medstoYpckzd) {
		return dao.findDrugOutDetaile(medstoYpckzd);
    }

	/**
	 * 药品出库明细查询
	 * @param page
	 * @param medstoYpckzd
	 * @return
	 */
	public Page<MedstoYpckzd> findDrugOutDetaile(Page<MedstoYpckzd> page, MedstoYpckzd medstoYpckzd) {
		medstoYpckzd.setPage(page);
		page.setList(dao.findDrugOutDetaile(medstoYpckzd));
		return page;
	}
}