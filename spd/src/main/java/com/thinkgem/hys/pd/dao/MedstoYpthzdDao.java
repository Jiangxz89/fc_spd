/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYpthzd;

/**
 * 药品退货账单DAO接口
 * @author wg
 * @version 2018-08-14
 */
@MyBatisDao
public interface MedstoYpthzdDao extends CrudDao<MedstoYpthzd> {
	public int batchInsert(List<MedstoYpthzd> list);
}