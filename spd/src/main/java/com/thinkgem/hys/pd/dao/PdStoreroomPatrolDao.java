/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrol;

/**
 * 库房巡查DAO接口
 * @author wg
 * @version 2018-03-30
 */
@MyBatisDao
public interface PdStoreroomPatrolDao extends CrudDao<PdStoreroomPatrol> {
	public int updateDegree(PdStoreroomPatrol pdStoreroomPatrol);
}