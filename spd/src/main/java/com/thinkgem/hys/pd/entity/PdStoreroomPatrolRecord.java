/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 库房巡查记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStoreroomPatrolRecord extends DataEntity<PdStoreroomPatrolRecord> {
	
	private static final long serialVersionUID = 1L;
	private String patrolCode;		// 巡查编号
	private String productId;		// 产品主键
	private Integer stockNum; //记录时库存数量
	private String patrolResult;		// 巡查结果 : 合格，不合格，其他
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条码
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	//-temp
	private PdProduct pdProduct;//产品信息
	private String isExpire;//1、过期，0、即将过期
	private Date validDate;//有效期
	private String validDateStr;//有效期字符
	private String storeroomId;
	private String storeroomName;
	
	public PdStoreroomPatrolRecord() {
		super();
	}

	public PdStoreroomPatrolRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="巡查编号长度必须介于 0 和 64 之间")
	public String getPatrolCode() {
		return patrolCode;
	}

	public void setPatrolCode(String patrolCode) {
		this.patrolCode = patrolCode;
	}
	
	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=1, message="巡查结果 : 合格，不合格，其他长度必须介于 0 和 1 之间")
	public String getPatrolResult() {
		return patrolResult;
	}

	public void setPatrolResult(String patrolResult) {
		this.patrolResult = patrolResult;
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

	public String getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getValidDateStr() {
		if (validDate == null)
			return "";
		return DateUtils.formatDate(validDate, "yyyy-MM-dd");
	}

	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}

	public String getStoreroomName() {
		return storeroomName;
	}

	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}

}