/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYpthmx;

/**
 * 药品退货明细DAO接口
 * @author wg
 * @version 2018-08-14
 */
@MyBatisDao
public interface MedstoYpthmxDao extends CrudDao<MedstoYpthmx> {
	public int batchInsert(List<MedstoYpthmx> list);
}