/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdDosagertDetail;
import com.thinkgem.hys.pd.dao.PdDosagertDetailDao;

/**
 * 用量退回产品信息Service
 * @author yueguoyun
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class PdDosagertDetailService extends CrudService<PdDosagertDetailDao, PdDosagertDetail> {

	public PdDosagertDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdDosagertDetail> findList(PdDosagertDetail pdDosagertDetail) {
		return super.findList(pdDosagertDetail);
	}
	
	public Page<PdDosagertDetail> findPage(Page<PdDosagertDetail> page, PdDosagertDetail pdDosagertDetail) {
		return super.findPage(page, pdDosagertDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdDosagertDetail pdDosagertDetail) {
		super.save(pdDosagertDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdDosagertDetail pdDosagertDetail) {
		super.delete(pdDosagertDetail);
	}
	
}