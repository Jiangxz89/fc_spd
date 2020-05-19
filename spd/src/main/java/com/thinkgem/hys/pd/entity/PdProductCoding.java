/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品赋码表Entity
 * @author zxh
 * @version 2019-05-16
 */
public class PdProductCoding extends DataEntity<PdProductCoding> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 编码
	private String prodBarcode;		// 产品二级条码
	private String rfidCode;		// RFID码
	private String productId;		// 产品id
	private String batchNo; //批次号
	private String limitDate;//有效期

	//冗余
	private String productName; //产品名称
	private String venderNameShow; //生产厂家
	private String spec; //生产厂家
	private String version; //生产厂家

	
	public PdProductCoding() {
		super();
	}

	public PdProductCoding(String id){
		super(id);
	}

	@Length(min=1, max=100, message="编码长度必须介于 1 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=1, max=100, message="产品二级条码长度必须介于 1 和 100 之间")
	public String getProdBarcode() {
		return prodBarcode;
	}

	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}
	
	@Length(min=1, max=100, message="RFID码长度必须介于 1 和 100 之间")
	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}
	
	@Length(min=1, max=64, message="产品id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVenderNameShow() {
		return venderNameShow;
	}

	public void setVenderNameShow(String venderNameShow) {
		this.venderNameShow = venderNameShow;
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
}