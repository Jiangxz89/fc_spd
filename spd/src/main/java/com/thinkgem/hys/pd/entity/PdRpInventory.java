/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 进销存明细Entity
 * @author zxh
 * @version 2018-05-10
 */
public class PdRpInventory extends DataEntity<PdRpInventory> {
	
	private static final long serialVersionUID = 1L;
	private String storeroomId;		// 库房ID
	private String productId;		// 产品主键
	private String productNo;		// 产品编码
	private String prodBarcode;		// 产品条码
	private String stockNum;		// 库存
	private String rukNum;		// 入库数量
	private String zhengcNum;	//正常出库数
	private String diaocNum;		// 调出数量
	private String diaorNum;		// 调入数量
	private String tuihcNum;       //退货出库数量
	private String tuihrNum;		//退货入库数量
	private String shiyNum;		// 使用数量
	private String tuihNum;		// 退货数量
	private String yythNum;		//院外退货数量
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private Date recordingDate;//记录时间
	private String total;
	
	//冗余
	private String beginStockNum;//期初库存
	private String endStockNum;//期末库存
	private String productName;//产品名称
	private String version; //产品型号
	private String supplier;//供应商
	private String sellingPrice;//单价
	private String dosageAmount;//金额
	private String supplierId;//供应商id
	private Date queryStartDate;//查询开始时间
	private Date queryEndDate;//查询结束时间
	
	public PdRpInventory() {
		super();
	}

	public PdRpInventory(String id){
		super(id);
	}

	@Length(min=1, max=64, message="库房ID长度必须介于 1 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	
	@Length(min=1, max=64, message="产品主键长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=50, message="产品编码长度必须介于 1 和 50 之间")
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Length(min=0, max=30, message="产品条码长度必须介于 0 和 30 之间")
	public String getProdBarcode() {
		return prodBarcode;
	}

	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}
	
	@Length(min=0, max=3, message="库存长度必须介于 0 和 3 之间")
	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	
	@Length(min=0, max=3, message="入库数量长度必须介于 0 和 3 之间")
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

	@Length(min=0, max=3, message="调出数量长度必须介于 0 和 3 之间")
	public String getDiaocNum() {
		return diaocNum;
	}

	public void setDiaocNum(String diaocNum) {
		this.diaocNum = diaocNum;
	}
	
	@Length(min=0, max=3, message="调入数量长度必须介于 0 和 3 之间")
	public String getDiaorNum() {
		return diaorNum;
	}

	public void setDiaorNum(String diaorNum) {
		this.diaorNum = diaorNum;
	}
	
	@Length(min=0, max=3, message="使用数量长度必须介于 0 和 3 之间")
	public String getShiyNum() {
		return shiyNum;
	}

	public void setShiyNum(String shiyNum) {
		this.shiyNum = shiyNum;
	}
	
	@Length(min=0, max=3, message="退货数量长度必须介于 0 和 3 之间")
	public String getTuihNum() {
		return tuihNum;
	}

	public void setTuihNum(String tuihNum) {
		this.tuihNum = tuihNum;
	}
	
	@Length(min=0, max=200, message="extend1长度必须介于 0 和 200 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=100, message="extend2长度必须介于 0 和 100 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=100, message="extend3长度必须介于 0 和 100 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public String getYythNum() {
		return yythNum;
	}

	public void setYythNum(String yythNum) {
		this.yythNum = yythNum;
	}

	public String getBeginStockNum() {
		return beginStockNum;
	}

	public void setBeginStockNum(String beginStockNum) {
		this.beginStockNum = beginStockNum;
	}

	public String getEndStockNum() {
		return endStockNum;
	}

	public void setEndStockNum(String endStockNum) {
		this.endStockNum = endStockNum;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public Date getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(Date queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public Date getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
}