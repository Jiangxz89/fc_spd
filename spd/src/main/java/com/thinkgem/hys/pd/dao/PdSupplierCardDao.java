/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdSupplierCard;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 供应商证件信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdSupplierCardDao extends CrudDao<PdSupplierCard> {
	
	public void deleteBySupplierId(String supplierId);
	
	public void saveGroupBySupplierId(List<PdSupplierCard> pdSupplierCardList);
}