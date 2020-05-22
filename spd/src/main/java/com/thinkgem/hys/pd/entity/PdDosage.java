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
 * 器械用量Entity
 * @author wg
 * @version 2018-03-06
 */
public class PdDosage extends DataEntity<PdDosage> {
	
	private static final long serialVersionUID = 1L;
	private String dosageNo;		// 用量单号
	private String warehouseId;		// 用户库房id
	private String warehouseName;		// 用量库房名称
	private Integer dosageCount;		// 用量数量
	private Date dosageDate;		// 用量日期
	private String dosageBy;		//操作人
	private String salesman;		//销售人员
	private Integer amountCount;		// 总数量
	private Double amountMoney;		// 总金额
	private String patientInfo;		// 病人信息
	private String patientDetailInfo;		// 病人详细信息
	private String exeDeptId;		// 执行科室ID
	private String exeDeptName;		// 执行科室名称
	private String oprDeptId;		// 手术科室ID
	private String oprDeptName;		// 手术科室名称
	private String surgeon;		// 手术医生
	private String surgeonId;	//手术医生ID
	private String sqrtDoctor;		// 开方医生
	private String sqrtDoctorId;	//开方医生ID
	private String inHospitalNo;	//住院号
	private String visitNo; //就诊流水号
	private String displayFlag;//是否有his接口标识
	private String isCharge;//产品是否计费标识
	//-temp
	private List<PdDosageDetail> dosageDetailList;
	private String chargeFlag;//收费标识1：收费，0：不收费
	private String dosageOperator;//操作人姓名

	private String subordinateWardId;//所属病区
	private String subordinateWard;//所属病区id
	private String outpatientNumber;//门诊号
	private String operativeNumber;//手术编号

	//冗余
	private String number;			// 产品编码
	private String pdName;
	private String spec;		// 规格
	private String version;		// 型号
	private String unit;		// 产品单位
	private String power;		// 产品权限     0:公有产品   1：自有产品
	private String venderName;		// 生产厂家名称
	private BigDecimal sellingPrice;		// 出价
	private Date limitDate;		// 有效期
	private Integer productNum;		// 出入库数量
	private String productBarCode;		// 产品条码
	private String batchNo; //批次号
	private String supplierId;//供应商id
	private String supplierName;//供应商名称
	public PdDosage() {
		super();
	}

	public PdDosage(String id){
		super(id);
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}

	@Length(min=0, max=64, message="用量单号长度必须介于 0 和 64 之间")
	public String getDosageNo() {
		return dosageNo;
	}

	public void setDosageNo(String dosageNo) {
		this.dosageNo = dosageNo;
	}
	
	@Length(min=0, max=64, message="用户库房id长度必须介于 0 和 64 之间")
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	@Length(min=0, max=50, message="用量库房名称长度必须介于 0 和 50 之间")
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDosageDate() {
		return dosageDate;
	}

	public void setDosageDate(Date dosageDate) {
		this.dosageDate = dosageDate;
	}
	
	public Integer getDosageCount() {
		return dosageCount;
	}

	public void setDosageCount(Integer dosageCount) {
		this.dosageCount = dosageCount;
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

	@Length(min=0, max=100, message="病人信息长度必须介于 0 和 100 之间")
	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}
	
	@Length(min=0, max=255, message="病人详细信息长度必须介于 0 和 255 之间")
	public String getPatientDetailInfo() {
		return patientDetailInfo;
	}

	public void setPatientDetailInfo(String patientDetailInfo) {
		this.patientDetailInfo = patientDetailInfo;
	}
	
	@Length(min=0, max=64, message="执行科室ID长度必须介于 0 和 64 之间")
	public String getExeDeptId() {
		return exeDeptId;
	}

	public void setExeDeptId(String exeDeptId) {
		this.exeDeptId = exeDeptId;
	}
	
	@Length(min=0, max=20, message="执行科室名称长度必须介于 0 和 20 之间")
	public String getExeDeptName() {
		return exeDeptName;
	}

	public void setExeDeptName(String exeDeptName) {
		this.exeDeptName = exeDeptName;
	}
	
	@Length(min=0, max=64, message="手术科室ID长度必须介于 0 和 64 之间")
	public String getOprDeptId() {
		return oprDeptId;
	}

	public void setOprDeptId(String oprDeptId) {
		this.oprDeptId = oprDeptId;
	}
	
	@Length(min=0, max=20, message="手术科室名称长度必须介于 0 和 20 之间")
	public String getOprDeptName() {
		return oprDeptName;
	}

	public void setOprDeptName(String oprDeptName) {
		this.oprDeptName = oprDeptName;
	}
	
	@Length(min=0, max=20, message="手术医生长度必须介于 0 和 20 之间")
	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}
	
	@Length(min=0, max=20, message="开方医生长度必须介于 0 和 20 之间")
	public String getSqrtDoctor() {
		return sqrtDoctor;
	}

	public void setSqrtDoctor(String sqrtDoctor) {
		this.sqrtDoctor = sqrtDoctor;
	}

	public List<PdDosageDetail> getDosageDetailList() {
		return dosageDetailList;
	}

	public void setDosageDetailList(List<PdDosageDetail> dosageDetailList) {
		this.dosageDetailList = dosageDetailList;
	}

	public String getChargeFlag() {
		return chargeFlag;
	}

	public void setChargeFlag(String chargeFlag) {
		this.chargeFlag = chargeFlag;
	}

	public String getDosageBy() {
		return dosageBy;
	}

	public void setDosageBy(String dosageBy) {
		this.dosageBy = dosageBy;
	}

	public String getDosageOperator() {
		return dosageOperator;
	}

	public void setDosageOperator(String dosageOperator) {
		this.dosageOperator = dosageOperator;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSurgeonId() {
		return surgeonId;
	}

	public void setSurgeonId(String surgeonId) {
		this.surgeonId = surgeonId;
	}

	public String getSqrtDoctorId() {
		return sqrtDoctorId;
	}

	public void setSqrtDoctorId(String sqrtDoctorId) {
		this.sqrtDoctorId = sqrtDoctorId;
	}

	public String getInHospitalNo() {
		return inHospitalNo;
	}

	public void setInHospitalNo(String inHospitalNo) {
		this.inHospitalNo = inHospitalNo;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	
	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public String getSubordinateWardId() {
		return subordinateWardId;
	}

	public void setSubordinateWardId(String subordinateWardId) {
		this.subordinateWardId = subordinateWardId;
	}

	public String getSubordinateWard() {
		return subordinateWard;
	}

	public void setSubordinateWard(String subordinateWard) {
		this.subordinateWard = subordinateWard;
	}

	public String getOutpatientNumber() {
		return outpatientNumber;
	}

	public void setOutpatientNumber(String outpatientNumber) {
		this.outpatientNumber = outpatientNumber;
	}

	public String getOperativeNumber() {
		return operativeNumber;
	}

	public void setOperativeNumber(String operativeNumber) {
		this.operativeNumber = operativeNumber;
	}
}