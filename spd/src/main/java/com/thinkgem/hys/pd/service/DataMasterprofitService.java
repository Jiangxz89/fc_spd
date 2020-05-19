/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.DataMasterprofitDao;
import com.thinkgem.hys.pd.entity.DataMasterprofit;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 医院药品报损丢失单Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class DataMasterprofitService extends CrudService<DataMasterprofitDao, DataMasterprofit> {

	public DataMasterprofit get(String id) {
		return super.get(id);
	}
	
	public List<DataMasterprofit> findList(DataMasterprofit dataMasterprofit) {
		return super.findList(dataMasterprofit);
	}
	
	public Page<DataMasterprofit> findPage(Page<DataMasterprofit> page, DataMasterprofit dataMasterprofit) {
		return super.findPage(page, dataMasterprofit);
	}
	
	@Transactional(readOnly = false)
	public void save(DataMasterprofit dataMasterprofit) {
		super.save(dataMasterprofit);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataMasterprofit dataMasterprofit) {
		super.delete(dataMasterprofit);
	}
	
}