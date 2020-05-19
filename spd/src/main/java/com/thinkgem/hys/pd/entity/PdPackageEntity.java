/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 实体包表Entity
 * @author sutianqi
 * @version 2018-03-07
 */
public class PdPackageEntity extends DataEntity<PdPackageEntity> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// _id
	private String packageId;		// package_id
	private String number;		// number
	
	public PdPackageEntity() {
		super();
	}

	public PdPackageEntity(String id){
		super(id);
	}

	@Length(min=1, max=64, message="_id长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	
	@Length(min=0, max=64, message="package_id长度必须介于 0 和 64 之间")
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	@Length(min=0, max=64, message="number长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}