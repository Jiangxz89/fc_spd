/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品单位表Entity
 * @author jiangxz
 * @version 2019-05-10
 */
public class PdUnit extends DataEntity<PdUnit> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 单位代码（预留字段）
	private String name;		// 单位名称
	private int synFlag;		// syn_flag
	
	public PdUnit() {
		super();
	}

	public PdUnit(String id){
		super(id);
	}

	@Length(min=0, max=100, message="单位代码（预留字段）长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=100, message="单位名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="syn_flag长度必须介于 0 和 2 之间")
	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
	
}