/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * pdPatienEntity
 * @author jiangxz
 * @version 2019-10-08
 */
public class PdPatien extends DataEntity<PdPatien> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String pinyin;      //姓名简拼
	private String idNumber;		// 身份证号
	private String inhospitalNo;		// 住院号/门诊号
	private String operationDoctor;		// 手术医生
	private String operationDepartment;		// 手术科室
	private String inhospitalDoctor;		// 住院医生
	private String inhospitalDepartment;		// 住院科室
	private String prescriberDoctor;		// 开方医生
	private String packageId;		// 定数包id（保留字段）
	private String price;		// 缴费金额（保留字段，如果要记录几个月的缴费记录，建议另开一张表）


    private String operationDepartmentName;		// 手术科室名称
    private String inhospitalDepartmentName;		// 住院科室名称

	public PdPatien() {
		super();
	}

	public PdPatien(String id){
		super(id);
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=1, max=64, message="姓名简拼长度必须介于 1 和 64 之间")
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Length(min=0, max=20, message="身份证号长度必须介于 0 和 20 之间")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	@Length(min=1, max=32, message="住院号/门诊号长度必须介于 1 和 32 之间")
	public String getInhospitalNo() {
		return inhospitalNo;
	}

	public void setInhospitalNo(String inhospitalNo) {
		this.inhospitalNo = inhospitalNo;
	}
	
	@Length(min=0, max=64, message="手术医生长度必须介于 0 和 64 之间")
	public String getOperationDoctor() {
		return operationDoctor;
	}

	public void setOperationDoctor(String operationDoctor) {
		this.operationDoctor = operationDoctor;
	}
	
	@Length(min=0, max=64, message="手术科室长度必须介于 0 和 64 之间")
	public String getOperationDepartment() {
		return operationDepartment;
	}

	public void setOperationDepartment(String operationDepartment) {
		this.operationDepartment = operationDepartment;
	}
	
	@Length(min=0, max=64, message="住院医生长度必须介于 0 和 64 之间")
	public String getInhospitalDoctor() {
		return inhospitalDoctor;
	}

	public void setInhospitalDoctor(String inhospitalDoctor) {
		this.inhospitalDoctor = inhospitalDoctor;
	}
	
	@Length(min=0, max=64, message="住院科室长度必须介于 0 和 64 之间")
	public String getInhospitalDepartment() {
		return inhospitalDepartment;
	}

	public void setInhospitalDepartment(String inhospitalDepartment) {
		this.inhospitalDepartment = inhospitalDepartment;
	}
	
	@Length(min=0, max=64, message="开方医生长度必须介于 0 和 64 之间")
	public String getPrescriberDoctor() {
		return prescriberDoctor;
	}

	public void setPrescriberDoctor(String prescriberDoctor) {
		this.prescriberDoctor = prescriberDoctor;
	}
	
	@Length(min=0, max=64, message="定数包id（保留字段）长度必须介于 0 和 64 之间")
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

    public String getOperationDepartmentName() {
        return operationDepartmentName;
    }

    public void setOperationDepartmentName(String operationDepartmentName) {
        this.operationDepartmentName = operationDepartmentName;
    }

    public String getInhospitalDepartmentName() {
        return inhospitalDepartmentName;
    }

    public void setInhospitalDepartmentName(String inhospitalDepartmentName) {
        this.inhospitalDepartmentName = inhospitalDepartmentName;
    }
}