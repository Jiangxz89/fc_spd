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
 * 药品调价单Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataMatpriceadj extends DataEntity<DataMatpriceadj> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String depid;		// 科室ID
	private String deptname;		// 科室名称
	private Date billdate;		// 单据日期
	private String billno;		// 单据号
	private String billkd;		// 类型
	private String reason;		// 调价原因
	private String adjtype;		// 调价方式
	private String billstf;		// 制单人
	private String billstfname;		// 制单人名
	private String hdlstf;		// 调价审核人
	private String hdlstfname;		// 审核人名
	private String state;		// 状态
	private Date iptdate;		// 创建时间
	private Date adjdate;		// 审核时间
	
	public DataMatpriceadj() {
		super();
	}

	public DataMatpriceadj(String id){
		super(id);
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
	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}
	
	@Length(min=1, max=20, message="科室名称长度必须介于 1 和 20 之间")
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
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
	
	@Length(min=1, max=4, message="类型长度必须介于 1 和 4 之间")
	public String getBillkd() {
		return billkd;
	}

	public void setBillkd(String billkd) {
		this.billkd = billkd;
	}
	
	@Length(min=1, max=250, message="调价原因长度必须介于 1 和 250 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=1, max=4, message="调价方式长度必须介于 1 和 4 之间")
	public String getAdjtype() {
		return adjtype;
	}

	public void setAdjtype(String adjtype) {
		this.adjtype = adjtype;
	}
	
	@Length(min=1, max=11, message="制单人长度必须介于 1 和 11 之间")
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
	
	@Length(min=1, max=11, message="调价审核人长度必须介于 1 和 11 之间")
	public String getHdlstf() {
		return hdlstf;
	}

	public void setHdlstf(String hdlstf) {
		this.hdlstf = hdlstf;
	}
	
	@Length(min=1, max=20, message="审核人名长度必须介于 1 和 20 之间")
	public String getHdlstfname() {
		return hdlstfname;
	}

	public void setHdlstfname(String hdlstfname) {
		this.hdlstfname = hdlstfname;
	}
	
	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIptdate() {
		return iptdate;
	}

	public void setIptdate(Date iptdate) {
		this.iptdate = iptdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAdjdate() {
		return adjdate;
	}

	public void setAdjdate(Date adjdate) {
		this.adjdate = adjdate;
	}
	
}