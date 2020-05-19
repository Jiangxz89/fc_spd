/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.BaseMedpriceDao;
import com.thinkgem.hys.pd.entity.BaseMedprice;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 本院药品目录Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class BaseMedpriceService extends CrudService<BaseMedpriceDao, BaseMedprice> {

	public BaseMedprice get(String id) {
		return super.get(id);
	}
	
	public List<BaseMedprice> findList(BaseMedprice baseMedprice) {
		return super.findList(baseMedprice);
	}
	
	public Page<BaseMedprice> findPage(Page<BaseMedprice> page, BaseMedprice baseMedprice) {
		return super.findPage(page, baseMedprice);
	}
	
	@Transactional(readOnly = false)
	public void save(BaseMedprice baseMedprice) {
		super.save(baseMedprice);
	}
	
	@Transactional(readOnly = false)
	public void delete(BaseMedprice baseMedprice) {
		super.delete(baseMedprice);
	}
	
}