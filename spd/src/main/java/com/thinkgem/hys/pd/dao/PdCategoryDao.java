/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.bo.PdCategoryBO;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdCategory;

/**
 * 类别表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdCategoryDao extends CrudDao<PdCategory> {
	PdCategory getByName(PdCategory pdCategory);
	
	PdCategory deleteById(@Param(value = "id")String id);

	List<PdCategory> verify(PdCategory pdCategory);

	PdCategory findByName(PdCategory pdCategory);

	List<PdCategoryBO> findUnsynchronizedList(PdCategoryBO pdCategoryBO);

	int updateSynFlag(PdCategoryBO categoryBO);
}