/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 物流追溯信息Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStockLog extends DataEntity<PdStockLog> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品主键
	private String batchNo;		// 批次号
	private String productBarCode;		// 产品条形码
	private String productNum;		// 产品数量
	private String inFrom;		// 来源
	private String outTo;		// 去向
	private String logType;		//记录业务类型
	private String invoiceNo;	//单号
	private String patientInfo;//病人信息
	private String chargeDeptName;//收费科室
	private Date recordTime;//记录时间
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	//-temp
	private String[] timeStr = new String[]{"","",""}; 
	private String flowType;
	
	public PdStockLog() {
		super();
	}

	public PdStockLog(String id){
		super(id);
	}

	@Length(min=0, max=64, message="产品主键长度必须介于 0 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=64, message="批次号长度必须介于 0 和 64 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=64, message="产品条形码长度必须介于 0 和 64 之间")
	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}
	
	@Length(min=0, max=11, message="产品数量长度必须介于 0 和 11 之间")
	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	@Length(min=0, max=200, message="来源长度必须介于 0 和 200 之间")
	public String getInFrom() {
		return inFrom;
	}

	public void setInFrom(String inFrom) {
		this.inFrom = inFrom;
	}
	
	@Length(min=0, max=200, message="去向长度必须介于 0 和 200 之间")
	public String getOutTo() {
		return outTo;
	}

	public void setOutTo(String outTo) {
		this.outTo = outTo;
	}
	
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}

	public String getChargeDeptName() {
		return chargeDeptName;
	}

	public void setChargeDeptName(String chargeDeptName) {
		this.chargeDeptName = chargeDeptName;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
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

	//前台做展示用
	public String[] getTimeStr() {
		if(this.recordTime != null){
			this.timeStr[0] = DateUtils.formatDate(this.recordTime, "yyyy-MM-dd");
			this.timeStr[1] = DateUtils.getDayOfWeek(this.recordTime);
			this.timeStr[2] = DateUtils.formatDate(this.recordTime, "HH:mm:ss");
		}
		return timeStr;
	}
	
	public String getFlowType(){
		if(this.logType!=null && !"".equals(this.logType)){
			return DictUtils.getDictLabel(this.logType, "product_flow_type", "");
		}
		return "";
	}
	
}