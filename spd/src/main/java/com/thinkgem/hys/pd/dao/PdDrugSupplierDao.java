/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdDrugSupplier;

/**
 * 药品供应商表DAO接口
 * @author wg
 * @version 2018-08-23
 */
@MyBatisDao
public interface PdDrugSupplierDao extends CrudDao<PdDrugSupplier> {
	public PdDrugSupplier getOne(@Param("ypdm")String ypdm, @Param("type")String type);

	public void batchDelete(HashMap<String, Object> map);

	public void batchSave(List<PdDrugSupplier> drugSuppliers);
}