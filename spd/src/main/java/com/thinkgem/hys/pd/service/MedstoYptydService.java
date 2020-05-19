/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.MedstoYptyd;
import com.thinkgem.hys.pd.dao.MedstoYptydDao;

/**
 * 医院药品退药单表Service
 * @author zxh
 * @version 2019-05-09
 */
@Service
@Transactional(readOnly = true)
public class MedstoYptydService extends CrudService<MedstoYptydDao, MedstoYptyd> {

	public MedstoYptyd get(String id) {
		return super.get(id);
	}
	
	public List<MedstoYptyd> findList(MedstoYptyd medstoYptyd) {
		return super.findList(medstoYptyd);
	}
	
	public Page<MedstoYptyd> findPage(Page<MedstoYptyd> page, MedstoYptyd medstoYptyd) {
		return super.findPage(page, medstoYptyd);
	}
	
	@Transactional(readOnly = false)
	public void save(MedstoYptyd medstoYptyd) {
		super.save(medstoYptyd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MedstoYptyd medstoYptyd) {
		super.delete(medstoYptyd);
	}

	/**
	 * 药品退药单查询
	 * @param page
	 * @param medstoYptyd
	 * @return
	 */
    public Page<MedstoYptyd> findDrugReturnBillDetaile(Page<MedstoYptyd> page, MedstoYptyd medstoYptyd) {
		medstoYptyd.setPage(page);
		page.setList(dao.findDrugReturnBillDetaile(medstoYptyd));
		return page;
    }

	/**
	 * 药品退药单查询
	 * @param medstoYptyd
	 * @return
	 */
	public List<MedstoYptyd> findDrugReturnBillDetaile(MedstoYptyd medstoYptyd) {
		return dao.findDrugReturnBillDetaile(medstoYptyd);
	}
}