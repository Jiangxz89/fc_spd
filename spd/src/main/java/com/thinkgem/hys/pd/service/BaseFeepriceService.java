/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.BaseFeepriceDao;
import com.thinkgem.hys.pd.entity.BaseFeeprice;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 本院收费项目目录Service
 * @author zxh
 * @version 2019-11-13
 */
@Service
@Transactional(readOnly = true)
public class BaseFeepriceService extends CrudService<BaseFeepriceDao, BaseFeeprice> {

	public BaseFeeprice get(String id) {
		return super.get(id);
	}
	
	public List<BaseFeeprice> findList(BaseFeeprice baseFeeprice) {
		return super.findList(baseFeeprice);
	}
	
	public Page<BaseFeeprice> findPage(Page<BaseFeeprice> page, BaseFeeprice baseFeeprice) {
		return super.findPage(page, baseFeeprice);
	}
	
	@Transactional(readOnly = false)
	public void save(BaseFeeprice baseFeeprice) {
		super.save(baseFeeprice);
	}
	
	@Transactional(readOnly = false)
	public void delete(BaseFeeprice baseFeeprice) {
		super.delete(baseFeeprice);
	}
	
}