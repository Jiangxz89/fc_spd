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
 * 申购单详细Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdPurchaseDetail extends DataEntity<PdPurchaseDetail> {

	private static final long serialVersionUID = 1L;
	private String orderNo;		// 申购单号
	private String prodId;		// 产品ID
	private String prodNo;		// 产品编码
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private Double inPrice;     // 采购价（入库价格） add by jiangxz 20190923
	private Integer applyCount;		// 申领数量
	private Double amountMoney;		// 金额
	private Integer stockNum;//申请时产品库存
	private String tpProdId;//交易平台产品ID
	//--temp
	private String prodName;//产品名
	private String prodSpec;//产品规格
	private String prodVersion;//产品型号
	private String prodUnit;//产品单位
	private String prodPrice;//产品价格
	private String storeroomId;//库房ID
	private String myStockNum;//本科室库存
	private String supplierName;//供应商名称
	private String venderName;		// 生产厂家名称

	private List<String> orderNos;		// 申购单号组

	public String getTpProdId() {
		return tpProdId;
	}

	public void setTpProdId(String tpProdId) {
		this.tpProdId = tpProdId;
	}

	public PdPurchaseDetail() {
		super();
	}

	public PdPurchaseDetail(String id){
		super(id);
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	@Length(min=0, max=64, message="申购单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
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

	public String getProdUnit() {
		return prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}


	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getMyStockNum() {
		return myStockNum;
	}

	public void setMyStockNum(String myStockNum) {
		this.myStockNum = myStockNum;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}