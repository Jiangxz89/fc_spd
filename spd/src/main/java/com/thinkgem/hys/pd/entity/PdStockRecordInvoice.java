/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 出入库发票记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStockRecordInvoice extends DataEntity<PdStockRecordInvoice> {
	
	private static final long serialVersionUID = 1L;
	private String recordId;		// 入库记录ID
	private String invoiceDate;		// 发票日期
	private String invoiceNo;		// 发票编号
	private String invoiceAmount;		// 发票金额
	private String saleDate;		// 销售日期
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	public PdStockRecordInvoice() {
		super();
	}

	public PdStockRecordInvoice(String id){
		super(id);
	}

	@Length(min=0, max=64, message="记录ID长度必须介于 0 和 64 之间")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@Length(min=0, max=64, message="发票日期长度必须介于 0 和 64 之间")
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@Length(min=0, max=64, message="发票编号长度必须介于 0 和 64 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	@Length(min=0, max=64, message="销售日期长度必须介于 0 和 64 之间")
	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
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