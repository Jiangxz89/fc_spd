/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMeddetailDao;
import com.thinkgem.hys.pd.entity.DataMeddetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 医院药品退药单明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMeddetailService extends CrudService<DataMeddetailDao, DataMeddetail> {

	public DataMeddetail get(String id) {
		return super.get(id);
	}
	
	public List<DataMeddetail> findList(DataMeddetail dataMeddetail) {
		return super.findList(dataMeddetail);
	}
	
	public Page<DataMeddetail> findPage(Page<DataMeddetail> page, DataMeddetail dataMeddetail) {
		return super.findPage(page, dataMeddetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMeddetail dataMeddetail) {
		super.save(dataMeddetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMeddetail dataMeddetail) {
		super.delete(dataMeddetail);
	}
	
}