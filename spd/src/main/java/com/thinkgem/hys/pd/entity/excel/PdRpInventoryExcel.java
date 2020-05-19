package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class PdRpInventoryExcel {
	@ExcelField(title="供应商",sort=10,align=2)
	private String supplier;//供应商
	@ExcelField(title="产品名称",sort=20,align=2)
	private String productName;//产品名称
	@ExcelField(title="型号",sort=30,align=2)
	private String version; //产品型号
	@ExcelField(title="期初库存",sort=40,align=2)
	private String beginStockNum;//期初库存
	@ExcelField(title="本期入库",sort=50,align=2)
	private String rukNum;		// 入库数量
	@ExcelField(title="本期出库",sort=60,align=2)
	private String zhengcNum;	//正常出库数
	@ExcelField(title="调出数量",sort=70,align=2)
	private String diaocNum;		// 调出数量
	@ExcelField(title="调入数量",sort=80,align=2)
	private String diaorNum;		// 调入数量
	@ExcelField(title="退货出库",sort=90,align=2)
	private String tuihcNum;       //退货出库数量
	@ExcelField(title="退货入库",sort=100,align=2)
	private String tuihrNum;		//退货入库数量
	@ExcelField(title="用量数量",sort=110,align=2)
	private String shiyNum;		// 使用数量
	@ExcelField(title="用量退回",sort=120,align=2)
	private String tuihNum;		// 退货数量
	@ExcelField(title="院外退货",sort=130,align=2)
	private String yythNum;		//院外退货数量
	@ExcelField(title="单价",sort=140,align=2)
	private String sellingPrice;//单价
	@ExcelField(title="用量金额",sort=150,align=2)
	private String dosageAmount;//金额
	@ExcelField(title="期末库存",sort=160,align=2)
	private String endStockNum;//期末库存
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBeginStockNum() {
		return beginStockNum;
	}
	public void setBeginStockNum(String beginStockNum) {
		this.beginStockNum = beginStockNum;
	}
	public String getRukNum() {
		return rukNum;
	}
	public void setRukNum(String rukNum) {
		this.rukNum = rukNum;
	}
	public String getZhengcNum() {
		return zhengcNum;
	}
	public void setZhengcNum(String zhengcNum) {
		this.zhengcNum = zhengcNum;
	}
	public String getDiaocNum() {
		return diaocNum;
	}
	public void setDiaocNum(String diaocNum) {
		this.diaocNum = diaocNum;
	}
	public String getDiaorNum() {
		return diaorNum;
	}
	public void setDiaorNum(String diaorNum) {
		this.diaorNum = diaorNum;
	}
	public String getTuihcNum() {
		return tuihcNum;
	}
	public void setTuihcNum(String tuihcNum) {
		this.tuihcNum = tuihcNum;
	}
	public String getTuihrNum() {
		return tuihrNum;
	}
	public void setTuihrNum(String tuihrNum) {
		this.tuihrNum = tuihrNum;
	}
	public String getShiyNum() {
		return shiyNum;
	}
	public void setShiyNum(String shiyNum) {
		this.shiyNum = shiyNum;
	}
	public String getTuihNum() {
		return tuihNum;
	}
	public void setTuihNum(String tuihNum) {
		this.tuihNum = tuihNum;
	}
	public String getYythNum() {
		return yythNum;
	}
	public void setYythNum(String yythNum) {
		this.yythNum = yythNum;
	}
	public String getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getDosageAmount() {
		return dosageAmount;
	}
	public void setDosageAmount(String dosageAmount) {
		this.dosageAmount = dosageAmount;
	}
	public String getEndStockNum() {
		return endStockNum;
	}
	public void setEndStockNum(String endStockNum) {
		this.endStockNum = endStockNum;
	}
	
	
}
