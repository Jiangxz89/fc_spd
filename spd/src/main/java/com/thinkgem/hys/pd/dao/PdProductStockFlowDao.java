/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdProductStockFlow;

/**
 * 库存流水DAO接口
 * @author lww
 * @version 2018-03-28
 */
@MyBatisDao
public interface PdProductStockFlowDao extends CrudDao<PdProductStockFlow> {
	
}