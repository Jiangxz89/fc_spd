/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;


/**
 * 耗材订单表Entity
 * @author zhangxiaohan
 * @version 2018-07-06
 */
public class PdConsumablesOrder{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String number;		// 订单编号
	private String hospital;		// 下单医院
	private Integer orderQuantity;		// 订货量
	private Double orderAmount;	// 订单金额
	private String orderState;		// 订单状态
	private String orderDate;		// 订单日期
	
	
	
	//冗余
	private String productType; //耗材类型
	private String enterprise; //生产企业
	private String supplierName;//供应商
	private List<PdConsumablesOrderDetail> pdConsumablesOrderDetails; //耗材订单详情
	public PdConsumablesOrder() {
		super();
	}

	@Length(min=0, max=100, message="订单编号长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=64, message="下单医院长度必须介于 0 和 64 之间")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	@Length(min=0, max=11, message="订货数长度必须介于 0 和 11 之间")
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Length(min=0, max=1, message="订单状态长度必须介于 0 和 1 之间")
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<PdConsumablesOrderDetail> getPdConsumablesOrderDetails() {
		return pdConsumablesOrderDetails;
	}

	public void setPdConsumablesOrderDetails(List<PdConsumablesOrderDetail> pdConsumablesOrderDetails) {
		this.pdConsumablesOrderDetails = pdConsumablesOrderDetails;
	}
	
	
}