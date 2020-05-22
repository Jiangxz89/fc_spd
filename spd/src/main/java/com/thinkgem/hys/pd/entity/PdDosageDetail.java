/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 器械用量详情Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdDosageDetail extends DataEntity<PdDosageDetail> {
	
	private static final long serialVersionUID = 1L;
	private String dosageNo;		// 用量单号
	private String prodId;		// 产品ID
	private String prodNo;		// 产品编码
	private String prodBarcode;		// 产品条码
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private Integer dosageCount;		// 用量数量
	private Integer stockNum;	//添加用量时库存
	private Integer leftRefundNum;	//剩余可退数量
	private Double amountMoney;		// 金额
	// 是否执行收费   0未执行收费，库存已经扣减，1已经收费库存已经扣减，2，已经退费，库存已经还回，只是库存还回
	private String isCharge;
	private String chargeCode;//收费代码
	private String isCharges;		// 产品是否计费标识
	//-temp
	private String singlePrice;//产品单价
	private PdProduct pdProduct;
	private String productBarCode;
	private String storeroomId;
	private String prodType;//1、高值；0、低值
	
	//查询条件控制变量
	private String cancleCharge;

	private String hisChargeId;
	private String hisChargeItemId;
	
	private List<PdDosageDetail> detailList;
	
	public PdDosageDetail() {
		super();
	}

	public PdDosageDetail(String id){
		super(id);
	}

	public String getHisChargeId() {
		return hisChargeId;
	}

	public void setHisChargeId(String hisChargeId) {
		this.hisChargeId = hisChargeId;
	}

	public String getHisChargeItemId() {
		return hisChargeItemId;
	}

	public void setHisChargeItemId(String hisChargeItemId) {
		this.hisChargeItemId = hisChargeItemId;
	}

	@Length(min=0, max=64, message="用量单号长度必须介于 0 和 64 之间")
	public String getDosageNo() {
		return dosageNo;
	}

	public void setDosageNo(String dosageNo) {
		this.dosageNo = dosageNo;
	}
	
	@Length(min=0, max=64, message="产品ID长度必须介于 0 和 64 之间")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	@Length(min=0, max=100, message="产品编码长度必须介于 0 和 100 之间")
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	@Length(min=0, max=100, message="产品条码长度必须介于 0 和 100 之间")
	public String getProdBarcode() {
		return prodBarcode;
	}

	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}
	
	@Length(min=0, max=100, message="批号长度必须介于 0 和 100 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public Integer getDosageCount() {
		return dosageCount;
	}

	public void setDosageCount(Integer dosageCount) {
		this.dosageCount = dosageCount;
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}

	@Length(min=0, max=1, message="是否执行收费长度必须介于 0 和 1 之间")
	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public String getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getLeftRefundNum() {
		return leftRefundNum;
	}

	public void setLeftRefundNum(Integer leftRefundNum) {
		this.leftRefundNum = leftRefundNum;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getCancleCharge() {
		return cancleCharge;
	}

	public void setCancleCharge(String cancleCharge) {
		this.cancleCharge = cancleCharge;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public List<PdDosageDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PdDosageDetail> detailList) {
		this.detailList = detailList;
	}

	public String getIsCharges() {
		return isCharges;
	}

	public void setIsCharges(String isCharges) {
		this.isCharges = isCharges;
	}
}