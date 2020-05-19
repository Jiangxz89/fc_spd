/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdSyncState;

/**
 * 同步中心平台状态表DAO接口
 * @author zxh
 * @version 2018-07-30
 */
@MyBatisDao
public interface PdSyncStateDao extends CrudDao<PdSyncState> {

	PdSyncState findByDate(PdSyncState pdSyncState);

	List<PdSyncState> findListByState(PdSyncState syncState);
	
}