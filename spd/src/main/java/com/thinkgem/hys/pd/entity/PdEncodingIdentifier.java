/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 应用标识符表Entity
 * @author zxh
 * @version 2019-04-19
 */
public class PdEncodingIdentifier extends DataEntity<PdEncodingIdentifier> {
	
	private static final long serialVersionUID = 1L;
	private String value;		// 值
	private String meaning;		// 含义
	private String type;		// 类型
	private Integer length;		// 长度
	
	public PdEncodingIdentifier() {
		super();
	}

	public PdEncodingIdentifier(String id){
		super(id);
	}

	@Length(min=1, max=64, message="值长度必须介于 1 和 64 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=64, message="含义长度必须介于 1 和 64 之间")
	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	@Length(min=1, max=64, message="类型长度必须介于 1 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}