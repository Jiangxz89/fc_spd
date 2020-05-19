/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退货明细信息Entity
 * @author yueguoyun
 * @version 2018-04-29
 */
public class PdRejectedDetail extends DataEntity<PdRejectedDetail> {
	
	private static final long serialVersionUID = 1L;
	private String rejectedId;		// 关联头部id
	private String productId;		// product_id
	private String productNum;		// product_id
	private String productName;		// product_name
	private String productNo;		// product_no
	private String productBarCode;		// product_bar_code
	private String batchNo;		// batch_no
	private Date validDate;		// valid_date
	private int rejectedNum;		// 退货数量
	
	public PdRejectedDetail() {
		super();
	}

	public PdRejectedDetail(String id){
		super(id);
	}

	@Length(min=1, max=64, message="关联头部id长度必须介于 1 和 64 之间")
	public String getRejectedId() {
		return rejectedId;
	}

	public void setRejectedId(String rejectedId) {
		this.rejectedId = rejectedId;
	}
	
	@Length(min=1, max=64, message="product_id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	@Length(min=0, max=100, message="product_name长度必须介于 0 和 100 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=1, max=50, message="product_no长度必须介于 1 和 50 之间")
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Length(min=1, max=64, message="product_bar_code长度必须介于 1 和 64 之间")
	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}
	
	@Length(min=1, max=30, message="batch_no长度必须介于 1 和 30 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	public int getRejectedNum() {
		return rejectedNum;
	}

	public void setRejectedNum(int rejectedNum) {
		this.rejectedNum = rejectedNum;
	}
	
}