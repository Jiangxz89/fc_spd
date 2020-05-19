package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdRejectedListHeadExcel {
	
	
	@ExcelField(title="退货单号",sort=10,align=2)
	private String number;
	
	@ExcelField(title="退货日期",sort=20,align=2)
	private String rejectedDate;
	
	@ExcelField(title="退库库房",sort=30,align=2)
	private String rejectedRepoName;
	
	@ExcelField(title="供应商",sort=40,align=2)
	private String supplierName;
	
	@ExcelField(title="产品名称",sort=50,align=2)
	private String productName;
	
	@ExcelField(title="规格",sort=60,align=2)
	private String spec;
	
	@ExcelField(title="型号",sort=70,align=2)
	private String version;
	
	@ExcelField(title="批号",sort=80,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=90,align=2)
	private String validDate;
	
	@ExcelField(title="数量",sort=100,align=2)
	private String rejectedCount;
	
	@ExcelField(title="操作人",sort=110,align=2)
	private String operPerson;
	
	@ExcelField(title="备注",sort=120,align=2)
	private String remarks;

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

	public String getRejectedRepoName() {
		return rejectedRepoName;
	}

	public void setRejectedRepoName(String rejectedRepoName) {
		this.rejectedRepoName = rejectedRepoName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(String rejectedCount) {
		this.rejectedCount = rejectedCount;
	}

	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}	
