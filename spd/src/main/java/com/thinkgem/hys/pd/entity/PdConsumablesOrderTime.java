/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.config.Global;

/**
 * 耗材订单及配送查询表Entity
 * @author zhangxiaohan
 * @version 2018-07-12
 */
public class PdConsumablesOrderTime {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 订单编号
	private String productNumber;		// 产品编号
	private Integer orderQuantity;		// 订货量
	private Integer psQuantity;		// 配送量
	private Double price;		// 单价
	private Double amount;		// 金额
	private String hospital;		// 医院名称
	private Date syncDate;		// 同步时间
	private String checkTime;
	
	public PdConsumablesOrderTime() {
		super();
		this.hospital = Global.getConfig("HOSPITAL_NUMBER");
	}
	
	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=100, message="产品编号长度必须介于 0 和 100 之间")
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	@Length(min=0, max=11, message="订货量长度必须介于 0 和 11 之间")
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	@Length(min=0, max=11, message="配送量长度必须介于 0 和 11 之间")
	public Integer getPsQuantity() {
		return psQuantity;
	}

	public void setPsQuantity(Integer psQuantity) {
		this.psQuantity = psQuantity;
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

	@Length(min=0, max=64, message="医院名称长度必须介于 0 和 64 之间")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	
	
}