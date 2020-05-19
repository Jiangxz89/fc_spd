/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 组别表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdGroup extends DataEntity<PdGroup> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String name;		// 组别名称
	private String udpateBy;		// udpate_by
	private int synFlag ;//同步标志，0-未同步；1-已同步
	
	public PdGroup() {
		super();
	}

	public PdGroup(String id){
		super(id);
	}
	
	@Length(min=0, max=100, message="组别名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="udpate_by长度必须介于 0 和 100 之间")
	public String getUdpateBy() {
		return udpateBy;
	}

	public void setUdpateBy(String udpateBy) {
		this.udpateBy = udpateBy;
	}
	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
}