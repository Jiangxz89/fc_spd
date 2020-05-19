/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdEncodingIdentifier;
import com.thinkgem.hys.pd.dao.PdEncodingIdentifierDao;

/**
 * 应用标识符表Service
 * @author zxh
 * @version 2019-04-19
 */
@Service
@Transactional(readOnly = true)
public class PdEncodingIdentifierService extends CrudService<PdEncodingIdentifierDao, PdEncodingIdentifier> {

	public PdEncodingIdentifier get(String id) {
		return super.get(id);
	}
	
	public List<PdEncodingIdentifier> findList(PdEncodingIdentifier pdEncodingIdentifier) {
		return super.findList(pdEncodingIdentifier);
	}
	
	public Page<PdEncodingIdentifier> findPage(Page<PdEncodingIdentifier> page, PdEncodingIdentifier pdEncodingIdentifier) {
		return super.findPage(page, pdEncodingIdentifier);
	}
	
	@Transactional(readOnly = false)
	public void save(PdEncodingIdentifier pdEncodingIdentifier) {
		super.save(pdEncodingIdentifier);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdEncodingIdentifier pdEncodingIdentifier) {
		super.delete(pdEncodingIdentifier);
	}
	
}