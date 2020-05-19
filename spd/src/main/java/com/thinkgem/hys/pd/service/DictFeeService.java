/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DictFeeDao;
import com.thinkgem.hys.pd.entity.DictFee;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 中心收费项目目录Service
 * @author zxh
 * @version 2019-11-13
 */
@Service
@Transactional(readOnly = true)
public class DictFeeService extends CrudService<DictFeeDao, DictFee> {

	public DictFee get(String id) {
		return super.get(id);
	}
	
	public List<DictFee> findList(DictFee dictFee) {
		return super.findList(dictFee);
	}
	
	public Page<DictFee> findPage(Page<DictFee> page, DictFee dictFee) {
		return super.findPage(page, dictFee);
	}
	
	@Transactional(readOnly = false)
	public void save(DictFee dictFee) {
		super.save(dictFee);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictFee dictFee) {
		super.delete(dictFee);
	}
	
}