/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMedmasterDao;
import com.thinkgem.hys.pd.entity.DataMedmaster;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品入库主记录Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMedmasterService extends CrudService<DataMedmasterDao, DataMedmaster> {

	public DataMedmaster get(String id) {
		return super.get(id);
	}
	
	public List<DataMedmaster> findList(DataMedmaster dataMedmaster) {
		return super.findList(dataMedmaster);
	}
	
	public Page<DataMedmaster> findPage(Page<DataMedmaster> page, DataMedmaster dataMedmaster) {
		return super.findPage(page, dataMedmaster);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMedmaster dataMedmaster) {
		super.save(dataMedmaster);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMedmaster dataMedmaster) {
		super.delete(dataMedmaster);
	}
	
}