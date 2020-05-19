/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.PdPatien;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * pdPatienDAO接口
 * @author jiangxz
 * @version 2019-10-08
 */
@MyBatisDao
public interface PdPatienDao extends CrudDao<PdPatien> {
	
}