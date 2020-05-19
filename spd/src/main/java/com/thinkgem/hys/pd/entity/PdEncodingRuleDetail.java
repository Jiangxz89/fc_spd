/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 编码规则详情信息表Entity
 * @author zxh
 * @version 2019-04-22
 */
public class PdEncodingRuleDetail extends DataEntity<PdEncodingRuleDetail> {
	
	private static final long serialVersionUID = 1L;
	private String codeId;		// 编码规则表id
	private String identifier;		// 应用标识符id
	private String length;		// 长度
	private String codeOrder;		// 顺序

	//冗余
	private String value;//标识符的值
	private String Meaning;//标识符的含义
    private String type;//标识符类型
	private String size;//标识符固定长度
	
	public PdEncodingRuleDetail() {
		super();
	}

	public PdEncodingRuleDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="编码规则表id长度必须介于 0 和 64 之间")
	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	@Length(min=0, max=64, message="应用标识符id长度必须介于 0 和 64 之间")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Length(min=0, max=11, message="长度长度必须介于 0 和 11 之间")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	@Length(min=0, max=64, message="顺序长度必须介于 0 和 64 之间")
	public String getCodeOrder() {
		return codeOrder;
	}

	public void setCodeOrder(String codeOrder) {
		this.codeOrder = codeOrder;
	}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return Meaning;
    }

    public void setMeaning(String meaning) {
        Meaning = meaning;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}