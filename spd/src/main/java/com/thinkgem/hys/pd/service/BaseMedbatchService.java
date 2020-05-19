/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.BaseMedbatchDao;
import com.thinkgem.hys.pd.entity.BaseMedbatch;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品库存明细Service
 * @author zxh
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class BaseMedbatchService extends CrudService<BaseMedbatchDao, BaseMedbatch> {

	public BaseMedbatch get(String id) {
		return super.get(id);
	}
	
	public List<BaseMedbatch> findList(BaseMedbatch baseMedbatch) {
		return super.findList(baseMedbatch);
	}
	
	public Page<BaseMedbatch> findPage(Page<BaseMedbatch> page, BaseMedbatch baseMedbatch) {
		return super.findPage(page, baseMedbatch);
	}
	
	@Transactional(readOnly = false)
	public void save(BaseMedbatch baseMedbatch) {
		super.save(baseMedbatch);
	}
	
	@Transactional(readOnly = false)
	public void delete(BaseMedbatch baseMedbatch) {
		super.delete(baseMedbatch);
	}
	
}