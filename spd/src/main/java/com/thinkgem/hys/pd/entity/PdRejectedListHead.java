/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退货单单头表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdRejectedListHead extends DataEntity<PdRejectedListHead> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String number;		// number
	private String rejectedDate;		// rejected_date
	private String rejectedRepoId;
	private String rejectedRepoName;		// rejected_repo_name
	private String supplierId;
	private String supplierName;		// supplier_name
	private String operPerson;		// oper_person
	private int isEnd;		//是否结束
	
	private List<PdRejectedListChild> child; //子列表
	
	private String supplier;//供应商名称给中心平台使用2018年8月8日11:44:01
	
	//冗余
	private String productName;		// 产品名称
	private String productNumber;
	private String version;
	private String spec;		// 规格
	private String unit;		// 产品单位
	private String categoryName;	// 分类名称
	private String groupName;	// 组别名称
	private Date validDate;		    // 有效期
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条码
	private int rejectedCount;		// 退货数量
	private int productTotNum;      //退货总数量
	
	private String ghdwMc;
	
	
	public String getRejectedRepoId() {
		return rejectedRepoId;
	}

	public void setRejectedRepoId(String rejectedRepoId) {
		this.rejectedRepoId = rejectedRepoId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public PdRejectedListHead() {
		super();
	}

	public PdRejectedListHead(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=100, message="number长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	


	public String getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(String rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	@Length(min=0, max=100, message="rejected_repo_name长度必须介于 0 和 100 之间")
	public String getRejectedRepoName() {
		return rejectedRepoName;
	}

	public void setRejectedRepoName(String rejectedRepoName) {
		this.rejectedRepoName = rejectedRepoName;
	}
	
	@Length(min=0, max=100, message="supplier_name长度必须介于 0 和 100 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=100, message="oper_person长度必须介于 0 和 100 之间")
	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	
	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}

	public List<PdRejectedListChild> getChild() {
		return child;
	}

	public void setChild(List<PdRejectedListChild> child) {
		this.child = child;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(int rejectedCount) {
		this.rejectedCount = rejectedCount;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getGhdwMc() {
		return ghdwMc;
	}

	public void setGhdwMc(String ghdwMc) {
		this.ghdwMc = ghdwMc;
	}

	public int getProductTotNum() {
		return productTotNum;
	}

	public void setProductTotNum(int productTotNum) {
		this.productTotNum = productTotNum;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
}