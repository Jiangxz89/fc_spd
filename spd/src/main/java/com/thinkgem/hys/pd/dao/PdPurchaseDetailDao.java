/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;

import com.thinkgem.hys.pd.entity.PdPurchaseDetail;
import com.thinkgem.hys.pd.entity.vo.PurchaseOrderVo;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 申购单详细DAO接口
 * @author wg
 * @version 2018-03-06
 */
@MyBatisDao
public interface PdPurchaseDetailDao extends CrudDao<PdPurchaseDetail> {
	
	/**
	 * 按科室汇总
	 * @param map
	 * @return
	 */
	public List<PurchaseOrderVo> queryGroupListByDept(PurchaseOrderVo purchaseOrderVo);
	/**
	 * 按品名汇总
	 * @param map
	 * @return
	 */
	public List<PurchaseOrderVo> queryGroupListByProduct(PurchaseOrderVo purchaseOrderVo);
	
	/**
	 * 批量插入申购单明细
	 * @param list
	 * @return
	 */
	public int batchInsert(List<PdPurchaseDetail> list);
	
	/**
	 * 
	 * */
	public List<PdPurchaseDetail> findAllList();
	
}