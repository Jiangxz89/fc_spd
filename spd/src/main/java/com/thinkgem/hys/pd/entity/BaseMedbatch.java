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
 * 药品库存明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class BaseMedbatch extends DataEntity<BaseMedbatch> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String deptid;		// 科室ID
	private String deptname;		// 科室名称
	private String stockid;		// 药品ID
	private String medname;		// 药品名称
	private String batch;		// 批次
	private String qty;		// 实际库存
	private String unit;		// 单位
	private String inprice;		// 购进价
	private String clinprice;		// 零售价
	private String wardprice;		// 预计零售价
	private Date expidate;		// 有效期
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String state;		// 状态
	private String permdoc;		// 批准文号
	private String refercnt;		// 批次入库次数
	private Date createtime;		// 入库时间
	private String supplierid;		// 供应商ID
	private String suppliername;		// 供应商名
	private String validqty;		// 有效库存
	private String batchno;		// 批号
	private String cabinetid;		// 库位号ID
	
	public BaseMedbatch() {
		super();
	}

	public BaseMedbatch(String id){
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
	
	@Length(min=1, max=50, message="批次长度必须介于 1 和 50 之间")
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
	
	@Length(min=0, max=4, message="单位长度必须介于 0 和 4 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="有效期不能为空")
	public Date getExpidate() {
		return expidate;
	}

	public void setExpidate(Date expidate) {
		this.expidate = expidate;
	}
	
	@Length(min=1, max=11, message="厂家ID长度必须介于 1 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=1, max=150, message="厂家名称长度必须介于 1 和 150 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=60, message="批准文号长度必须介于 1 和 60 之间")
	public String getPermdoc() {
		return permdoc;
	}

	public void setPermdoc(String permdoc) {
		this.permdoc = permdoc;
	}
	
	@Length(min=1, max=6, message="批次入库次数长度必须介于 1 和 6 之间")
	public String getRefercnt() {
		return refercnt;
	}

	public void setRefercnt(String refercnt) {
		this.refercnt = refercnt;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="入库时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=6, message="供应商ID长度必须介于 0 和 6 之间")
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	
	@Length(min=0, max=60, message="供应商名长度必须介于 0 和 60 之间")
	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	
	public String getValidqty() {
		return validqty;
	}

	public void setValidqty(String validqty) {
		this.validqty = validqty;
	}
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
	@Length(min=1, max=11, message="库位号ID长度必须介于 1 和 11 之间")
	public String getCabinetid() {
		return cabinetid;
	}

	public void setCabinetid(String cabinetid) {
		this.cabinetid = cabinetid;
	}
	
}