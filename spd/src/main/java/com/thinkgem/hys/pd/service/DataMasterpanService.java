/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMasterpanDao;
import com.thinkgem.hys.pd.entity.DataMasterpan;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品盘点单Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMasterpanService extends CrudService<DataMasterpanDao, DataMasterpan> {

	public DataMasterpan get(String id) {
		return super.get(id);
	}
	
	public List<DataMasterpan> findList(DataMasterpan dataMasterpan) {
		return super.findList(dataMasterpan);
	}
	
	public Page<DataMasterpan> findPage(Page<DataMasterpan> page, DataMasterpan dataMasterpan) {
		return super.findPage(page, dataMasterpan);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMasterpan dataMasterpan) {
		super.save(dataMasterpan);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMasterpan dataMasterpan) {
		super.delete(dataMasterpan);
	}
	
}