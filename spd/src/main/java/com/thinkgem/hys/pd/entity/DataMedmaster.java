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
 * 药品入库主记录Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataMedmaster extends DataEntity<DataMedmaster> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 记录主ID
	private String hospitalid;		// 机构ID
	private String hospitalname;		// 医疗机构名称
	private String deptid;		// 出库科室ID
	private String deptname;		// 科室名称
	private String redeptid;		// 相关科室ID
	private String redeptname;		// 相关科室名称
	private Date billdate;		// 单据日期
	private String billno;		// 单据号
	private String supplierid;		// 供应商ID
	private String suppliername;		// 供应商名
	private String billkd;		// 单据类型
	private String billstf;		// 制单人
	private String billstfname;		// 制单人名
	private String state;		// 单据状态
	private String audstf;		// 单据审核人
	private String audstfname;		// 单据审核人名
	private Date audtime;		// 审核时间
	private String mataccpid;		// 药库盘点单ID
	private String housepid;		// 药房盘点单ID
	private String relatedid;		// 相关单据ID
	private String dspt;		// 备注
	private String gatkdid;		// 支付方式
	private String invoiceno;		// 发票号
	private String receiptno;		// 送货单号
	private String invoicedate;		// 发票日期
	
	public DataMedmaster() {
		super();
	}

	public DataMedmaster(String id){
		super(id);
	}

	@Length(min=1, max=11, message="记录主ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=6, message="机构ID长度必须介于 1 和 6 之间")
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
	
	@Length(min=1, max=11, message="出库科室ID长度必须介于 1 和 11 之间")
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
	
	@Length(min=1, max=11, message="相关科室ID长度必须介于 1 和 11 之间")
	public String getRedeptid() {
		return redeptid;
	}

	public void setRedeptid(String redeptid) {
		this.redeptid = redeptid;
	}
	
	@Length(min=1, max=20, message="相关科室名称长度必须介于 1 和 20 之间")
	public String getRedeptname() {
		return redeptname;
	}

	public void setRedeptname(String redeptname) {
		this.redeptname = redeptname;
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
	
	@Length(min=1, max=6, message="供应商ID长度必须介于 1 和 6 之间")
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	
	@Length(min=1, max=60, message="供应商名长度必须介于 1 和 60 之间")
	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	
	@Length(min=1, max=6, message="单据类型长度必须介于 1 和 6 之间")
	public String getBillkd() {
		return billkd;
	}

	public void setBillkd(String billkd) {
		this.billkd = billkd;
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
	
	@Length(min=1, max=6, message="单据状态长度必须介于 1 和 6 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=11, message="单据审核人长度必须介于 1 和 11 之间")
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
	@NotNull(message="审核时间不能为空")
	public Date getAudtime() {
		return audtime;
	}

	public void setAudtime(Date audtime) {
		this.audtime = audtime;
	}
	
	@Length(min=1, max=11, message="药库盘点单ID长度必须介于 1 和 11 之间")
	public String getMataccpid() {
		return mataccpid;
	}

	public void setMataccpid(String mataccpid) {
		this.mataccpid = mataccpid;
	}
	
	@Length(min=1, max=11, message="药房盘点单ID长度必须介于 1 和 11 之间")
	public String getHousepid() {
		return housepid;
	}

	public void setHousepid(String housepid) {
		this.housepid = housepid;
	}
	
	@Length(min=1, max=11, message="相关单据ID长度必须介于 1 和 11 之间")
	public String getRelatedid() {
		return relatedid;
	}

	public void setRelatedid(String relatedid) {
		this.relatedid = relatedid;
	}
	
	@Length(min=1, max=60, message="备注长度必须介于 1 和 60 之间")
	public String getDspt() {
		return dspt;
	}

	public void setDspt(String dspt) {
		this.dspt = dspt;
	}
	
	@Length(min=1, max=11, message="支付方式长度必须介于 1 和 11 之间")
	public String getGatkdid() {
		return gatkdid;
	}

	public void setGatkdid(String gatkdid) {
		this.gatkdid = gatkdid;
	}
	
	@Length(min=0, max=30, message="发票号长度必须介于 0 和 30 之间")
	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	
	@Length(min=0, max=30, message="送货单号长度必须介于 0 和 30 之间")
	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	
	@Length(min=0, max=30, message="发票日期长度必须介于 0 和 30 之间")
	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	
}