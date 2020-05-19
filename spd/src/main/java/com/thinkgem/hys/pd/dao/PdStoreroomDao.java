/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 库房信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStoreroomDao extends CrudDao<PdStoreroom> {
	public String findFirstStoreroom();
	
	/**
	 * 传入用户ID查询用户管理的库房信息
	 * @param adminId
	 * @return
	 */
	public List<PdStoreroom> findByUserid(String adminId);
	public List<PdStoreroom> findByStoreroom(PdStoreroom storeroom);
	public List<Map<String,Object>> findStoreroomList(PdStoreroom storeroom);
}