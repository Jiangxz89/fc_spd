/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataYfexedetailDao;
import com.thinkgem.hys.pd.entity.DataYfexedetail;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药房发药单明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataYfexedetailService extends CrudService<DataYfexedetailDao, DataYfexedetail> {

	public DataYfexedetail get(String id) {
		return super.get(id);
	}
	
	public List<DataYfexedetail> findList(DataYfexedetail dataYfexedetail) {
		return super.findList(dataYfexedetail);
	}
	
	public Page<DataYfexedetail> findPage(Page<DataYfexedetail> page, DataYfexedetail dataYfexedetail) {
		return super.findPage(page, dataYfexedetail);
	}
	
	@Transactional(readOnly = false)
	public void save(DataYfexedetail dataYfexedetail) {
		super.save(dataYfexedetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataYfexedetail dataYfexedetail) {
		super.delete(dataYfexedetail);
	}
	
}