/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdRejectedListChild;

/**
 * 退货单子单表DAO接口
 * @author sutianqi
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdRejectedListChildDao extends CrudDao<PdRejectedListChild> {
	
}