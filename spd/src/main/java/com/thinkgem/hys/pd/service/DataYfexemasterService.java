/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataYfexemasterDao;
import com.thinkgem.hys.pd.entity.DataYfexemaster;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药房发药单Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataYfexemasterService extends CrudService<DataYfexemasterDao, DataYfexemaster> {

	public DataYfexemaster get(String id) {
		return super.get(id);
	}
	
	public List<DataYfexemaster> findList(DataYfexemaster dataYfexemaster) {
		return super.findList(dataYfexemaster);
	}
	
	public Page<DataYfexemaster> findPage(Page<DataYfexemaster> page, DataYfexemaster dataYfexemaster) {
		return super.findPage(page, dataYfexemaster);
	}
	
	@Transactional(readOnly = false)
	public void save(DataYfexemaster dataYfexemaster) {
		super.save(dataYfexemaster);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataYfexemaster dataYfexemaster) {
		super.delete(dataYfexemaster);
	}
	
}