/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import com.thinkgem.hys.pd.dao.PdProductRuleDao;
import com.thinkgem.hys.pd.entity.PdProductRule;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品绑定编码规则Service
 * @author zxh
 * @version 2019-11-03
 */
@Service
@Transactional(readOnly = true)
public class PdProductRuleService extends CrudService<PdProductRuleDao, PdProductRule> {

	public PdProductRule get(String id) {
		return super.get(id);
	}
	
	public List<PdProductRule> findList(PdProductRule pdProductRule) {
		return super.findList(pdProductRule);
	}
	
	public Page<PdProductRule> findPage(Page<PdProductRule> page, PdProductRule pdProductRule) {
		return super.findPage(page, pdProductRule);
	}
	
	@Transactional(readOnly = false)
	public void save(PdProductRule pdProductRule) {
		super.save(pdProductRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdProductRule pdProductRule) {
		super.delete(pdProductRule);
	}
	
}