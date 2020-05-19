/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdSupplier;

/**
 * 供应商信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdSupplierDao extends CrudDao<PdSupplier> {
	public void batchDelete(List<String> result);

	public List<PdSupplier> verify(PdSupplier pdSupplier);

	public PdSupplier findByName(PdSupplier pdSupplier);

    List<PdSupplier> findSelectList(PdSupplier pdSupplier);
}