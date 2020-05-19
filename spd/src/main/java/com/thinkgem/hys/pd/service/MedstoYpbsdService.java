/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYpbsd;
import com.thinkgem.hys.pd.dao.MedstoYpbsdDao;

/**
 * 医院药品报损丢失单Service
 * @author zxh
 * @version 2019-05-09
 */
@Service
@Transactional(readOnly = true)
public class MedstoYpbsdService extends CrudService<MedstoYpbsdDao, MedstoYpbsd> {

	public MedstoYpbsd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYpbsd> findList(MedstoYpbsd medstoYpbsd) {
		return super.findList(medstoYpbsd);
	}
	
	public Page<MedstoYpbsd> findPage(Page<MedstoYpbsd> page, MedstoYpbsd medstoYpbsd) {
		return super.findPage(page, medstoYpbsd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYpbsd medstoYpbsd) {
		super.save(medstoYpbsd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYpbsd medstoYpbsd) {
		super.delete(medstoYpbsd);
	}

	/**
	 * 药品丢失单明细
	 * @param page
	 * @param medstoYpbsd
	 * @return
	 */
    public Page<MedstoYpbsd> findDrugLostOrderDetaile(Page<MedstoYpbsd> page, MedstoYpbsd medstoYpbsd) {
		medstoYpbsd.setPage(page);
		page.setList(dao.findDrugLostOrderDetaile(medstoYpbsd));
		return page;
    }

	/**
	 * 药品丢失单明细
	 * @param medstoYpbsd
	 * @return
	 */
	public List<MedstoYpbsd> findDrugLostOrderDetaile(MedstoYpbsd medstoYpbsd) {
		return dao.findDrugLostOrderDetaile(medstoYpbsd);
	}
}