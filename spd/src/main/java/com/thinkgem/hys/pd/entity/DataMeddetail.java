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
 * 医院药品退药单明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataMeddetail extends DataEntity<DataMeddetail> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 单据主记录ID
	private String stockid;		// 药品ID
	private String medname;		// 药品名称
	private String batch;		// 药品批次
	private String qty;		// 数量
	private String actualqty;		// 实际数量
	private String unit;		// 药品单位
	private String spec;		// 药品规格
	private String inprice;		// 购进价
	private String clinprice;		// 零售价
	private String wardprice;		// 预计零售价
	private String batchno;		// 批号
	private Date expidate;		// 有效期
	private String matpayid;		// 结算ID
	private Date matpaydate;		// 结算日期
	private String invno;		// 发票号
	private Date invdate;		// 发票日期
	private String dspt;		// 备注
	private String checkdspt;		// 验收结论
	private String permdoc;		// 批准文号
	private String facname;		// 生产厂家
	private String totalbatchqty;		// 批次总库存
	private String indetailid;		// 相关ID
	
	public DataMeddetail() {
		super();
	}

	public DataMeddetail(String id){
		super(id);
	}

	@Length(min=1, max=11, message="单据主记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=11, message="药品ID长度必须介于 1 和 11 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	@Length(min=1, max=60, message="药品名称长度必须介于 1 和 60 之间")
	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}
	
	@Length(min=0, max=30, message="药品批次长度必须介于 0 和 30 之间")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	public String getActualqty() {
		return actualqty;
	}

	public void setActualqty(String actualqty) {
		this.actualqty = actualqty;
	}
	
	@Length(min=0, max=10, message="药品单位长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=100, message="药品规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}
	
	public String getClinprice() {
		return clinprice;
	}

	public void setClinprice(String clinprice) {
		this.clinprice = clinprice;
	}
	
	public String getWardprice() {
		return wardprice;
	}

	public void setWardprice(String wardprice) {
		this.wardprice = wardprice;
	}
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpidate() {
		return expidate;
	}

	public void setExpidate(Date expidate) {
		this.expidate = expidate;
	}
	
	@Length(min=0, max=11, message="结算ID长度必须介于 0 和 11 之间")
	public String getMatpayid() {
		return matpayid;
	}

	public void setMatpayid(String matpayid) {
		this.matpayid = matpayid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结算日期不能为空")
	public Date getMatpaydate() {
		return matpaydate;
	}

	public void setMatpaydate(Date matpaydate) {
		this.matpaydate = matpaydate;
	}
	
	@Length(min=1, max=10, message="发票号长度必须介于 1 和 10 之间")
	public String getInvno() {
		return invno;
	}

	public void setInvno(String invno) {
		this.invno = invno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发票日期不能为空")
	public Date getInvdate() {
		return invdate;
	}

	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	
	@Length(min=1, max=20, message="备注长度必须介于 1 和 20 之间")
	public String getDspt() {
		return dspt;
	}

	public void setDspt(String dspt) {
		this.dspt = dspt;
	}
	
	@Length(min=1, max=11, message="验收结论长度必须介于 1 和 11 之间")
	public String getCheckdspt() {
		return checkdspt;
	}

	public void setCheckdspt(String checkdspt) {
		this.checkdspt = checkdspt;
	}
	
	@Length(min=1, max=60, message="批准文号长度必须介于 1 和 60 之间")
	public String getPermdoc() {
		return permdoc;
	}

	public void setPermdoc(String permdoc) {
		this.permdoc = permdoc;
	}
	
	@Length(min=1, max=100, message="生产厂家长度必须介于 1 和 100 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=0, max=11, message="批次总库存长度必须介于 0 和 11 之间")
	public String getTotalbatchqty() {
		return totalbatchqty;
	}

	public void setTotalbatchqty(String totalbatchqty) {
		this.totalbatchqty = totalbatchqty;
	}
	
	@Length(min=0, max=11, message="相关ID长度必须介于 0 和 11 之间")
	public String getIndetailid() {
		return indetailid;
	}

	public void setIndetailid(String indetailid) {
		this.indetailid = indetailid;
	}
	
}