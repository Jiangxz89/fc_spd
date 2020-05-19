/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdApplyDetail;

/**
 * 申领单详细DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdApplyDetailDao extends CrudDao<PdApplyDetail> {
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int batchInsert(List<PdApplyDetail> list);
}