/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdUnit;
import com.thinkgem.hys.pd.dao.PdUnitDao;

/**
 * 产品单位表Service
 * @author jiangxz
 * @version 2019-05-10
 */
@Service
@Transactional(readOnly = true)
public class PdUnitService extends CrudService<PdUnitDao, PdUnit> {

	public PdUnit get(String id) {
		return super.get(id);
	}
	
	public List<PdUnit> findList(PdUnit pdUnit) {
		return super.findList(pdUnit);
	}
	
	public Page<PdUnit> findPage(Page<PdUnit> page, PdUnit pdUnit) {
		return super.findPage(page, pdUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(PdUnit pdUnit) {
		pdUnit.setSynFlag(MinaGlobalConstants.SYN_NONE);
		super.save(pdUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdUnit pdUnit) {
		pdUnit.setSynFlag(MinaGlobalConstants.SYN_NONE);
		super.delete(pdUnit);
	}

	@Transactional(readOnly = false)
	public void update(PdUnit pdUnit) {
		pdUnit.setSynFlag(MinaGlobalConstants.SYN_NONE);
		dao.update(pdUnit);
	}

	/**
	 * 校验组别是否唯一
	 * @param pdUnit
	 * @return
	 */
	public List<PdUnit> verify(PdUnit pdUnit) {
		return dao.verify(pdUnit);
	}
}