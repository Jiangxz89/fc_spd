/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdStockLog;

/**
 * 物流追溯信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStockLogDao extends CrudDao<PdStockLog> {
	public int batchInsert(List<PdStockLog> list);
}