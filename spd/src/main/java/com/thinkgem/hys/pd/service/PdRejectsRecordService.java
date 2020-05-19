/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdRejectsRecord;
import com.thinkgem.hys.pd.dao.PdRejectsRecordDao;

/**
 * 不良品记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdRejectsRecordService extends CrudService<PdRejectsRecordDao, PdRejectsRecord> {

	public PdRejectsRecord get(String id) {
		return super.get(id);
	}
	
	public List<PdRejectsRecord> findList(PdRejectsRecord pdRejectsRecord) {
		return super.findList(pdRejectsRecord);
	}
	
	public Page<PdRejectsRecord> findPage(Page<PdRejectsRecord> page, PdRejectsRecord pdRejectsRecord) {
		return super.findPage(page, pdRejectsRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRejectsRecord pdRejectsRecord) {
		super.save(pdRejectsRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRejectsRecord pdRejectsRecord) {
		super.delete(pdRejectsRecord);
	}
	
}