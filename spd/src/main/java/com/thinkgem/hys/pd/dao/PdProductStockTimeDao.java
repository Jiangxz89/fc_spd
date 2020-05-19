/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockTime;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 库存时刻表DAO接口
 * @author wg
 * @version 2018-04-25
 */
@MyBatisDao
public interface PdProductStockTimeDao extends CrudDao<PdProductStockTime> {
	/**
	 * 查询当天的产品库存
	 * @param pdProductStockTime
	 * @return
	 */
	public PdProductStockTime getPrdStockTime(PdProductStockTime pdProductStockTime);
	/**
	 * 批量插入
	 */
	public int batchInsert(@Param("list")List<PdProductStock> list, @Param("stockDate")Date stockDate);
	
	/**
	 * 查询当天库存
	 * @param pdProductStockTime
	 * @return
	 */
	public List<PdProductStockTime> queryTodayStockInfo(PdProductStockTime pdProductStockTime);
}