/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrolRecord;
import com.thinkgem.hys.pd.dao.PdStoreroomPatrolRecordDao;

/**
 * 库房巡查记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdStoreroomPatrolRecordService extends CrudService<PdStoreroomPatrolRecordDao, PdStoreroomPatrolRecord> {

	public PdStoreroomPatrolRecord get(String id) {
		return super.get(id);
	}
	
	public List<PdStoreroomPatrolRecord> findList(PdStoreroomPatrolRecord pdStoreroomPatrolRecord) {
		return super.findList(pdStoreroomPatrolRecord);
	}
	
	public Page<PdStoreroomPatrolRecord> findPage(Page<PdStoreroomPatrolRecord> page, PdStoreroomPatrolRecord pdStoreroomPatrolRecord) {
		return super.findPage(page, pdStoreroomPatrolRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PdStoreroomPatrolRecord pdStoreroomPatrolRecord) {
		super.save(pdStoreroomPatrolRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdStoreroomPatrolRecord pdStoreroomPatrolRecord) {
		super.delete(pdStoreroomPatrolRecord);
	}
	
}