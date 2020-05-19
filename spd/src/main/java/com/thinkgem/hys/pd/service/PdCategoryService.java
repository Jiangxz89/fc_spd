/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.service;

import java.util.List;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.bo.PdCategoryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.hys.pd.entity.PdCategory;
import com.thinkgem.hys.pd.dao.PdCategoryDao;

/**
 * 类别表Service
 * @author sutianqi
 * @version 2018-03-07
 */

@Service
@Transactional(readOnly = true)
public class PdCategoryService extends CrudService<PdCategoryDao, PdCategory> {

	@Autowired
	PdCategoryDao pdCategoryDao ;
	
	public PdCategory get(String id) {
		return super.get(id);
	}
	
	public List<PdCategory> findList(PdCategory pdCategory) {
		return super.findList(pdCategory);
	}
	
	public Page<PdCategory> findPage(Page<PdCategory> page, PdCategory pdCategory) {
		return super.findPage(page, pdCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(PdCategory pdCategory) {
		pdCategory.setSynFlag(MinaGlobalConstants.SYN_NONE);
		super.save(pdCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PdCategory pdCategory) {
		super.delete(pdCategory);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(PdCategory pdCategory) {
		pdCategoryDao.deleteById(pdCategory.getId());
	}
	
	@Transactional(readOnly = false)
	public void update(PdCategory pdCategory){
		pdCategory.setSynFlag(MinaGlobalConstants.SYN_NONE);
		pdCategoryDao.update(pdCategory);
	}
	
	public PdCategory getByName(PdCategory pdCategory) {
		return pdCategoryDao.getByName(pdCategory);
	}

	/**
	 * 校验分类是否重复
	 * @param pdCategory
	 * @return
	 */
	public List<PdCategory> verify(PdCategory pdCategory) {
		return pdCategoryDao.verify(pdCategory);
	}

	/**
	 * 校验产品分类是否输入正确
	 * @param pdCategory
	 * @return
	 */
	public PdCategory findByName(PdCategory pdCategory) {
		return pdCategoryDao.findByName(pdCategory);
	}

	/**
	 * 查询位同步列表
	 * @author jiangxz
	 * @date
	 * @param
	 * @return
	 */
	public List<PdCategoryBO> findUnsynchronizedList(PdCategoryBO pdCategoryBO){
		return pdCategoryDao.findUnsynchronizedList(pdCategoryBO);
	}

	@Transactional(readOnly = false)
	public void updateSynFlag(PdCategoryBO categoryBO){
		pdCategoryDao.updateSynFlag(categoryBO);
	}
}