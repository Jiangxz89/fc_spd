/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdApplyDetail;
import com.thinkgem.hys.pd.dao.PdApplyDetailDao;

/**
 * 申领单详细Service
 * @author wg
 * @version 2018-03-06
 */
@Service
@Transactional(readOnly = true)
public class PdApplyDetailService extends CrudService<PdApplyDetailDao, PdApplyDetail> {

	public PdApplyDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdApplyDetail> findList(PdApplyDetail pdApplyDetail) {
		return super.findList(pdApplyDetail);
	}
	
	public Page<PdApplyDetail> findPage(Page<PdApplyDetail> page, PdApplyDetail pdApplyDetail) {
		return super.findPage(page, pdApplyDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdApplyDetail pdApplyDetail) {
		super.save(pdApplyDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdApplyDetail pdApplyDetail) {
		super.delete(pdApplyDetail);
	}
	
}