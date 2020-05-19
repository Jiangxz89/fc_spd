/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.DictMaterial;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 中心物资目录DAO接口
 * @author zxh
 * @version 2019-11-13
 */
@MyBatisDao
public interface DictMaterialDao extends CrudDao<DictMaterial> {
	
}