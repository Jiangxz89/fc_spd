/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 实体包实体产品表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdPackageEntityChild extends DataEntity<PdPackageEntityChild> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String stockId;		// stock_id
	private String count;		// count
	
	public PdPackageEntityChild() {
		super();
	}

	public PdPackageEntityChild(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Length(min=0, max=64, message="stock_id长度必须介于 0 和 64 之间")
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	@Length(min=0, max=11, message="count长度必须介于 0 和 11 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}