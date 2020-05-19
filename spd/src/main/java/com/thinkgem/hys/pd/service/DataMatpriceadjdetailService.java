/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMatpriceadjdetailDao;
import com.thinkgem.hys.pd.entity.DataMatpriceadjdetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品调价单明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMatpriceadjdetailService extends CrudService<DataMatpriceadjdetailDao, DataMatpriceadjdetail> {

	public DataMatpriceadjdetail get(String id) {
		return super.get(id);
	}
	
	public List<DataMatpriceadjdetail> findList(DataMatpriceadjdetail dataMatpriceadjdetail) {
		return super.findList(dataMatpriceadjdetail);
	}
	
	public Page<DataMatpriceadjdetail> findPage(Page<DataMatpriceadjdetail> page, DataMatpriceadjdetail dataMatpriceadjdetail) {
		return super.findPage(page, dataMatpriceadjdetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMatpriceadjdetail dataMatpriceadjdetail) {
		super.save(dataMatpriceadjdetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMatpriceadjdetail dataMatpriceadjdetail) {
		super.delete(dataMatpriceadjdetail);
	}
	
}