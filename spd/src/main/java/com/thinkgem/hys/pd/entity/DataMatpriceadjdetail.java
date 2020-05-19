/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品调价单明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataMatpriceadjdetail extends DataEntity<DataMatpriceadjdetail> {
	
	private static final long serialVersionUID = 1L;
	private String priceadjid;		// 明细ID
	private String chamedid;		// 主记录ID
	private String stockid;		// 药品ID
	private String medname;		// 药品名称
	private String qty;		// 调价数量
	private String unit;		// 单位
	private String oldbprice;		// 原购进价
	private String newbprice;		// 新购进价
	private String oldsprice;		// 原零售价
	private String newsprice;		// 新零售价
	private String batch;		// 批次
	private String batchno;		// 批号
	
	public DataMatpriceadjdetail() {
		super();
	}

	public DataMatpriceadjdetail(String id){
		super(id);
	}

	@Length(min=1, max=11, message="明细ID长度必须介于 1 和 11 之间")
	public String getPriceadjid() {
		return priceadjid;
	}

	public void setPriceadjid(String priceadjid) {
		this.priceadjid = priceadjid;
	}
	
	@Length(min=1, max=11, message="主记录ID长度必须介于 1 和 11 之间")
	public String getChamedid() {
		return chamedid;
	}

	public void setChamedid(String chamedid) {
		this.chamedid = chamedid;
	}
	
	@Length(min=1, max=11, message="药品ID长度必须介于 1 和 11 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	@Length(min=1, max=60, message="药品名称长度必须介于 1 和 60 之间")
	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}
	
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	@Length(min=1, max=4, message="单位长度必须介于 1 和 4 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getOldbprice() {
		return oldbprice;
	}

	public void setOldbprice(String oldbprice) {
		this.oldbprice = oldbprice;
	}
	
	public String getNewbprice() {
		return newbprice;
	}

	public void setNewbprice(String newbprice) {
		this.newbprice = newbprice;
	}
	
	public String getOldsprice() {
		return oldsprice;
	}

	public void setOldsprice(String oldsprice) {
		this.oldsprice = oldsprice;
	}
	
	public String getNewsprice() {
		return newsprice;
	}

	public void setNewsprice(String newsprice) {
		this.newsprice = newsprice;
	}
	
	@Length(min=0, max=30, message="批次长度必须介于 0 和 30 之间")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
}