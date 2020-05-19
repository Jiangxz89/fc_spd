/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用量退回产品信息Entity
 * @author yueguoyun
 * @version 2018-03-31
 */
public class PdDosagertDetail extends DataEntity<PdDosagertDetail> {
	
	private static final long serialVersionUID = 1L;
	private String dosagertId;		// 用量退回主表ID
	private String prodId;		// 产品ID
	private String prodNo;		// 产品编码
	private String prodBarcode;		// 产品条码
	private String prodName;		// 产品名称
	private String prodSpec;		//规格
	private String prodUnit;		//单位
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private Integer rtCount;		// 退回数量
	private Integer dosageCount;  // 用量数量
	private Double unitPrice;		// 产品单价
	private Double amountMoney;		// 金额
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String isRefund;
	
	//--temp
	private PdProduct pdProduct;
	private String dosagertNo;
	private String dosageNo;
	private String version;
	//器械详情表主键
	private String dosageDetailId;
	//收费代码
	private String chargeCode;
	
	
	public String getDosageNo() {
		return dosageNo;
	}

	public void setDosageNo(String dosageNo) {
		this.dosageNo = dosageNo;
	}

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public PdDosagertDetail() {
		super();
	}

	public PdDosagertDetail(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用量退回主表ID长度必须介于 1 和 64 之间")
	public String getDosagertId() {
		return dosagertId;
	}

	public void setDosagertId(String dosagertId) {
		this.dosagertId = dosagertId;
	}
	
	@Length(min=1, max=64, message="产品ID长度必须介于 1 和 64 之间")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	@Length(min=0, max=50, message="产品编码长度必须介于 0 和 50 之间")
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public String getProdUnit() {
		return prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	@Length(min=0, max=30, message="产品条码长度必须介于 0 和 30 之间")
	public String getProdBarcode() {
		return prodBarcode;
	}

	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}
	
	@Length(min=1, max=64, message="产品名称长度必须介于 1 和 64 之间")
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
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
	
	public Integer getRtCount() {
		return rtCount;
	}

	public void setRtCount(Integer rtCount) {
		this.rtCount = rtCount;
	}
	
	public Integer getDosageCount() {
		return dosageCount;
	}

	public void setDosageCount(Integer dosageCount) {
		this.dosageCount = dosageCount;
	}
	

	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
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

	public String getDosagertNo() {
		return dosagertNo;
	}
	
	public void setDosagertNo(String dosagertNo) {
		this.dosagertNo = dosagertNo;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDosageDetailId() {
		return dosageDetailId;
	}

	public void setDosageDetailId(String dosageDetailId) {
		this.dosageDetailId = dosageDetailId;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	

	
	
	
}