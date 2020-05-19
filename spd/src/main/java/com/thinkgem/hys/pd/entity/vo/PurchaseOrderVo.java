/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity.vo;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 临时实体--作展示数据用
 * @author wg
 * @version 2018-03-06
 */
public class PurchaseOrderVo extends DataEntity<PurchaseOrderVo> {
	
	private static final long serialVersionUID = 1L;
	
	private String deptId;		//科室ID
	private String deptName;	//科室名称
	private String brandName;	//品牌
	private String prodName;	//品名
	private String prodSpec;	//规格
	private String prodVersion;	//型号
	private String prodUnit;	//单位
	private String purchaseNum;	//申购数量
	
	//-temp
	private String orderStatus;//采购单状态
	
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdSpec() {
		return prodSpec;
	}
	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProdVersion() {
		return prodVersion;
	}
	public void setProdVersion(String prodVersion) {
		this.prodVersion = prodVersion;
	}
	
}