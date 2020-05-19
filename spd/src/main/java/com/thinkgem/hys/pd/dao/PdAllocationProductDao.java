/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdAllocationProduct;

/**
 * 调拨产品中间表DAO接口
 * @author zhengjinlei
 * @version 2018-03-24
 */
@MyBatisDao
public interface PdAllocationProductDao extends CrudDao<PdAllocationProduct> {

	void addAllocationProductBatch(@Param("pdAllocationProductList") List<PdAllocationProduct> pdAllocationProductList);
	
}