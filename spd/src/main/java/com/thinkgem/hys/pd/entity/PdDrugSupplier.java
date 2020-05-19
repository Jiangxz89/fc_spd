/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品供应商表Entity
 * @author wg
 * @version 2018-08-23
 */
public class PdDrugSupplier extends DataEntity<PdDrugSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String ypdm;		// 药品代码
	private String ghdwId;		// 供应商ID
	private String ghdwMc;		// 供应商名称
	private String type;		//类型
	
	public PdDrugSupplier() {
		super();
	}

	public PdDrugSupplier(String id){
		super(id);
	}

	@Length(min=1, max=32, message="药品代码长度必须介于 1 和 32 之间")
	public String getYpdm() {
		return ypdm;
	}

	public void setYpdm(String ypdm) {
		this.ypdm = ypdm;
	}
	
	@Length(min=0, max=32, message="供应商ID长度必须介于 0 和 32 之间")
	public String getGhdwId() {
		return ghdwId;
	}

	public void setGhdwId(String ghdwId) {
		this.ghdwId = ghdwId;
	}
	
	@Length(min=0, max=50, message="供应商名称长度必须介于 0 和 50 之间")
	public String getGhdwMc() {
		return ghdwMc;
	}

	public void setGhdwMc(String ghdwMc) {
		this.ghdwMc = ghdwMc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}