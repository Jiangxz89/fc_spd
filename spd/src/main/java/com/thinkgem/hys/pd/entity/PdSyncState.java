/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 同步中心平台状态表Entity
 * @author zxh
 * @version 2018-07-30
 */
public class PdSyncState extends DataEntity<PdSyncState> {
	
	private static final long serialVersionUID = 1L;
	private String purchaseOrderState;		// 采购单数据提交状态
	private String stockRecordState;		// 耗材订单及其配送状态
	private String rtProductState;		// 退货数据提交状态
	private Date syncDate;		// 同步时间
	
	public PdSyncState() {
		super();
	}

	public PdSyncState(String id){
		super(id);
	}

	@Length(min=0, max=1, message="采购单数据提交状态长度必须介于 0 和 1 之间")
	public String getPurchaseOrderState() {
		return purchaseOrderState;
	}

	public void setPurchaseOrderState(String purchaseOrderState) {
		this.purchaseOrderState = purchaseOrderState;
	}
	
	@Length(min=0, max=1, message="耗材订单及其配送状态长度必须介于 0 和 1 之间")
	public String getStockRecordState() {
		return stockRecordState;
	}

	public void setStockRecordState(String stockRecordState) {
		this.stockRecordState = stockRecordState;
	}
	
	@Length(min=0, max=1, message="退货数据提交状态长度必须介于 0 和 1 之间")
	public String getRtProductState() {
		return rtProductState;
	}

	public void setRtProductState(String rtProductState) {
		this.rtProductState = rtProductState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	
}