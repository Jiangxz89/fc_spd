/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.hys.pd.entity.PdPurchaseOrder;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 申购单DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdPurchaseOrderDao extends CrudDao<PdPurchaseOrder> {
	/**
	 * 批量更新状态
	 */
	public int batchUpdateOrderStatus(HashMap<String,Object> map);
	/**
	 * 批量更新合并单号
	 */
	public int batchUpdateMergeOrderNo(HashMap<String,Object> map);
	/**
	 * 只更新状态
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public int updateStatusByOrderNo(@Param("orderNo")String orderNo, @Param("orderStatus")String status);
	
	//根据申购单号查询数量和金额
	public PdPurchaseOrder getCountAndMoney(@Param("orderNos")List<String> orderNos);
}