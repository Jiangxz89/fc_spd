package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PurchaseDetailExcel {
	@ExcelField(title="产品编号",sort=10,align=2)
	private String prodNo;
	@ExcelField(title="产品名称",sort=20,align=2)
	private String prodName;
	@ExcelField(title="交易平台产品ID",sort=30,align=2)
	private String tpProdId;
	@ExcelField(title="供应商名称",sort=40,align=2)
	private String supplierName;
	@ExcelField(title="生产厂家",sort=50,align=2)
	private String venderName;
	@ExcelField(title="产品价格",sort=60,align=2)
	private String prodPrice;
	@ExcelField(title="产品规格",sort=70,align=2)
	private String prodSpec;
	@ExcelField(title="型号",sort=80,align=2)
	private String prodVersion;
	@ExcelField(title="单位",sort=90,align=2)
	private String prodUnit;
	@ExcelField(title="库存数量",sort=100,align=2)
	private String stockNum;
	@ExcelField(title="申购数量",sort=110,align=2)
	private String applyCount;

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getTpProdId() {
		return tpProdId;
	}

	public void setTpProdId(String tpProdId) {
		this.tpProdId = tpProdId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
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

	public String getProdUnit() {
		return prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}
}
