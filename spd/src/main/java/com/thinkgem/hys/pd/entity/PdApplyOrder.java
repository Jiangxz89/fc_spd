/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 申领单Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdApplyOrder extends DataEntity<PdApplyOrder> {
	
	private static final long serialVersionUID = 1L;
	private String applyNo;		// 申领单号
	private String applyBy;		// 申领人
	private Date applyDate;		// 申领日期
	private String deptId;		// 申领科室ID
	private String deptName;		// 申领科室名称
	private Integer applyCount;		// 申领数量
	private Integer factCount;		// 实领数量
	private String applyStatus;		// 申领单状态
	private String auditBy;		// 审核人
	private Date auditDate;		// 审核时间
	private String refuseReason;		// 拒绝理由
	private String isInRefund;		//是否在退货中
	private String isEnd;//是否完结，1是，0否
	
	//-temp
	private List<PdApplyDetail> applyDetailList;
	private String sectionDate; //选择日期;
	private String applyName;//申请人
	private String auditName;//审核人
	
	private String multiFlag;//复合查询标识
	
	//多条件查询使用
	private String applyStatuses;		// 申领单状态,“,”号分隔
	private List<String> applyStatusList = new ArrayList<String>();		// 申领单状态

	public PdApplyOrder() {
		super();
	}

	public PdApplyOrder(String id){
		super(id);
	}

	@Length(min=1, max=64, message="申领单号长度必须介于 1 和 64 之间")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	@Length(min=0, max=64, message="申领人长度必须介于 0 和 64 之间")
	public String getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=0, max=64, message="申领科室ID长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=20, message="申领科室名称长度必须介于 0 和 20 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Integer getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getFactCount() {
		return factCount;
	}

	public void setFactCount(Integer factCount) {
		this.factCount = factCount;
	}

	@Length(min=0, max=1, message="申领单状态长度必须介于 0 和 1 之间")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Length(min=0, max=64, message="审核人长度必须介于 0 和 64 之间")
	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Length(min=0, max=100, message="拒绝理由长度必须介于 0 和 100 之间")
	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getIsInRefund() {
		return isInRefund;
	}

	public void setIsInRefund(String isInRefund) {
		this.isInRefund = isInRefund;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public List<PdApplyDetail> getApplyDetailList() {
		return applyDetailList;
	}

	public void setApplyDetailList(List<PdApplyDetail> applyDetailList) {
		this.applyDetailList = applyDetailList;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getSectionDate() {
		return sectionDate;
	}

	public void setSectionDate(String sectionDate) {
		this.sectionDate = sectionDate;
	}
	
	public String getApplyStatuses() {
		return applyStatuses;
	}

	public void setApplyStatuses(String applyStatuses) {
		this.applyStatuses = applyStatuses;
		if(StringUtils.isNotEmpty(applyStatuses)){
			String[] statuses = applyStatuses.split(",");
			for(int i=0;i<statuses.length;i++){
				applyStatusList.add(statuses[i]);
			}
		}
	}

	public List<String> getApplyStatusList() {
		return applyStatusList;
	}

	public void setApplyStatusList(List<String> applyStatusList) {
		this.applyStatusList = applyStatusList;
	}

	public String getMultiFlag() {
		return multiFlag;
	}

	public void setMultiFlag(String multiFlag) {
		this.multiFlag = multiFlag;
	}
	
}