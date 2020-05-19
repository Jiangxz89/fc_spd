/**
s * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退货单子单表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdRejectedListChild extends DataEntity<PdRejectedListChild> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String headId;		// head_id
	private String productStockId;		// 产品库存id
	private int rejectedCount;		// 退货数量
	private int stockNum;		//原有库存数量
	private PdProduct productBean;	//产品实体
	private PdProductStock	productStockBean;	//库存实体
	
	//--temp
	private String prodId;
	private String prodNo;
	private String batchNo;
	private String barcode;
	
	
	
	
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public PdProduct getProductBean() {
		return productBean;
	}

	public void setProductBean(PdProduct productBean) {
		this.productBean = productBean;
	}

	public PdProductStock getProductStockBean() {
		return productStockBean;
	}

	public void setProductStockBean(PdProductStock productStockBean) {
		this.productStockBean = productStockBean;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public PdRejectedListChild() {
		super();
	}

	public PdRejectedListChild(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=64, message="head_id长度必须介于 0 和 64 之间")
	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}

	

	public String getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(String productStockId) {
		this.productStockId = productStockId;
	}

	public int getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(int rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	
	
	
}