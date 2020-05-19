/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataDetailpanDao;
import com.thinkgem.hys.pd.entity.DataDetailpan;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品盘点单明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataDetailpanService extends CrudService<DataDetailpanDao, DataDetailpan> {

	public DataDetailpan get(String id) {
		return super.get(id);
	}
	
	public List<DataDetailpan> findList(DataDetailpan dataDetailpan) {
		return super.findList(dataDetailpan);
	}
	
	public Page<DataDetailpan> findPage(Page<DataDetailpan> page, DataDetailpan dataDetailpan) {
		return super.findPage(page, dataDetailpan);
	}
	
	@Transactional(readOnly = false)
	public void save(DataDetailpan dataDetailpan) {
		super.save(dataDetailpan);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataDetailpan dataDetailpan) {
		super.delete(dataDetailpan);
	}
	
}