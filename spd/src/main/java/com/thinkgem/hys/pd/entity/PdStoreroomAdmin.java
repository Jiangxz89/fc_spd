/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 库房管理员信息Entity
 * @author changjundong
 * @version 2018-03-07
 */
public class PdStoreroomAdmin extends DataEntity<PdStoreroomAdmin> {
	
	private static final long serialVersionUID = 1L;
	private String storeroomId;		// 库房主键
	private String adminId;		// 管理员主键
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	
	//冗余
	private String adminLoginName;	//登录名
	private String adminName;		//姓名
	
	public PdStoreroomAdmin() {
		super();
	}

	public PdStoreroomAdmin(String id){
		super(id);
	}

	@Length(min=0, max=64, message="库房主键长度必须介于 0 和 64 之间")
	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	
	@Length(min=0, max=64, message="管理员主键长度必须介于 0 和 64 之间")
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	@Length(min=0, max=200, message="extend1长度必须介于 0 和 200 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=100, message="extend2长度必须介于 0 和 100 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=100, message="extend3长度必须介于 0 和 100 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getAdminLoginName() {
		return adminLoginName;
	}

	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}