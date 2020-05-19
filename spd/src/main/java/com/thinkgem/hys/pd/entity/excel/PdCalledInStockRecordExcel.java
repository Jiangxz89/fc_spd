package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdCalledInStockRecordExcel {
	
	@ExcelField(title="出库单号",sort=10,align=2)
	private String outReCordNo;
	
	@ExcelField(title="出库日期",sort=20,align=2)
	private String recordDate;
	
	@ExcelField(title="出库库房",sort=30,align=2)
	private String outId;
	
	@ExcelField(title="入库库房",sort=40,align=2)
	private String inId;
	
	@ExcelField(title="产品名称",sort=50,align=2)
	private String pdName;
	
	@ExcelField(title="产品编号",sort=60,align=2)
	private String number;
	
	@ExcelField(title="产品条码",sort=70,align=2)
	private String productBarCode;
	
	@ExcelField(title="规格",sort=80,align=2)
	private String spec;
	
	@ExcelField(title="型号",sort=90,align=2)
	private String version;
	
	@ExcelField(title="批号",sort=100,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=110,align=2)
	private String limitDate;
	
	@ExcelField(title="数量",sort=120,align=2)
	private String productNum;
	
	@ExcelField(title="操作人员",sort=130,align=2)
	private String recordPeople;
	
	@ExcelField(title="备注",sort=140,align=2)
	private String remarks;
	
	public String getOutReCordNo() {
		return outReCordNo;
	}
	public void setOutReCordNo(String outReCordNo) {
		this.outReCordNo = outReCordNo;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getRecordPeople() {
		return recordPeople;
	}
	public void setRecordPeople(String recordPeople) {
		this.recordPeople = recordPeople;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
