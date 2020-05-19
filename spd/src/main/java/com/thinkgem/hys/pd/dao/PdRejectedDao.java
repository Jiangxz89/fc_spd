/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdRejected;

/**
 * 退货信息DAO接口
 * @author yueguoyun
 * @version 2018-04-29
 */
@MyBatisDao
public interface PdRejectedDao extends CrudDao<PdRejected> {
	
}