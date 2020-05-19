/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 医院药品报损丢失单明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataDetailprofit extends DataEntity<DataDetailprofit> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 主记录ID
	private String stockid;		// 药品ID
	private String medname;		// 药品名称
	private String unit;		// 单位
	private String spec;		// 规格
	private String inprice;		// 购进价
	private String sellprice;		// 零售价
	private String batch;		// 批次
	private Date expidate;		// 有效期
	private String type;		// 类型
	private String qty;		// 损益数
	private String reason;		// 调整原因
	private String batchno;		// 批号
	
	public DataDetailprofit() {
		super();
	}

	public DataDetailprofit(String id){
		super(id);
	}

	@Length(min=1, max=11, message="主记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
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
	
	@Length(min=1, max=4, message="单位长度必须介于 1 和 4 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=1, max=100, message="规格长度必须介于 1 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}
	
	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	
	@Length(min=0, max=50, message="批次长度必须介于 0 和 50 之间")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="有效期不能为空")
	public Date getExpidate() {
		return expidate;
	}

	public void setExpidate(Date expidate) {
		this.expidate = expidate;
	}
	
	@Length(min=1, max=6, message="类型长度必须介于 1 和 6 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	@Length(min=1, max=50, message="调整原因长度必须介于 1 和 50 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
}