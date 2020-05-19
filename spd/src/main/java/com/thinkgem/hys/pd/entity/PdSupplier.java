/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 供应商信息Entity
 * @author changjundong
 * @version 2018-04-28
 */
public class PdSupplier extends DataEntity<PdSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String supplierName;		// 供应商名称
	private String pinyin;   //拼音简码
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String cardName1;		// 证件名
	private String cardCode1;		// 证件编号
	private Date validityTerm1;		// 有效期
	private String imgUrl1;		// 图片地址
	private String cardName2;		// 证件名
	private String cardCode2;		// 证件编号
	private Date validityTerm2;		// 有效期
	private String imgUrl2;		// 图片地址
	private String cardName3;		// 证件名
	private String cardCode3;		// 证件编号
	private Date validityTerm3;		// 有效期
	private String imgUrl3;		// 图片地址
	private String cardName4;		// 证件名
	private String cardCode4;		// 证件编号
	private Date validityTerm4;		// 有效期
	private String imgUrl4;		// 图片地址
	private String cardName5;		// 证件名
	private String cardCode5;		// 证件编号
	private Date validityTerm5;		// 有效期
	private String imgUrl5;		// 图片地址
	private String cardName6;		// 证件名
	private String cardCode6;		// 证件编号
	private Date validityTerm6;		// 有效期
	private String imgUrl6;		// 图片地址
	private String cardName7;		// 证件名
	private String cardCode7;		// 证件编号
	private Date validityTerm7;		// 有效期
	private String imgUrl7;		// 图片地址
	private String cardName8;		// 证件名
	private String cardCode8;		// 证件编号
	private Date validityTerm8;		// 有效期
	private String imgUrl8;		// 图片地址
	private String cardName9;		// 证件名
	private String cardCode9;		// 证件编号
	private Date validityTerm9;		// 有效期
	private String imgUrl9;		// 图片地址
	private String cardName10;		// 证件名
	private String cardCode10;		// 证件编号
	private Date validityTerm10;		// 有效期
	private String imgUrl10;		// 图片地址
	private String cardName11;		// 证件名
	private String cardCode11;		// 证件编号
	private Date validityTerm11;		// 有效期
	private String imgUrl11;		// 图片地址
	private String cardName12;		// 证件名
	private String cardCode12;		// 证件编号
	private Date validityTerm12;		// 有效期
	private String imgUrl12;		// 图片地址
	private String avlType;		// 有效期标识
	private String nameAndpinyin;   //用于下拉列表

	public String getAvlType() {
		return avlType;
	}

	public void setAvlType(String avlType) {
		this.avlType = avlType;
	}

	public PdSupplier() {
		super();
	}

	public PdSupplier(String id){
		super(id);
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName1() {
		return cardName1;
	}

	public String getNameAndpinyin() {
		return nameAndpinyin;
	}

	public void setNameAndpinyin(String nameAndpinyin) {
		this.nameAndpinyin = nameAndpinyin;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public void setCardName1(String cardName1) {
		this.cardName1 = cardName1;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode1() {
		return cardCode1;
	}

	public void setCardCode1(String cardCode1) {
		this.cardCode1 = cardCode1;
	}
	
	////@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm1() {
		return validityTerm1;
	}

	public void setValidityTerm1(Date validityTerm1) {
		this.validityTerm1 = validityTerm1;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl1() {
		return imgUrl1;
	}

	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName2() {
		return cardName2;
	}

	public void setCardName2(String cardName2) {
		this.cardName2 = cardName2;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode2() {
		return cardCode2;
	}

	public void setCardCode2(String cardCode2) {
		this.cardCode2 = cardCode2;
	}
	
	////@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm2() {
		return validityTerm2;
	}

	public void setValidityTerm2(Date validityTerm2) {
		this.validityTerm2 = validityTerm2;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl2() {
		return imgUrl2;
	}

	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName3() {
		return cardName3;
	}

	public void setCardName3(String cardName3) {
		this.cardName3 = cardName3;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode3() {
		return cardCode3;
	}

	public void setCardCode3(String cardCode3) {
		this.cardCode3 = cardCode3;
	}

	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm3() {
		return validityTerm3;
	}

	public void setValidityTerm3(Date validityTerm3) {
		this.validityTerm3 = validityTerm3;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl3() {
		return imgUrl3;
	}

	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName4() {
		return cardName4;
	}

	public void setCardName4(String cardName4) {
		this.cardName4 = cardName4;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode4() {
		return cardCode4;
	}

	public void setCardCode4(String cardCode4) {
		this.cardCode4 = cardCode4;
	}

	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm4() {
		return validityTerm4;
	}

	public void setValidityTerm4(Date validityTerm4) {
		this.validityTerm4 = validityTerm4;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl4() {
		return imgUrl4;
	}

	public void setImgUrl4(String imgUrl4) {
		this.imgUrl4 = imgUrl4;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName5() {
		return cardName5;
	}

	public void setCardName5(String cardName5) {
		this.cardName5 = cardName5;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode5() {
		return cardCode5;
	}

	public void setCardCode5(String cardCode5) {
		this.cardCode5 = cardCode5;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm5() {
		return validityTerm5;
	}

	public void setValidityTerm5(Date validityTerm5) {
		this.validityTerm5 = validityTerm5;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl5() {
		return imgUrl5;
	}

	public void setImgUrl5(String imgUrl5) {
		this.imgUrl5 = imgUrl5;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName6() {
		return cardName6;
	}

	public void setCardName6(String cardName6) {
		this.cardName6 = cardName6;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode6() {
		return cardCode6;
	}

	public void setCardCode6(String cardCode6) {
		this.cardCode6 = cardCode6;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm6() {
		return validityTerm6;
	}

	public void setValidityTerm6(Date validityTerm6) {
		this.validityTerm6 = validityTerm6;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl6() {
		return imgUrl6;
	}

	public void setImgUrl6(String imgUrl6) {
		this.imgUrl6 = imgUrl6;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName7() {
		return cardName7;
	}

	public void setCardName7(String cardName7) {
		this.cardName7 = cardName7;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode7() {
		return cardCode7;
	}

	public void setCardCode7(String cardCode7) {
		this.cardCode7 = cardCode7;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm7() {
		return validityTerm7;
	}

	public void setValidityTerm7(Date validityTerm7) {
		this.validityTerm7 = validityTerm7;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl7() {
		return imgUrl7;
	}

	public void setImgUrl7(String imgUrl7) {
		this.imgUrl7 = imgUrl7;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName8() {
		return cardName8;
	}

	public void setCardName8(String cardName8) {
		this.cardName8 = cardName8;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode8() {
		return cardCode8;
	}

	public void setCardCode8(String cardCode8) {
		this.cardCode8 = cardCode8;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm8() {
		return validityTerm8;
	}

	public void setValidityTerm8(Date validityTerm8) {
		this.validityTerm8 = validityTerm8;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl8() {
		return imgUrl8;
	}

	public void setImgUrl8(String imgUrl8) {
		this.imgUrl8 = imgUrl8;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName9() {
		return cardName9;
	}

	public void setCardName9(String cardName9) {
		this.cardName9 = cardName9;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode9() {
		return cardCode9;
	}

	public void setCardCode9(String cardCode9) {
		this.cardCode9 = cardCode9;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm9() {
		return validityTerm9;
	}

	public void setValidityTerm9(Date validityTerm9) {
		this.validityTerm9 = validityTerm9;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl9() {
		return imgUrl9;
	}

	public void setImgUrl9(String imgUrl9) {
		this.imgUrl9 = imgUrl9;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName10() {
		return cardName10;
	}

	public void setCardName10(String cardName10) {
		this.cardName10 = cardName10;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode10() {
		return cardCode10;
	}

	public void setCardCode10(String cardCode10) {
		this.cardCode10 = cardCode10;
	}
		
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm10() {
		return validityTerm10;
	}

	public void setValidityTerm10(Date validityTerm10) {
		this.validityTerm10 = validityTerm10;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl10() {
		return imgUrl10;
	}

	public void setImgUrl10(String imgUrl10) {
		this.imgUrl10 = imgUrl10;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName11() {
		return cardName11;
	}

	public void setCardName11(String cardName11) {
		this.cardName11 = cardName11;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode11() {
		return cardCode11;
	}

	public void setCardCode11(String cardCode11) {
		this.cardCode11 = cardCode11;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm11() {
		return validityTerm11;
	}

	public void setValidityTerm11(Date validityTerm11) {
		this.validityTerm11 = validityTerm11;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl11() {
		return imgUrl11;
	}

	public void setImgUrl11(String imgUrl11) {
		this.imgUrl11 = imgUrl11;
	}
	
	@Length(min=0, max=200, message="证件名长度必须介于 0 和 200 之间")
	public String getCardName12() {
		return cardName12;
	}

	public void setCardName12(String cardName12) {
		this.cardName12 = cardName12;
	}
	
	@Length(min=0, max=200, message="证件编号长度必须介于 0 和 200 之间")
	public String getCardCode12() {
		return cardCode12;
	}

	public void setCardCode12(String cardCode12) {
		this.cardCode12 = cardCode12;
	}
	
	//@Length(min=0, max=200, message="有效期长度必须介于 0 和 200 之间")
	public Date getValidityTerm12() {
		return validityTerm12;
	}

	public void setValidityTerm12(Date validityTerm12) {
		this.validityTerm12 = validityTerm12;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getImgUrl12() {
		return imgUrl12;
	}

	public void setImgUrl12(String imgUrl12) {
		this.imgUrl12 = imgUrl12;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	
	
	
}