package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author jiangxz
 * @description 病人信息
 * @date 2020-5-20
 */
public class HisPatient {

    private static final long serialVersionUID = 1L;

    private String vaa07;//申请编号 患者流水号 aplyNo
    private String operNo;//手术编号（如果没有手术，可为空）
    private String blngDptmNo;//所属病区编码
    private String blngDptmName;//所属病区
    private String blngNo;//所属科室编码  bck01b
    private String blngName;//所属科室
    private String type;//住院标识（1：是  2：否）
    private String projectName;//项目名称
    private String departName;//手术或检查项目科室（如果没有手术，则是检查项目科室）
    private String createDate;//登记日期
    private String doctorCode;//申请医生编码
    private String doctorName;//申请医生姓名
    private String outpatCode;//门诊号
    private String sex;       //男或女
    private String patientName;//病人姓名
    private String hitaionNo;//住院号（如果不是住院，可为空）
    private String bedCode;//床位号（如果没有，可为空）

    public String getVaa07() {
        return vaa07;
    }

    public void setVaa07(String vaa07) {
        this.vaa07 = vaa07;
    }

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
    }

    public String getBlngDptmNo() {
        return blngDptmNo;
    }

    public void setBlngDptmNo(String blngDptmNo) {
        this.blngDptmNo = blngDptmNo;
    }

    public String getBlngDptmName() {
        return blngDptmName;
    }

    public void setBlngDptmName(String blngDptmName) {
        this.blngDptmName = blngDptmName;
    }

    public String getBlngNo() {
        return blngNo;
    }

    public void setBlngNo(String blngNo) {
        this.blngNo = blngNo;
    }

    public String getBlngName() {
        return blngName;
    }

    public void setBlngName(String blngName) {
        this.blngName = blngName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getOutpatCode() {
        return outpatCode;
    }

    public void setOutpatCode(String outpatCode) {
        this.outpatCode = outpatCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHitaionNo() {
        return hitaionNo;
    }

    public void setHitaionNo(String hitaionNo) {
        this.hitaionNo = hitaionNo;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
