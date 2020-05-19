package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class CourtWithinOutDtlExcel {

	@ExcelField(title="退货单号",sort=10,align=2)
	private String recordNo;		// 出入库单号
	
	@ExcelField(title="退货日期",sort=20,align=2)
	private String recordDate;		// 出入库时间
	
	@ExcelField(title="退库库房",sort=30,align=2)
	private String outId;		// 出库方主键
	
	@ExcelField(title="入库库房",sort=40,align=2)
	private String inId;		// 入库方主键
	
	@ExcelField(title="产品名称",sort=50,align=2)
	private String pdName;
	
	@ExcelField(title="产品编号",sort=60,align=2)
	private String number;		// 产品编码
	
	@ExcelField(title="产品条码",sort=70,align=2)
	private String productBarCode;		// 产品条码
	
	@ExcelField(title="规格",sort=80,align=2)
	private String spec;		// 规格
	
	@ExcelField(title="型号",sort=90,align=2)
	private String version;		// 型号
	
	@ExcelField(title="批号",sort=100,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=110,align=2)
	private String limitDate;		// 有效期
	
	@ExcelField(title="数量",sort=120,align=2)
	private String productNum;		// 出入库数量
	
	@ExcelField(title="操作人",sort=130,align=2)
	private String recordPeople;		// 操作人
	
	@ExcelField(title="备注",sort=140,align=2)
	private String remarks;
	
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
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
