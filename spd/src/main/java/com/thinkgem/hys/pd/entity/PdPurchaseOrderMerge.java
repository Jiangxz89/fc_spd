/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 合并申购单Entity
 * @author wg
 * @version 2018-03-08
 */
public class PdPurchaseOrderMerge extends DataEntity<PdPurchaseOrderMerge> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 申购单号
	private Date purchaseDate;		// 申购日期
	private String deptId;		// 申购科室ID
	private String deptName;		// 申购科室名称
	private String purchaseBy;		// 申购人
	private String orderStatus;		// 申购单状态
	private String supplierStatus; //供应商受理状态
	
	//--temp
	private String purchaseName;//申购人姓名
	private String supplierId;//供应商编号
	
	public PdPurchaseOrderMerge() {
		super();
	}

	public PdPurchaseOrderMerge(String id){
		super(id);
	}

	@Length(min=0, max=64, message="申购单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Length(min=0, max=64, message="申购科室ID长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=20, message="申购科室名称长度必须介于 0 和 20 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=64, message="申购人长度必须介于 0 和 64 之间")
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}
	
	@Length(min=0, max=1, message="申购单状态长度必须介于 0 和 1 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}