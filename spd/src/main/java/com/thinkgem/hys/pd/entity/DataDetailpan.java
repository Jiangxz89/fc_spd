/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品盘点单明细Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DataDetailpan extends DataEntity<DataDetailpan> {
	
	private static final long serialVersionUID = 1L;
	private String masterid;		// 主记录ID
	private String matid;		// 药品ID
	private String medname;		// 药品名称
	private String batch;		// 批次
	private String qty;		// 电脑数量
	private String realqty;		// 盘点数量
	private String inprice;		// 购进价
	private String clinprice;		// 零售价
	private String batchno;		// 批号
	
	public DataDetailpan() {
		super();
	}

	public DataDetailpan(String id){
		super(id);
	}

	@Length(min=1, max=11, message="主记录ID长度必须介于 1 和 11 之间")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	
	@Length(min=1, max=11, message="药品ID长度必须介于 1 和 11 之间")
	public String getMatid() {
		return matid;
	}

	public void setMatid(String matid) {
		this.matid = matid;
	}
	
	@Length(min=1, max=60, message="药品名称长度必须介于 1 和 60 之间")
	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}
	
	@Length(min=0, max=30, message="批次长度必须介于 0 和 30 之间")
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
	
	public String getRealqty() {
		return realqty;
	}

	public void setRealqty(String realqty) {
		this.realqty = realqty;
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
	
	@Length(min=0, max=30, message="批号长度必须介于 0 和 30 之间")
	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
}