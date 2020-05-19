package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


public class ProductExcel {

	@ExcelField(title="产品编号",sort=10,align=2)
	private String number;

	@ExcelField(title="产品名称",sort=20,align=2)
	private String name;

	@ExcelField(title="产品分类",sort=30,align=2)
	private String category;

	@ExcelField(title="产品组别",sort=40,align=2)
	private String group;

	@ExcelField(title="单位",sort=50,align=2)
	private String unit;

	@ExcelField(title = "供应商",sort=80,align=2)
	private String supplierNameShow;

	@ExcelField(title="规格",sort=60,align=2)
	private String spec;

	@ExcelField(title="型号",sort=70,align=2)
	private String version;

	@ExcelField(title="生产厂家",sort=80,align=2)
	private String vender;

	@ExcelField(title="项目收费代码",sort=90,align=2)
	private String chargeCode;

	@ExcelField(title="注册证号",sort=100,align=2)
	private String registration;

	@ExcelField(title = "是否计费",sort=50,align=2)
	private String isCharge;

	@ExcelField(title="出货单价",sort=110,align=2)
	private String sellingPrice;
	
	@ExcelField(title="备注",sort=120,align=2)
	private String description;

	public String getSupplierNameShow() {
		return supplierNameShow;
	}

	public void setSupplierNameShow(String supplierNameShow) {
		this.supplierNameShow = supplierNameShow;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge =isCharge;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	
}
