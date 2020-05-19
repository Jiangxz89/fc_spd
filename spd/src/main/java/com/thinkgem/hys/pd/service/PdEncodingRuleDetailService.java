/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdEncodingRuleDetail;
import com.thinkgem.hys.pd.dao.PdEncodingRuleDetailDao;

/**
 * 编码规则详情信息表Service
 * @author zxh
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly = true)
public class PdEncodingRuleDetailService extends CrudService<PdEncodingRuleDetailDao, PdEncodingRuleDetail> {

	public PdEncodingRuleDetail get(String id) {
		return super.get(id);
	}
	
	public List<PdEncodingRuleDetail> findList(PdEncodingRuleDetail pdEncodingRuleDetail) {
		return super.findList(pdEncodingRuleDetail);
	}
	
	public Page<PdEncodingRuleDetail> findPage(Page<PdEncodingRuleDetail> page, PdEncodingRuleDetail pdEncodingRuleDetail) {
		return super.findPage(page, pdEncodingRuleDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PdEncodingRuleDetail pdEncodingRuleDetail) {
		super.save(pdEncodingRuleDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdEncodingRuleDetail pdEncodingRuleDetail) {
		super.delete(pdEncodingRuleDetail);
	}
	
}