/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 调拨记录Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdAllocationRecord extends DataEntity<PdAllocationRecord> {
	
	private static final long serialVersionUID = 1L;
	private String allocationCode;		// 调拨单编号
	private Date allocationDate;		// 调拨日期
	private String recordPeople;		// 操作人
	private String outId;		// 出库库房主键
	private String outName;		// 出库库房名称
	private String inId;		// 入库库房主键
	private String inName;		// 入库库房名称
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条码
	private String allocationNum;		// 调拨数量
	private String priceAmount;		// 金额
	private String recordState;		// 记录状态 : 待审核；已通过；已拒绝
	private String rejectReason;		// 驳回原因
	private String checkPeople;		//审核人员
	private Date checkDate;		//审核日期
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private List<PdAllocationProduct> pdAllocationProductList;
	
	//供查询使用
	private String sectionDate; //选择日期
	private String productName;  //产品名称
	
	//多条件查询
	private String recordStates;
	private List<String> stateList = new ArrayList<String>();
	
	public PdAllocationRecord() {
		super();
	}

	public PdAllocationRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="调拨单编号长度必须介于 0 和 64 之间")
	public String getAllocationCode() {
		return allocationCode;
	}

	public void setAllocationCode(String allocationCode) {
		this.allocationCode = allocationCode;
	}
	
	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	@Length(min=0, max=64, message="操作人长度必须介于 0 和 64 之间")
	public String getRecordPeople() {
		return recordPeople;
	}

	public void setRecordPeople(String recordPeople) {
		this.recordPeople = recordPeople;
	}
	
	@Length(min=0, max=64, message="出库库房主键长度必须介于 0 和 64 之间")
	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}
	
	@Length(min=0, max=64, message="入库库房主键长度必须介于 0 和 64 之间")
	public String getInId() {
		return inId;
	}

	public void setInId(String inId) {
		this.inId = inId;
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
	
	@Length(min=0, max=11, message="调拨数量长度必须介于 0 和 11 之间")
	public String getAllocationNum() {
		return allocationNum;
	}

	public void setAllocationNum(String allocationNum) {
		this.allocationNum = allocationNum;
	}
	
	public String getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(String priceAmount) {
		this.priceAmount = priceAmount;
	}
	
	@Length(min=0, max=1, message="记录状态 : 待审核；已通过；已拒绝长度必须介于 0 和 1 之间")
	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSectionDate() {
		return sectionDate;
	}

	public void setSectionDate(String sectionDate) {
		this.sectionDate = sectionDate;
	}

	public List<PdAllocationProduct> getPdAllocationProductList() {
		return pdAllocationProductList;
	}

	public void setPdAllocationProductList(
			List<PdAllocationProduct> pdAllocationProductList) {
		this.pdAllocationProductList = pdAllocationProductList;
	}

	public String getCheckPeople() {
		return checkPeople;
	}

	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getRecordStates() {
		return recordStates;
	}

	public void setRecordStates(String recordStates) {
		this.recordStates = recordStates;
	}

	public List<String> getStateList() {
		if(StringUtils.isNotBlank(recordStates)){
			String[] stateArr = recordStates.split(",");
			for (String state : stateArr) {
				stateList.add(state);
			}
		}
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}
	
}