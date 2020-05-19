/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.bo.PdVenderBO;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdVender;

/**
 * 生产厂家表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdVenderDao extends CrudDao<PdVender> {
	PdVender getByName(PdVender pdVender);
	
	List<PdVender> findSimpleList(PdVender pdVender);

	List<PdVender> verify(PdVender pdVender);

	void deleteById(String string);

	PdVender findByName(PdVender pdVender);

	List<PdVenderBO> findUnsynchronizedList(PdVenderBO pdVenderBO);

	int updateSynFlag(PdVenderBO pdVenderBO);
}