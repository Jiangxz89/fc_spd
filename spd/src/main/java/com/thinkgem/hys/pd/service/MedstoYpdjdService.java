/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpdjd;
import com.thinkgem.hys.pd.dao.MedstoYpdjdDao;

/**
 * 药品调价单表Service
 * @author zxh
 * @version 2019-05-08
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpdjdService extends CrudService<MedstoYpdjdDao, MedstoYpdjd> {

	public MedstoYpdjd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpdjd> findList(MedstoYpdjd medstoYpdjd) {
		return super.findList(medstoYpdjd);
	}
	
	public Page<MedstoYpdjd> findPage(Page<MedstoYpdjd> page, MedstoYpdjd medstoYpdjd) {
		return super.findPage(page, medstoYpdjd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpdjd medstoYpdjd) {
		super.save(medstoYpdjd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpdjd medstoYpdjd) {
		super.delete(medstoYpdjd);
	}

	/**
	 * 药品调价单明细
	 * @param page
	 * @param medstoYpdjd
	 * @return
	 */
    public Page<MedstoYpdjd> findDrugPriceAdjustment(Page<MedstoYpdjd> page, MedstoYpdjd medstoYpdjd) {
		medstoYpdjd.setPage(page);
		page.setList(dao.findDrugPriceAdjustment(medstoYpdjd));
		return page;
    }

	/**
	 * 药品调价单明细
	 * @param medstoYpdjd
	 * @return
	 */
	public List<MedstoYpdjd> findDrugPriceAdjustment(MedstoYpdjd medstoYpdjd) {
		return dao.findDrugPriceAdjustment(medstoYpdjd);
	}
}