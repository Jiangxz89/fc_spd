package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class StockTimeExcel {
	
	@ExcelField(title="库房名称",sort=10,align=2)
	private String storeroomId;
	
	@ExcelField(title="产品名称",sort=10,align=2)
	private String productName;
	
	@ExcelField(title="产品编号",sort=10,align=2)
	private String prodNo;
	
	@ExcelField(title="产品条码",sort=10,align=2)
	private String barCode;
	
	@ExcelField(title="规格",sort=10,align=2)
	private String productSpec;
	
	@ExcelField(title="型号",sort=10,align=2)
	private String productVersion;
	
	@ExcelField(title="批号",sort=10,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=10,align=2)
	private String validDate;
	
	@ExcelField(title="数量",sort=10,align=2)
	private String stockNum;
	
	@ExcelField(title="生产厂家",sort=10,align=2)
	private String venderName;
	
	@ExcelField(title="供应商",sort=10,align=2)
	private String supplierName;
	
	public String getStoreroomId() {
		return storeroomId;
	}
	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public String getProductVersion() {
		return productVersion;
	}
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
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
	
}
