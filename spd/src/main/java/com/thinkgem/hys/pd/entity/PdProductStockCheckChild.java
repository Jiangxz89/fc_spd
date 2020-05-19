/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 盘点详细表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdProductStockCheckChild extends DataEntity<PdProductStockCheckChild> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String parentId;	//父id
	private String stockId;		// stock_id
	private int actualCount;		// 实际数量
	private int profitLossCount;	//盈亏
	private String remarks;
	
	//--冗余
	private int stockCount;			//冗余字段：用作计算总量，理论库存数量
	private PdProductStock pdProductStock;
	private PdProduct pdProduct;
	
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public PdProductStock getPdProductStock() {
		return pdProductStock;
	}

	public void setPdProductStock(PdProductStock pdProductStock) {
		this.pdProductStock = pdProductStock;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public PdProductStockCheckChild() {
		super();
	}

	public PdProductStockCheckChild(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min=0, max=64, message="stock_id长度必须介于 0 和 64 之间")
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getActualCount() {
		return actualCount;
	}

	public void setActualCount(int actualCount) {
		this.actualCount = actualCount;
	}

	public int getProfitLossCount() {
		return profitLossCount;
	}

	public void setProfitLossCount(int profitLossCount) {
		this.profitLossCount = profitLossCount;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	
	
	
}