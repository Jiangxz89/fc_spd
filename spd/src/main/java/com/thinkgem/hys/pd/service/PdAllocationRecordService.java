/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdAllocationRecord;
import com.thinkgem.hys.pd.dao.PdAllocationRecordDao;

/**
 * 调拨记录Service
 * @author changjundong
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class PdAllocationRecordService extends CrudService<PdAllocationRecordDao, PdAllocationRecord> {

	@Autowired
	PdAllocationRecordDao pdAllocationRecordDao;
	public PdAllocationRecord get(String id) {
		return super.get(id);
	}
	
	public List<PdAllocationRecord> findList(PdAllocationRecord pdAllocationRecord) {
		return super.findList(pdAllocationRecord);
	}
	
	public Page<PdAllocationRecord> findPage(Page<PdAllocationRecord> page, PdAllocationRecord pdAllocationRecord) {
		return super.findPage(page, pdAllocationRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PdAllocationRecord pdAllocationRecord) {
		super.save(pdAllocationRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdAllocationRecord pdAllocationRecord) {
		super.delete(pdAllocationRecord);
	}	
	
}