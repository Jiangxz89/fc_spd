/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 不良品记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdRejectsRecord extends DataEntity<PdRejectsRecord> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品主键
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条码
	private String validDate;		//有效期
	private String seriousGrade;		// 严重等级
	private String describeInfo;		// 描述内容
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String storeroomId;//库房ID
	
	//-temp
	private PdProduct pdProduct;
	private String inHospitalNo;
	//作为参数条件传值
	private String oprtType;
	
	public PdRejectsRecord() {
		super();
	}

	public PdRejectsRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=64, message="批次号长度必须介于 0 和 64 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=64, message="产品条码长度必须介于 0 和 64 之间")
	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}
	
	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	@Length(min=0, max=1, message="严重等级长度必须介于 0 和 1 之间")
	public String getSeriousGrade() {
		return seriousGrade;
	}

	public void setSeriousGrade(String seriousGrade) {
		this.seriousGrade = seriousGrade;
	}
	
	public String getDescribeInfo() {
		return describeInfo;
	}

	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
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

	public PdProduct getPdProduct() {
		return pdProduct;
	}

	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}

	public String getInHospitalNo() {
		return inHospitalNo;
	}

	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}

	public String getOprtType() {
		return oprtType;
	}

	public void setOprtType(String oprtType) {
		this.oprtType = oprtType;
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

}