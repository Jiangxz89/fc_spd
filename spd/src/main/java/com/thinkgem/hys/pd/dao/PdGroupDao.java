/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.bo.PdGroupBO;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdGroup;

/**
 * 组别表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdGroupDao extends CrudDao<PdGroup> {
	PdGroup getByName(PdGroup pdGroup);

	List<PdGroup> verify(PdGroup pdGroup);

	PdGroup findByName(PdGroup pdGroup);

	List<PdGroupBO> findUnsynchronizedList(PdGroupBO pdGroupBO);

	int updateSynFlag(PdGroupBO pdGroupBO);
}