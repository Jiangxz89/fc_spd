/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 本院收费项目目录Entity
 * @author zxh
 * @version 2019-11-13
 */
public class BaseFeeprice extends DataEntity<BaseFeeprice> {
	
	private static final long serialVersionUID = 1L;
	private String stockid;		// 本院ID
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String feeid;		// 中心ID
	private String cuscode;		// 自定义码
	private String feenumber;		// 项目编码
	private String price;		// 价格
	private String isble;		// 处方可开
	private String outpack;		// 是否门诊套餐
	private String inpack;		// 是否住院套餐
	private String isstop;		// 是否停用
	private String sicode;		// 社保编码
	private String nhcode;		// 农合编码
	private String modstaff;		// 修改人
	private String modstaffname;		// 修改人名
	private Date moddate;		// 修改时间
	private String audstate;		// 是否通过社保审核
	private String invclass;		// 发票分类
	private String invclassname;		// 发票分类名称
	private String auditstatus;		// 审核状态
	private String auditor;		// 审核人
	private String auditorname;		// 审核人名
	private Date audittime;		// 审核时间
	private String oneLevel;		// 一级价格
	private String twoLevel;		// 二级价格
	private String threeLevel;		// 三级价格
	private String hosfeename;		// 本院名称
	
	public BaseFeeprice() {
		super();
	}

	public BaseFeeprice(String id){
		super(id);
	}

	@Length(min=1, max=11, message="本院ID长度必须介于 1 和 11 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	@Length(min=0, max=11, message="医院ID长度必须介于 0 和 11 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=0, max=50, message="医疗机构名称长度必须介于 0 和 50 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=0, max=11, message="中心ID长度必须介于 0 和 11 之间")
	public String getFeeid() {
		return feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}
	
	@Length(min=0, max=10, message="自定义码长度必须介于 0 和 10 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=0, max=20, message="项目编码长度必须介于 0 和 20 之间")
	public String getFeenumber() {
		return feenumber;
	}

	public void setFeenumber(String feenumber) {
		this.feenumber = feenumber;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=4, message="处方可开长度必须介于 0 和 4 之间")
	public String getIsble() {
		return isble;
	}

	public void setIsble(String isble) {
		this.isble = isble;
	}
	
	@Length(min=0, max=4, message="是否门诊套餐长度必须介于 0 和 4 之间")
	public String getOutpack() {
		return outpack;
	}

	public void setOutpack(String outpack) {
		this.outpack = outpack;
	}
	
	@Length(min=0, max=4, message="是否住院套餐长度必须介于 0 和 4 之间")
	public String getInpack() {
		return inpack;
	}

	public void setInpack(String inpack) {
		this.inpack = inpack;
	}
	
	@Length(min=0, max=4, message="是否停用长度必须介于 0 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=0, max=20, message="社保编码长度必须介于 0 和 20 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=0, max=20, message="农合编码长度必须介于 0 和 20 之间")
	public String getNhcode() {
		return nhcode;
	}

	public void setNhcode(String nhcode) {
		this.nhcode = nhcode;
	}
	
	@Length(min=0, max=11, message="修改人长度必须介于 0 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=0, max=20, message="修改人名长度必须介于 0 和 20 之间")
	public String getModstaffname() {
		return modstaffname;
	}

	public void setModstaffname(String modstaffname) {
		this.modstaffname = modstaffname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Length(min=0, max=6, message="是否通过社保审核长度必须介于 0 和 6 之间")
	public String getAudstate() {
		return audstate;
	}

	public void setAudstate(String audstate) {
		this.audstate = audstate;
	}
	
	@Length(min=0, max=11, message="发票分类长度必须介于 0 和 11 之间")
	public String getInvclass() {
		return invclass;
	}

	public void setInvclass(String invclass) {
		this.invclass = invclass;
	}
	
	@Length(min=0, max=20, message="发票分类名称长度必须介于 0 和 20 之间")
	public String getInvclassname() {
		return invclassname;
	}

	public void setInvclassname(String invclassname) {
		this.invclassname = invclassname;
	}
	
	@Length(min=0, max=4, message="审核状态长度必须介于 0 和 4 之间")
	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	
	@Length(min=0, max=11, message="审核人长度必须介于 0 和 11 之间")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	@Length(min=0, max=20, message="审核人名长度必须介于 0 和 20 之间")
	public String getAuditorname() {
		return auditorname;
	}

	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
	
	public String getOneLevel() {
		return oneLevel;
	}

	public void setOneLevel(String oneLevel) {
		this.oneLevel = oneLevel;
	}
	
	public String getTwoLevel() {
		return twoLevel;
	}

	public void setTwoLevel(String twoLevel) {
		this.twoLevel = twoLevel;
	}
	
	public String getThreeLevel() {
		return threeLevel;
	}

	public void setThreeLevel(String threeLevel) {
		this.threeLevel = threeLevel;
	}
	
	@Length(min=0, max=100, message="本院名称长度必须介于 0 和 100 之间")
	public String getHosfeename() {
		return hosfeename;
	}

	public void setHosfeename(String hosfeename) {
		this.hosfeename = hosfeename;
	}
	
}