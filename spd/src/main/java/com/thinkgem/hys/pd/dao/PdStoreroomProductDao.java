/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdStoreroomProduct;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 库房产品信息DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdStoreroomProductDao extends CrudDao<PdStoreroomProduct> {
	public void deleteByStoreroomId(String storeroomId);
	
	public void saveGroupByStoreroomId(List<PdStoreroomProduct> pdStoreroomProductList);
}