package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class DosagertExcel {

	@ExcelField(title="用量退回单号",sort=10,align=2)
	private String dosagertNo;
	@ExcelField(title="退用量日期",sort=10,align=2)
	private String dosagertDate;
	@ExcelField(title="入库库房",sort=10,align=2)
	private String dosagertInroomName;
	@ExcelField(title="产品名称",sort=10,align=2)
	private String prodName;
	@ExcelField(title="产品编号",sort=10,align=2)
	private String prodNumber;
	@ExcelField(title="产品条码",sort=10,align=2)
	private String prodBarcode;
	@ExcelField(title="规格",sort=10,align=2)
	private String prodSpec;
	@ExcelField(title="型号",sort=10,align=2)
	private String prodVersion;
	@ExcelField(title="批号",sort=10,align=2)
	private String batchNo;
	@ExcelField(title="有效期",sort=10,align=2)
	private String expireDate;
	@ExcelField(title="数量",sort=10,align=2)
	private String rtCount;
	@ExcelField(title="操作人员",sort=10,align=2)
	private String operaterName;
	@ExcelField(title="备注",sort=10,align=2)
	private String remarks;
	public String getDosagertNo() {
		return dosagertNo;
	}
	public void setDosagertNo(String dosagertNo) {
		this.dosagertNo = dosagertNo;
	}
	public String getDosagertDate() {
		return dosagertDate;
	}
	public void setDosagertDate(String dosagertDate) {
		this.dosagertDate = dosagertDate;
	}
	public String getDosagertInroomName() {
		return dosagertInroomName;
	}
	public void setDosagertInroomName(String dosagertInroomName) {
		this.dosagertInroomName = dosagertInroomName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdNumber() {
		return prodNumber;
	}
	public void setProdNumber(String prodNumber) {
		this.prodNumber = prodNumber;
	}
	public String getProdBarcode() {
		return prodBarcode;
	}
	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
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
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getRtCount() {
		return rtCount;
	}
	public void setRtCount(String rtCount) {
		this.rtCount = rtCount;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
