package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdDosageExcel {
	@ExcelField(title="用量单号",sort=10,align=2)
	private String dosageNo;
	@ExcelField(title="用量库房",sort=20,align=2)
	private String warehouseName;
	@ExcelField(title="用量日期",sort=30,align=2)
	private String dosageDate;
	@ExcelField(title="产品名称",sort=40,align=2)
	private String pdName;
	@ExcelField(title="产品权限",sort=50,align=2)
	private String power;
	@ExcelField(title="产品条码",sort=60,align=2)
	private String productBarCode;
	@ExcelField(title="规格",sort=70,align=2)
	private String spec;
	@ExcelField(title="型号",sort=80,align=2)
	private String version;
	@ExcelField(title="批号",sort=90,align=2)
	private String batchNo;
	@ExcelField(title="有效期",sort=100,align=2)
	private String limitDate;
	@ExcelField(title="数量",sort=110,align=2)
	private String productNum;
	@ExcelField(title="单位",sort=120,align=2)
	private String unit;
	@ExcelField(title="单价",sort=130,align=2)
	private String sellingPrice;
	@ExcelField(title="金额",sort=140,align=2)
	private String amountMoney;
	@ExcelField(title="生产厂家",sort=150,align=2)
	private String venderName;
	//@ExcelField(title="销售人员",sort=160,align=2)
	private String salesman;
	@ExcelField(title="操作人员",sort=170,align=2)
	private String dosageOperator;
	@ExcelField(title="执行科室",sort=180,align=2)
	private String exeDeptName;
	@ExcelField(title="手术科室",sort=190,align=2)
	private String oprDeptName;
	@ExcelField(title="住院号",sort=200,align=2)
	private String inHospitalNo;
	@ExcelField(title="病人姓名",sort=210,align=2)
	private String patientInfo;
	@ExcelField(title="手术医生",sort=220,align=2)
	private String surgeon;
/*	private String dosageNo;
	private String dosageNo;
	private String dosageNo;
	private String dosageNo;*/
	@ExcelField(title="备注",sort=270,align=2)
	private String remarks;
	public String getDosageNo() {
		return dosageNo;
	}
	public void setDosageNo(String dosageNo) {
		this.dosageNo = dosageNo;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getDosageDate() {
		return dosageDate;
	}
	public void setDosageDate(String dosageDate) {
		this.dosageDate = dosageDate;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getProductBarCode() {
		return productBarCode;
	}
	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
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
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(String amountMoney) {
		this.amountMoney = amountMoney;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getDosageOperator() {
		return dosageOperator;
	}
	public void setDosageOperator(String dosageOperator) {
		this.dosageOperator = dosageOperator;
	}
	public String getExeDeptName() {
		return exeDeptName;
	}
	public void setExeDeptName(String exeDeptName) {
		this.exeDeptName = exeDeptName;
	}
	public String getOprDeptName() {
		return oprDeptName;
	}
	public void setOprDeptName(String oprDeptName) {
		this.oprDeptName = oprDeptName;
	}
	public String getInHospitalNo() {
		return inHospitalNo;
	}
	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}
	public String getPatientInfo() {
		return patientInfo;
	}
	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}
	public String getSurgeon() {
		return surgeon;
	}
	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
