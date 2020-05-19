/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.auth;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * authuserEntity
 * @author wenmingfeng
 * @version 2017-08-08
 */
public class Oauth2User extends DataEntity<Oauth2User> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 客户端名称
	private String password;		// 客户端id
	private String salt;		// 客户端安全key
	private String loginType;		// login_type
	
	public Oauth2User() {
		super();
	}

	public Oauth2User(String id){
		super(id);
	}

	@Length(min=1, max=64, message="客户端名称长度必须介于 1 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=64, message="客户端id长度必须介于 1 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1, max=100, message="客户端安全key长度必须介于 1 和 100 之间")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Length(min=0, max=1, message="login_type长度必须介于 0 和 1 之间")
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
}