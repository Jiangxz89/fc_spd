package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdIntoStockRecordExcel {
	
	@ExcelField(title="入库日期",sort=10,align=2)
	private String recordDate;		// 出入库时间
	
	@ExcelField(title="入库库房",sort=20,align=2)
	private String inId;		// 入库方主键
	
	@ExcelField(title="产品名称",sort=30,align=2)
	private String pdName;
	
	@ExcelField(title="产品编号",sort=40,align=2)
	private String number;		// 产品编码
	
	@ExcelField(title="产品条码",sort=50,align=2)
	private String productBarCode;		// 产品条码
	
	@ExcelField(title="规格",sort=60,align=2)
	private String spec;		// 规格
	
	@ExcelField(title="型号",sort=70,align=2)
	private String version;		// 型号
	
	@ExcelField(title="批号",sort=80,align=2)
	private String batchNo;
	
	@ExcelField(title="有效期",sort=90,align=2)
	private String limitDate;		// 有效期
	
	@ExcelField(title="数量",sort=100,align=2)
	private Integer productNum;		// 出入库数量
	
	@ExcelField(title="单位",sort=110,align=2)
	private String unit;		// 产品单位
	
	@ExcelField(title="单价",sort=120,align=2)
	private String sellingPrice;		// 出价
	
	@ExcelField(title="金额",sort=130,align=2)
	private String pdTotalPrice;
	
	@ExcelField(title="生产厂家",sort=140,align=2)
	private String venderName;     //生产厂家
	
	@ExcelField(title="订单号",sort=150,align=2)
	private String recordNo;		// 出入库单号
	
	@ExcelField(title="备注",sort=160,align=2)
	protected String remarks;	// 备注
	
	@ExcelField(title="入库类型",sort=170,align=2)
	private String inType;		// 入库类型 : 正常入库，退货入库，调拨入库
	
	@ExcelField(title="操作人",sort=180,align=2)
	private String recordPeople;		// 操作人

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
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

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
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

	public String getPdTotalPrice() {
		return pdTotalPrice;
	}

	public void setPdTotalPrice(String pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}

	public String getRecordPeople() {
		return recordPeople;
	}

	public void setRecordPeople(String recordPeople) {
		this.recordPeople = recordPeople;
	}
	
	
}
