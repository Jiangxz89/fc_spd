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
 * 药房发药单Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataYfexemaster extends DataEntity<DataYfexemaster> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 记录ID
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String exedeptid;		// 执行科室ID
	private String exedeptname;		// 执行科室名称
	private String reatilfee;		// 零售金额
	private String costid;		// 收费ID
	private String invoiceid;		// 发票ID
	private String invoiceno;		// 发票号
	private String inid;		// 住院号
	private String fuqty;		// 副数
	private String patlstno;		// 门诊号
	private String patname;		// 姓名
	private Date prntime;		// 配药单时间
	private String dosstaff;		// 配药人
	private String dosstaffname;		// 配药人名
	private String opstaff;		// 发药人
	private String opstaffname;		// 发药人名
	private Date optime;		// 发药时间
	private Date createtime;		// 单据创建时间
	private String orderclass;		// 处方类型
	private String orderclassname;		// 处方类型名称
	private String pattype;		// 病人类型
	private String pattypename;		// 病人类型名称
	
	public DataYfexemaster() {
		super();
	}

	public DataYfexemaster(String id){
		super(id);
	}

	@Length(min=1, max=11, message="记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=11, message="医院ID长度必须介于 1 和 11 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=1, max=255, message="医疗机构名称长度必须介于 1 和 255 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=1, max=11, message="执行科室ID长度必须介于 1 和 11 之间")
	public String getExedeptid() {
		return exedeptid;
	}

	public void setExedeptid(String exedeptid) {
		this.exedeptid = exedeptid;
	}
	
	@Length(min=1, max=255, message="执行科室名称长度必须介于 1 和 255 之间")
	public String getExedeptname() {
		return exedeptname;
	}

	public void setExedeptname(String exedeptname) {
		this.exedeptname = exedeptname;
	}
	
	public String getReatilfee() {
		return reatilfee;
	}

	public void setReatilfee(String reatilfee) {
		this.reatilfee = reatilfee;
	}
	
	@Length(min=1, max=11, message="收费ID长度必须介于 1 和 11 之间")
	public String getCostid() {
		return costid;
	}

	public void setCostid(String costid) {
		this.costid = costid;
	}
	
	@Length(min=1, max=11, message="发票ID长度必须介于 1 和 11 之间")
	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	
	@Length(min=1, max=50, message="发票号长度必须介于 1 和 50 之间")
	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	
	@Length(min=1, max=11, message="住院号长度必须介于 1 和 11 之间")
	public String getInid() {
		return inid;
	}

	public void setInid(String inid) {
		this.inid = inid;
	}
	
	@Length(min=1, max=11, message="副数长度必须介于 1 和 11 之间")
	public String getFuqty() {
		return fuqty;
	}

	public void setFuqty(String fuqty) {
		this.fuqty = fuqty;
	}
	
	@Length(min=1, max=10, message="门诊号长度必须介于 1 和 10 之间")
	public String getPatlstno() {
		return patlstno;
	}

	public void setPatlstno(String patlstno) {
		this.patlstno = patlstno;
	}
	
	@Length(min=1, max=20, message="姓名长度必须介于 1 和 20 之间")
	public String getPatname() {
		return patname;
	}

	public void setPatname(String patname) {
		this.patname = patname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="配药单时间不能为空")
	public Date getPrntime() {
		return prntime;
	}

	public void setPrntime(Date prntime) {
		this.prntime = prntime;
	}
	
	@Length(min=1, max=11, message="配药人长度必须介于 1 和 11 之间")
	public String getDosstaff() {
		return dosstaff;
	}

	public void setDosstaff(String dosstaff) {
		this.dosstaff = dosstaff;
	}
	
	@Length(min=1, max=20, message="配药人名长度必须介于 1 和 20 之间")
	public String getDosstaffname() {
		return dosstaffname;
	}

	public void setDosstaffname(String dosstaffname) {
		this.dosstaffname = dosstaffname;
	}
	
	@Length(min=1, max=11, message="发药人长度必须介于 1 和 11 之间")
	public String getOpstaff() {
		return opstaff;
	}

	public void setOpstaff(String opstaff) {
		this.opstaff = opstaff;
	}
	
	@Length(min=1, max=20, message="发药人名长度必须介于 1 和 20 之间")
	public String getOpstaffname() {
		return opstaffname;
	}

	public void setOpstaffname(String opstaffname) {
		this.opstaffname = opstaffname;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getOrderclass() {
		return orderclass;
	}

	public void setOrderclass(String orderclass) {
		this.orderclass = orderclass;
	}

	public String getOrderclassname() {
		return orderclassname;
	}

	public void setOrderclassname(String orderclassname) {
		this.orderclassname = orderclassname;
	}

	@Length(min=0, max=11, message="病人类型长度必须介于 0 和 11 之间")
	public String getPattype() {
		return pattype;
	}

	public void setPattype(String pattype) {
		this.pattype = pattype;
	}
	
	@Length(min=0, max=20, message="病人类型名称长度必须介于 0 和 20 之间")
	public String getPattypename() {
		return pattypename;
	}

	public void setPattypename(String pattypename) {
		this.pattypename = pattypename;
	}
	
}