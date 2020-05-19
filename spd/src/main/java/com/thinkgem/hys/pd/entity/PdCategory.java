/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 类别表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdCategory extends DataEntity<PdCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name ;
	private String type ;
	private int synFlag ;//同步标志，0-未同步；1-已同步
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(int synFlag) {
		this.synFlag = synFlag;
	}
	
	

}
