package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdDosagertExcel {
	
	@ExcelField(title="用量退回单号",sort=10,align=2)
	private String dosagertNo;
	@ExcelField(title="退用量日期",sort=20,align=2)
	private String dosagertDate;
	@ExcelField(title="入库库房",sort=30,align=2)
	private String dosagertRoomId;
	@ExcelField(title="退货库房",sort=40,align=2)
	private String dosagertInroomId;
	@ExcelField(title="产品名称",sort=50,align=2)
	private String prodName;
	@ExcelField(title="产品编号",sort=60,align=2)
	private String prodNo;
	@ExcelField(title="产品条码",sort=70,align=2)
	private String prodBarcode;
	@ExcelField(title="规格",sort=80,align=2)
	private String prodSpec;
	@ExcelField(title="型号",sort=90,align=2)
	private String version;
	@ExcelField(title="批号",sort=100,align=2)
	private String batchNo;
	@ExcelField(title="有效期",sort=110,align=2)
	private String expireDate;
	@ExcelField(title="数量",sort=120,align=2)
	private String rtCount;
	@ExcelField(title="操作人员",sort=130,align=2)
	private String operaterName;
	@ExcelField(title="备注",sort=140,align=2)
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
	public String getDosagertRoomId() {
		return dosagertRoomId;
	}
	public void setDosagertRoomId(String dosagertRoomId) {
		this.dosagertRoomId = dosagertRoomId;
	}
	public String getDosagertInroomId() {
		return dosagertInroomId;
	}
	public void setDosagertInroomId(String dosagertInroomId) {
		this.dosagertInroomId = dosagertInroomId;
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
