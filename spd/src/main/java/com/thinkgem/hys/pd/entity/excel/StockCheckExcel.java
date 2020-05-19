package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class StockCheckExcel {
	@ExcelField(title="产品名称" , align = 2 , sort = 10)
	private String prodName;
	@ExcelField(title="产品编号" , align = 2 , sort = 20)
	private String prodNumber;
	@ExcelField(title="产品条码" , align = 2 , sort = 30)
	private String prodBarCode;
	@ExcelField(title="规格" , align = 2 , sort = 40)
	private String prodSpec;
	@ExcelField(title="批号" , align = 2 , sort = 50)
	private String prodBatchNo;
	@ExcelField(title="有效期" , align = 2 , sort = 60)
	private String prodValidDate;
	@ExcelField(title="单位" , align = 2 , sort = 70)
	private String prodUnit;
	@ExcelField(title="理论数量" , align = 2 , sort = 80)
	private String stockNum;
	@ExcelField(title="盘点数量" , align = 2 , sort = 90)
	private String actualCount;
	@ExcelField(title="盘盈盘亏" , align = 2 , sort = 100)
	private String profitLossCount;
	@ExcelField(title="备注" , align = 2 , sort = 110)
	private String remarks;
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
	public String getProdBatchNo() {
		return prodBatchNo;
	}
	public void setProdBatchNo(String prodBatchNo) {
		this.prodBatchNo = prodBatchNo;
	}
	public String getProdValidDate() {
		return prodValidDate;
	}
	public void setProdValidDate(String prodValidDate) {
		this.prodValidDate = prodValidDate;
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
	public String getActualCount() {
		return actualCount;
	}
	public void setActualCount(String actualCount) {
		this.actualCount = actualCount;
	}
	public String getProfitLossCount() {
		return profitLossCount;
	}
	public void setProfitLossCount(String profitLossCount) {
		this.profitLossCount = profitLossCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
