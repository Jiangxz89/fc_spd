/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 库存流水Entity
 * @author lww
 * @version 2018-03-28
 */
public class PdProductStockFlow extends DataEntity<PdProductStockFlow> {
	
	private static final long serialVersionUID = 1L;
	private String recordId;		// 出入库id : 出入库id
	private Date recordDate;		// 出入库日期 : 出入库日期
	private String recordType;		// 出入库类型 : 0入库，1出库
	private String flowNum;		// 出入库数量 : 出入库数量
	private String productId;		// 产品id
	private String storeroomId;		// 库房id : 库房id
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	public PdProductStockFlow() {
		super();
	}

	public PdProductStockFlow(String id){
		super(id);
	}

	@Length(min=0, max=64, message="出入库id : 出入库id长度必须介于 0 和 64 之间")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	@Length(min=0, max=64, message="出入库类型 : 0入库，1出库长度必须介于 0 和 64 之间")
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	@Length(min=0, max=64, message="出入库数量 : 出入库数量长度必须介于 0 和 64 之间")
	public String getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(String flowNum) {
		this.flowNum = flowNum;
	}
	
	@Length(min=0, max=200, message="产品id长度必须介于 0 和 200 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=200, message="库房id : 库房id长度必须介于 0 和 200 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
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