package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PatrolRecordExcel {

	@ExcelField(title="库房名称",sort=10,align=2)
	private String storeroomName;
	
	@ExcelField(title="产品名称",sort=20,align=2)
	private String prodName;
	
	@ExcelField(title="产品编号",sort=30,align=2)
	private String prodNo;
	
	@ExcelField(title="产品条码",sort=40,align=2)
	private String prodBarCode;
	
	@ExcelField(title="规格",sort=50,align=2)
	private String prodSpec;
	
	@ExcelField(title="型号",sort=60,align=2)
	private String prodVersion;
	
	@ExcelField(title="批号",sort=70,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=80,align=2)
	private String validDate;
	
	@ExcelField(title="数量",sort=90,align=2)
	private String stockNum;
	
	@ExcelField(title="单位",sort=100,align=2)
	private String prodUnit;
	
	@ExcelField(title="生产厂家",sort=110,align=2)
	private String venderName;
	
	@ExcelField(title="注册证号",sort=120,align=2)
	private String registration;
	
	@ExcelField(title="是否过期",sort=130,align=2, dictType="pd_state")
	private String isExpire;
	
	@ExcelField(title="检查结果",sort=140,align=2, dictType="pd_storeroom_patrol_result")
	private String result;
	
	@ExcelField(title="备注",sort=150,align=2)
	private String remarks;

	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdBarCode() {
		return prodBarCode;
	}

	public void setProdBarCode(String prodBarCode) {
		this.prodBarCode = prodBarCode;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public String getProdVersion() {
		return prodVersion;
	}

	public void setProdVersion(String prodVersion) {
		this.prodVersion = prodVersion;
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

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getProdUnit() {
		return prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
