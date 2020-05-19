/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.auth;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * oauth2_user_relateEntity
 * @author wenmingfeng
 * @version 2017-08-08
 */
public class Oauth2UserRelate extends DataEntity<Oauth2UserRelate> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户姓名
	private String code;		// 获取token的code
	private String token;		// 用户访问时的token
	private String refressToken;		// 刷新token
	private Date buildTime;		// 生成时间
	
	public Oauth2UserRelate() {
		super();
	}

	public Oauth2UserRelate(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户姓名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="获取token的code长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="用户访问时的token长度必须介于 0 和 64 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=0, max=64, message="刷新token长度必须介于 0 和 64 之间")
	public String getRefressToken() {
		return refressToken;
	}

	public void setRefressToken(String refressToken) {
		this.refressToken = refressToken;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	
}