/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdProductMPackage;

/**
 * 产品定数包中间表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdProductMPackageDao extends CrudDao<PdProductMPackage> {
	public List<PdProductMPackage> getProdListByPackageId(@Param("storeroomId")String storeroomId,@Param("otherDeptId")String otherDeptId, @Param("packageId")String packageId);
	
	public List<PdProductMPackage> getAllByPackageId(String packageId);
}