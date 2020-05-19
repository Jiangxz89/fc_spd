/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdRejectedDetail;
import com.thinkgem.hys.pd.dao.PdRejectedDetailDao;

/**
 * 退货明细信息Service
 * @author yueguoyun
 * @version 2018-04-29
 */
@Service
@Transactional(readOnly = true)
public class PdRejectedDetailService extends CrudService<PdRejectedDetailDao, PdRejectedDetail> {

	public PdRejectedDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdRejectedDetail> findList(PdRejectedDetail pdRejectedDetail) {
		return super.findList(pdRejectedDetail);
	}
	
	public Page<PdRejectedDetail> findPage(Page<PdRejectedDetail> page, PdRejectedDetail pdRejectedDetail) {
		return super.findPage(page, pdRejectedDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdRejectedDetail pdRejectedDetail) {
		super.save(pdRejectedDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdRejectedDetail pdRejectedDetail) {
		super.delete(pdRejectedDetail);
	}
	
}