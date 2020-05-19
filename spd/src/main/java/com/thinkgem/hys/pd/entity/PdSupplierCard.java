/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商证件信息Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdSupplierCard extends DataEntity<PdSupplierCard> {
	
	private static final long serialVersionUID = 1L;
	private String cardName;		// 证件名
	private String cardCode;		// 证件编号
	private String validityTerm;		// 有效期
	private String imgUrl;			//图片路径
	private String supplierId;		//供应商主键
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	public PdSupplierCard() {
		super();
	}

	public PdSupplierCard(String id){
		super(id);
	}

	@Length(min=0, max=100, message="证件名长度必须介于 0 和 100 之间")
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	@Length(min=0, max=64, message="证件编号长度必须介于 0 和 64 之间")
	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	
	@Length(min=0, max=64, message="有效期长度必须介于 0 和 64 之间")
	public String getValidityTerm() {
		return validityTerm;
	}

	public void setValidityTerm(String validityTerm) {
		this.validityTerm = validityTerm;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


}