/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 申购单Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdPurchaseOrder extends DataEntity<PdPurchaseOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 申购单号
	private String purchaseBy;		// 申购人
	private Date orderDate;		// 申购日期
	private String deptId;		// 申购科室ID
	private String deptName;		// 申购科室名称
	private String orderStatus;		// 申购单状态
	private String mergeOrderNo;	//合并申购单号
	private String auditBy;		// 审核人
	private Date auditDate;		// 审核时间
	private String refuseReason;		// 拒绝理由
	private Integer amountCount;		// 总数量
	private Double amountMoney;		// 总金额
	
	//temp
	private String prodName;//品名
	private String prodSpec;//规格
	private String prodVersion;//型号
	
	private String purchaseName;//采购人
	private String auditName;//审核人
	
	private String isPurchaseAudit;//来自采购审核菜单
	
	private List<PdPurchaseDetail> purchaseDetailList;
	
	public PdPurchaseOrder() {
		super();
	}

	public PdPurchaseOrder(String id){
		super(id);
	}

	@Length(min=1, max=64, message="申购单号长度必须介于 1 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="申购人长度必须介于 0 和 64 之间")
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Length(min=0, max=64, message="申购科室ID长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=20, message="申购科室名称长度必须介于 0 和 20 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=1, message="申购单状态长度必须介于 0 和 1 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getMergeOrderNo() {
		return mergeOrderNo;
	}

	public void setMergeOrderNo(String mergeOrderNo) {
		this.mergeOrderNo = mergeOrderNo;
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

	public Integer getAmountCount() {
		return amountCount;
	}

	public void setAmountCount(Integer amountCount) {
		this.amountCount = amountCount;
	}

	public Double getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public List<PdPurchaseDetail> getPurchaseDetailList() {
		return purchaseDetailList;
	}

	public void setPurchaseDetailList(List<PdPurchaseDetail> purchaseDetailList) {
		this.purchaseDetailList = purchaseDetailList;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public static String dealStrData(String data){
		String newStr = data.replaceAll(",", "','");
		return "('"+newStr+"')";
	}
	
	public String getPurchaseDate(){
		if (this.orderDate != null) {
			return DateUtils.formatDate(this.orderDate, "yyyy-MM-dd");
		}
		return "";
	}

	public String getIsPurchaseAudit() {
		return isPurchaseAudit;
	}

	public void setIsPurchaseAudit(String isPurchaseAudit) {
		this.isPurchaseAudit = isPurchaseAudit;
	}

	public String getProdVersion() {
		return prodVersion;
	}

	public void setProdVersion(String prodVersion) {
		this.prodVersion = prodVersion;
	}
	
}