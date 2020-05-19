/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用量退回信息Entity
 * @author yueguoyun
 * @version 2018-03-31
 */
public class PdDosagert extends DataEntity<PdDosagert> {
	
	private static final long serialVersionUID = 1L;
	private String dosagertRoomId;		// 用量退回库房
	private String dosagertNo;		// 用量退回单号
	private String dosagertInroomId;		// 退入库房ID
	private Date dosagertDate;		// 用量退回时间
	private String inHospitalNo;		// 用量退回病人住院号
	private String patientName;		// 用量退回病人姓名
	private String isRefund;		// 是否执行退费
	private Integer dosagertCount;		// 用量退回数量
	private BigDecimal dosagertMoney;		// 用量退回金额
	private String operaterName;		// 操作人姓名
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String dosagertState;  //用量退回状态    1 ：已导入
	
	private String dosageNumber;//用量单号2019年8月30日16:17:19
	//冗余
	private String prodName;		// 产品名称
	private String prodNo;		// 产品编码
	private String prodBarcode;		// 产品条码
	private String prodSpec;		//规格
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private Integer dosageCount;  // 用量数量
	private String version;		// 型号
	private Integer rtCount; //退回数量
	
	private List<PdDosagertDetail> detailList;	  //用量退回明细list
	
	public PdDosagert() {
		super();
	}

	public PdDosagert(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用量退回库房长度必须介于 1 和 64 之间")
	public String getDosagertRoomId() {
		return dosagertRoomId;
	}

	public void setDosagertRoomId(String dosagertRoomId) {
		this.dosagertRoomId = dosagertRoomId;
	}
	
	@Length(min=1, max=64, message="用量退回单号长度必须介于 1 和 64 之间")
	public String getDosagertNo() {
		return dosagertNo;
	}

	public void setDosagertNo(String dosagertNo) {
		this.dosagertNo = dosagertNo;
	}
	
	@Length(min=1, max=64, message="退入库房ID长度必须介于 1 和 64 之间")
	public String getDosagertInroomId() {
		return dosagertInroomId;
	}

	public void setDosagertInroomId(String dosagertInroomId) {
		this.dosagertInroomId = dosagertInroomId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDosagertDate() {
		return dosagertDate;
	}

	public void setDosagertDate(Date dosagertDate) {
		this.dosagertDate = dosagertDate;
	}
	
	@Length(min=1, max=64, message="用量退回病人住院号长度必须介于 1 和 64 之间")
	public String getInHospitalNo() {
		return inHospitalNo;
	}

	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}
	
	@Length(min=0, max=50, message="用量退回病人姓名长度必须介于 0 和 50 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=1, max=1, message="是否执行退费长度必须介于 1 和 1 之间")
	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
	
	
	
	public Integer getDosagertCount() {
		return dosagertCount;
	}

	public void setDosagertCount(Integer dosagertCount) {
		this.dosagertCount = dosagertCount;
	}


	public BigDecimal getDosagertMoney() {
		return dosagertMoney;
	}

	public void setDosagertMoney(BigDecimal dosagertMoney) {
		this.dosagertMoney = dosagertMoney;
	}

	@Length(min=0, max=50, message="操作人姓名长度必须介于 0 和 50 之间")
	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
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
	
	public List<PdDosagertDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PdDosagertDetail> detailList) {
		this.detailList = detailList;
	}

	public String getDosagertState() {
		return dosagertState;
	}

	public void setDosagertState(String dosagertState) {
		this.dosagertState = dosagertState;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdBarcode() {
		return prodBarcode;
	}

	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getDosageCount() {
		return dosageCount;
	}

	public void setDosageCount(Integer dosageCount) {
		this.dosageCount = dosageCount;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getRtCount() {
		return rtCount;
	}

	public void setRtCount(Integer rtCount) {
		this.rtCount = rtCount;
	}


	public String getDosageNumber() {
		return dosageNumber;
	}

	public void setDosageNumber(String dosageNumber) {
		this.dosageNumber = dosageNumber;
	}
}