/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DictMedicineDao;
import com.thinkgem.hys.pd.entity.DictMedicine;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 中心药品目录Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DictMedicineService extends CrudService<DictMedicineDao, DictMedicine> {

	public DictMedicine get(String id) {
		return super.get(id);
	}
	
	public List<DictMedicine> findList(DictMedicine dictMedicine) {
		return super.findList(dictMedicine);
	}
	
	public Page<DictMedicine> findPage(Page<DictMedicine> page, DictMedicine dictMedicine) {
		return super.findPage(page, dictMedicine);
	}
	
	@Transactional(readOnly = false)
	public void save(DictMedicine dictMedicine) {
		super.save(dictMedicine);
	}
	
	@Transactional(readOnly = false)
	public void delete(DictMedicine dictMedicine) {
		super.delete(dictMedicine);
	}
	
}