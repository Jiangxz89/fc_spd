/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DictMaterialDao;
import com.thinkgem.hys.pd.entity.DictMaterial;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 中心物资目录Service
 * @author zxh
 * @version 2019-11-13
 */
@Service
@Transactional(readOnly = true)
public class DictMaterialService extends CrudService<DictMaterialDao, DictMaterial> {

	public DictMaterial get(String id) {
		return super.get(id);
	}
	
	public List<DictMaterial> findList(DictMaterial dictMaterial) {
		return super.findList(dictMaterial);
	}
	
	public Page<DictMaterial> findPage(Page<DictMaterial> page, DictMaterial dictMaterial) {
		return super.findPage(page, dictMaterial);
	}
	
	@Transactional(readOnly = false)
	public void save(DictMaterial dictMaterial) {
		super.save(dictMaterial);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictMaterial dictMaterial) {
		super.delete(dictMaterial);
	}
	
}