/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdProductStock;

/**
 * 库存记录DAO接口
 * @author changjundong
 * @version 2018-03-07
 */
@MyBatisDao
public interface PdProductStockDao extends CrudDao<PdProductStock> {
	public List<PdProductStock>  getByProductBarCode(PdProductStock pdProductStock);
	
	public void updateStockNum(PdProductStock pdProductStock);
	
	public PdProductStock getByBatchNo(PdProductStock pdProductStock);
	
	//加库存
	public int addStock(PdProductStock pdProductStock);
	
	//减库存
	public int minusStock(PdProductStock pdProductStock);

	/**
	 * 库存管理查询
	 * @param pdProductStock
	 */
	public List<PdProductStock> findListForQuery(PdProductStock pdProductStock);
	
	public Map<String, Object> findPdCount(PdProductStock productStock);
	
	/**
	 * 物流追溯产品列表
	 * @param pdProductStock
	 * @return
	 */
	public List<PdProductStock> getByOriginalProduct(PdProductStock pdProductStock);
	
	/**
	 * 无id条件查询
	 * */
	public PdProductStock searchOneStock(PdProductStock pdProductStock);
	
	/**
	 * 库存明细锁表查询
	 * 注意：带事务方法调用时使用
	 * @param pdProductStock
	 */
	public List<PdProductStock> findForUpdate(PdProductStock pdProductStock);
	
	/**
	 * 多条件查询一条记录
	 * */
	public PdProductStock getOneStock(PdProductStock pdProductStock);
	
	/**
	 * 更新库存状态
	 * @param pdProductStock
	 */	
	public void updatePdState(PdProductStock pdProductStock);
	
	/**
	 * 模糊查询（关联产品表）
	 * */
	public List<PdProductStock> findListAndProduct(PdProductStock pdProductStock);

	public List<PdProductStock> getProductInfoByOther(PdProductStock pdProductStock);

    List<PdProductStock> getByProductBarCodeNew(PdProductStock pdProductStock);
}