/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 产品绑定编码规则Entity
 * @author zxh
 * @version 2019-11-03
 */
public class PdProductRule extends DataEntity<PdProductRule> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 产品id
	private String ruleId;		// 编码规则id
	private String ruleName;   //编码规则名称
	private String totalDigit;

	public String getTotalDigit() {
		return totalDigit;
	}

	public void setTotalDigit(String totalDigit) {
		this.totalDigit = totalDigit;
	}

	public PdProductRule() {
		super();
	}

	public PdProductRule(String id){
		super(id);
	}

	@Length(min=1, max=64, message="产品id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=64, message="编码规则id长度必须介于 1 和 64 之间")
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
}