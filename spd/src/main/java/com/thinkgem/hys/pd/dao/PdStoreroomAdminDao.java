/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 库房管理员信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStoreroomAdminDao extends CrudDao<PdStoreroomAdmin> {
	public void deleteByStoreroomId(String storeroomId);
	
	public void saveGroupByStoreroomId(List<PdStoreroomAdmin> pdStoreroomAdminList);
	
	//查询仓库管理员信息
	public List<PdStoreroomAdmin> findAdminList(PdStoreroomAdmin pdStoreroomAdmin);
	
}