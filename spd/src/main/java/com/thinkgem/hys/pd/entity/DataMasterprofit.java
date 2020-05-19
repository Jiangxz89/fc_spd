/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 医院药品报损丢失单Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataMasterprofit extends DataEntity<DataMasterprofit> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 记录ID
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String deptid;		// 科室ID
	private String deptname;		// 科室名称
	private String billkd;		// 类型
	private Date billdate;		// 单据日期
	private String billno;		// 单据号
	private String billstf;		// 制单人
	private String billstfname;		// 制单人名
	private String state;		// 状态
	private String audstf;		// 审核人
	private String audstfname;		// 单据审核人名
	private Date audtime;		// 审核时间
	private String mataccpid;		// 药库盘点ID
	private String housepid;		// 药房盘点ID
	private String dspt;		// 备注
	
	public DataMasterprofit() {
		super();
	}

	public DataMasterprofit(String id){
		super(id);
	}

	@Length(min=1, max=11, message="记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=6, message="医院ID长度必须介于 1 和 6 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=1, max=50, message="医疗机构名称长度必须介于 1 和 50 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=1, max=11, message="科室ID长度必须介于 1 和 11 之间")
	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	@Length(min=1, max=20, message="科室名称长度必须介于 1 和 20 之间")
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	@Length(min=1, max=11, message="类型长度必须介于 1 和 11 之间")
	public String getBillkd() {
		return billkd;
	}

	public void setBillkd(String billkd) {
		this.billkd = billkd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="单据日期不能为空")
	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	
	@Length(min=1, max=20, message="单据号长度必须介于 1 和 20 之间")
	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}
	
	@Length(min=1, max=6, message="制单人长度必须介于 1 和 6 之间")
	public String getBillstf() {
		return billstf;
	}

	public void setBillstf(String billstf) {
		this.billstf = billstf;
	}
	
	@Length(min=1, max=20, message="制单人名长度必须介于 1 和 20 之间")
	public String getBillstfname() {
		return billstfname;
	}

	public void setBillstfname(String billstfname) {
		this.billstfname = billstfname;
	}
	
	@Length(min=1, max=6, message="状态长度必须介于 1 和 6 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=11, message="审核人长度必须介于 1 和 11 之间")
	public String getAudstf() {
		return audstf;
	}

	public void setAudstf(String audstf) {
		this.audstf = audstf;
	}
	
	@Length(min=1, max=20, message="单据审核人名长度必须介于 1 和 20 之间")
	public String getAudstfname() {
		return audstfname;
	}

	public void setAudstfname(String audstfname) {
		this.audstfname = audstfname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAudtime() {
		return audtime;
	}

	public void setAudtime(Date audtime) {
		this.audtime = audtime;
	}
	
	@Length(min=1, max=11, message="药库盘点ID长度必须介于 1 和 11 之间")
	public String getMataccpid() {
		return mataccpid;
	}

	public void setMataccpid(String mataccpid) {
		this.mataccpid = mataccpid;
	}
	
	@Length(min=1, max=11, message="药房盘点ID长度必须介于 1 和 11 之间")
	public String getHousepid() {
		return housepid;
	}

	public void setHousepid(String housepid) {
		this.housepid = housepid;
	}
	
	@Length(min=1, max=50, message="备注长度必须介于 1 和 50 之间")
	public String getDspt() {
		return dspt;
	}

	public void setDspt(String dspt) {
		this.dspt = dspt;
	}
	
}