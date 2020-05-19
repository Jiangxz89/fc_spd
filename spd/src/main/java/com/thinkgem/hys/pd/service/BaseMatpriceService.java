/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.BaseMatpriceDao;
import com.thinkgem.hys.pd.entity.BaseMatprice;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 本院物资目录Service
 * @author zxh
 * @version 2019-11-13
 */
@Service
@Transactional(readOnly = true)
public class BaseMatpriceService extends CrudService<BaseMatpriceDao, BaseMatprice> {

	public BaseMatprice get(String id) {
		return super.get(id);
	}
	
	public List<BaseMatprice> findList(BaseMatprice baseMatprice) {
		return super.findList(baseMatprice);
	}
	
	public Page<BaseMatprice> findPage(Page<BaseMatprice> page, BaseMatprice baseMatprice) {
		return super.findPage(page, baseMatprice);
	}
	
	@Transactional(readOnly = false)
	public void save(BaseMatprice baseMatprice) {
		super.save(baseMatprice);
	}
	
	@Transactional(readOnly = false)
	public void delete(BaseMatprice baseMatprice) {
		super.delete(baseMatprice);
	}
	
}