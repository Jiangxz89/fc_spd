/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;

/**
 * 库存总DAO接口
 * @author changjundong
 * @version 2018-03-28
 */
@MyBatisDao
public interface PdProductStockTotalDao extends CrudDao<PdProductStockTotal> {
	public PdProductStockTotal  getByProductBarCode(PdProductStockTotal pdProductStockTotal);
	
	public void updateStockNum(PdProductStockTotal pdProductStockTotal);
	
	public PdProductStock getByBatchNo(PdProductStockTotal pdProductStockTotal);
	
	//加库存
	public void addStock(PdProductStockTotal pdProductStockTotal);
	
	//减库存
	public int minusStock(PdProductStockTotal pdProductStockTotal);

	/**
	 * 库存管理查询
	 * @param pdProductStock
	 */
	public List<PdProductStockTotal> findListForQuery(PdProductStockTotal pdProductStockTotal);

	public Map<String, Object> findPdCount(PdProductStockTotal pdProductStockTotal);

	public void updateProductStock(PdProductStockTotal stockTotal);	
	
	/**
	 * 库存总表加锁查询，有事务时使用
	 * @param  pdProductStockTotal
	 * @return List<PdProductStockTotal>
	 */
	public List<PdProductStockTotal> findForUpdate(PdProductStockTotal pdProductStockTotal);
	
	public void updateForDosagert(PdProductStockTotal pdProductStockTotal);

	public List<PdProductStockTotal> findListTask(PdProductStockTotal pdProductStockTotal);

}