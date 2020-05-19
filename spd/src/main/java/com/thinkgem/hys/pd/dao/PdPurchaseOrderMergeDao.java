/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.PdPurchaseOrderMerge;
import com.thinkgem.hys.pd.entity.vo.TempEntity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合并申购单DAO接口
 * @author wg
 * @version 2018-03-08
 */
@MyBatisDao
public interface PdPurchaseOrderMergeDao extends CrudDao<PdPurchaseOrderMerge> {
	/**
	 * 更新状态
	 */
	public int updateMergeOrderStatus(@Param("orderNo")String orderNo, @Param("orderStatus")String status);

	public void updateMergeSupplierStatus(PdPurchaseOrderMerge pdPurchaseOrderMerge);

	public void batchUpdate(List<TempEntity> array);

    List<PdPurchaseOrderMerge> findChooseOrderList(PdPurchaseOrderMerge pdPurchaseOrderMerge);
}