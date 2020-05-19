/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;


/**
 * 耗材订单详情表Entity
 * @author zhangxiaohan
 * @version 2018-07-06
 */
public class PdConsumablesOrderDetail{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String orderId;		// 药品订单id
	private String number;		// 耗材编码
	private Integer orderQuantity;		// 订货数
	private Double price;		// 单价
	private Double amount;		// 金额
	private String supplierName;//供应商名称
	
	
	//冗余
	private String productId;//产品ID
	private String name;//耗材名称
	private String spec;//规格
	private String unit; //单位
	private String venderName; //生产厂家
	public PdConsumablesOrderDetail() {
		super();
	}
	@Length(min=0, max=64, message="药品订单id长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=100, message="耗材编码长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	@Length(min=0, max=11, message="订货数长度必须介于 0 和 11 之间")
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}