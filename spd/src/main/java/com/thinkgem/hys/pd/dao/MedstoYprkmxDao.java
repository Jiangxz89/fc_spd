/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.MedstoYprkmx;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 药库入库明细DAO接口
 * @author sutianqi
 * @version 2018-07-31
 */
@MyBatisDao
public interface MedstoYprkmxDao extends CrudDao<MedstoYprkmx> {
	public int batchInsert(List<MedstoYprkmx> list);
	public List<MedstoYprkmx> findCallInQuery(MedstoYprkmx medstoYprkmx);

}