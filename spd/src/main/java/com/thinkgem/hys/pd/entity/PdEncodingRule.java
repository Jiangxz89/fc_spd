/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 编码规则表Entity
 * @author zxh
 * @version 2019-04-22
 */
public class PdEncodingRule extends DataEntity<PdEncodingRule> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 编码名称
	private String codeDesc;		// 规则描述
	private String totalDigit;		// 总位数
    private List<PdEncodingRuleDetail> pdEncodingRuleDetails;//编码详情
	
	public PdEncodingRule() {
		super();
	}

	public PdEncodingRule(String id){
		super(id);
	}

	@Length(min=1, max=64, message="编码名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="规则描述长度必须介于 0 和 200 之间")
	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	
	@Length(min=0, max=11, message="总位数长度必须介于 0 和 11 之间")
	public String getTotalDigit() {
		return totalDigit;
	}

	public void setTotalDigit(String totalDigit) {
		this.totalDigit = totalDigit;
	}

    public List<PdEncodingRuleDetail> getPdEncodingRuleDetails() {
        return pdEncodingRuleDetails;
    }

    public void setPdEncodingRuleDetails(List<PdEncodingRuleDetail> pdEncodingRuleDetails) {
        this.pdEncodingRuleDetails = pdEncodingRuleDetails;
    }
}