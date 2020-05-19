/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.DictMedicine;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 中心药品目录DAO接口
 * @author zxh
 * @version 2019-09-20
 */
@MyBatisDao
public interface DictMedicineDao extends CrudDao<DictMedicine> {
	
}