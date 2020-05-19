/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataDetailprofitDao;
import com.thinkgem.hys.pd.entity.DataDetailprofit;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 医院药品报损丢失单明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataDetailprofitService extends CrudService<DataDetailprofitDao, DataDetailprofit> {

	public DataDetailprofit get(String id) {
		return super.get(id);
	}
	
	public List<DataDetailprofit> findList(DataDetailprofit dataDetailprofit) {
		return super.findList(dataDetailprofit);
	}
	
	public Page<DataDetailprofit> findPage(Page<DataDetailprofit> page, DataDetailprofit dataDetailprofit) {
		return super.findPage(page, dataDetailprofit);
	}
	
	@Transactional(readOnly = false)
	public void save(DataDetailprofit dataDetailprofit) {
		super.save(dataDetailprofit);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataDetailprofit dataDetailprofit) {
		super.delete(dataDetailprofit);
	}
	
}