/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMatpriceadjDao;
import com.thinkgem.hys.pd.entity.DataMatpriceadj;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品调价单Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMatpriceadjService extends CrudService<DataMatpriceadjDao, DataMatpriceadj> {

	public DataMatpriceadj get(String id) {
		return super.get(id);
	}
	
	public List<DataMatpriceadj> findList(DataMatpriceadj dataMatpriceadj) {
		return super.findList(dataMatpriceadj);
	}
	
	public Page<DataMatpriceadj> findPage(Page<DataMatpriceadj> page, DataMatpriceadj dataMatpriceadj) {
		return super.findPage(page, dataMatpriceadj);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMatpriceadj dataMatpriceadj) {
		super.save(dataMatpriceadj);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMatpriceadj dataMatpriceadj) {
		super.delete(dataMatpriceadj);
	}
	
}