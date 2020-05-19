/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.hys.pd.dao.PdHisSyncDao;
import com.thinkgem.hys.pd.entity.PdHisSync;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * HIS数据同步状态表Service
 * @author wg
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly = true)
public class PdHisSyncService extends CrudService<PdHisSyncDao, PdHisSync> {

	public PdHisSync get(String id) {
		return super.get(id);
	}
	
	public List<PdHisSync> findList(PdHisSync pdHisSync) {
		return super.findList(pdHisSync);
	}
	
	public Page<PdHisSync> findPage(Page<PdHisSync> page, PdHisSync pdHisSync) {
		return super.findPage(page, pdHisSync);
	}
	
	@Transactional(readOnly = false)
	public void save(PdHisSync pdHisSync) {
		super.save(pdHisSync);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdHisSync pdHisSync) {
		super.delete(pdHisSync);
	}
	
	@Transactional(readOnly = false)
	public void insertRecord(PdHisSync pdHisSync) {
		List<PdHisSync> list = super.findList(pdHisSync);
		if (list != null && list.size()>0) {
			super.delete(list.get(0));
		}
		super.save(pdHisSync);
	}
}