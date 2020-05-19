/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYppdd;
import com.thinkgem.hys.pd.dao.MedstoYppddDao;

/**
 * 药品盘点表Service
 * @author zxh
 * @version 2019-05-08
 */
@Service
@Transactional(readOnly = true)
public class MedstoYppddService extends CrudService<MedstoYppddDao, MedstoYppdd> {

	public MedstoYppdd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYppdd> findList(MedstoYppdd medstoYppdd) {
		return super.findList(medstoYppdd);
	}
	
	public Page<MedstoYppdd> findPage(Page<MedstoYppdd> page, MedstoYppdd medstoYppdd) {
		return super.findPage(page, medstoYppdd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYppdd medstoYppdd) {
		super.save(medstoYppdd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYppdd medstoYppdd) {
		super.delete(medstoYppdd);
	}

	/**
	 * 药品盘点单明细
	 * @param page
	 * @param medstoYppdd
	 * @return
	 */
    public Page<MedstoYppdd> findDrugInventoryDetailed(Page<MedstoYppdd> page, MedstoYppdd medstoYppdd) {
		medstoYppdd.setPage(page);
		page.setList(dao.findDrugInventoryDetailed(medstoYppdd));
		return page;
    }

	/**
	 * 药品盘点单明细
	 * @param medstoYppdd
	 * @return
	 */
	public List<MedstoYppdd> findDrugInventoryDetailed(MedstoYppdd medstoYppdd) {
		return dao.findDrugInventoryDetailed(medstoYppdd);
	}
}