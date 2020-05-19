/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 调拨产品中间表Entity
 * @author zhengjinlei
 * @version 2018-03-24
 */
public class PdAllocationProduct extends DataEntity<PdAllocationProduct> {
	
	private static final long serialVersionUID = 1L;
	private String allocationCode;		// 调拨单编号
	private String productCode;		// 产品编号
	private String allocationNum;		// 调拨数量
	private String productAttr;		// 产品属性：1、产品 2、定数包
	
	public PdAllocationProduct() {
		super();
	}

	public PdAllocationProduct(String id){
		super(id);
	}

	@Length(min=0, max=64, message="调拨单编号长度必须介于 0 和 64 之间")
	public String getAllocationCode() {
		return allocationCode;
	}

	public void setAllocationCode(String allocationCode) {
		this.allocationCode = allocationCode;
	}
	
	@Length(min=0, max=64, message="产品编号长度必须介于 0 和 64 之间")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@Length(min=0, max=64, message="调拨数量长度必须介于 0 和 64 之间")
	public String getAllocationNum() {
		return allocationNum;
	}

	public void setAllocationNum(String allocationNum) {
		this.allocationNum = allocationNum;
	}
	
	@Length(min=0, max=2, message="产品属性：1、产品 2、定数包长度必须介于 0 和 2 之间")
	public String getProductAttr() {
		return productAttr;
	}

	public void setProductAttr(String productAttr) {
		this.productAttr = productAttr;
	}
	
}