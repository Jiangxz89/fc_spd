/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdHisSync;

/**
 * HIS数据同步状态表DAO接口
 * @author wg
 * @version 2018-08-29
 */
@MyBatisDao
public interface PdHisSyncDao extends CrudDao<PdHisSync> {
	
}