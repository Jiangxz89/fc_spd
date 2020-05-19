/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.auth;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * authclientEntity
 * @author wenmingfeng
 * @version 2017-08-08
 */
public class Oauth2Client extends DataEntity<Oauth2Client> {
	
	private static final long serialVersionUID = 1L;
	private String clientName;		// 客户端名称
	private String clientId;		// 客户端id
	private String clientSecret;		// 客户端安全key
	private String loginType;		// login_type
	
	public Oauth2Client() {
		super();
	}

	public Oauth2Client(String id){
		super(id);
	}

	@Length(min=1, max=64, message="客户端名称长度必须介于 1 和 64 之间")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Length(min=1, max=64, message="客户端id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=1, max=100, message="客户端安全key长度必须介于 1 和 100 之间")
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	@Length(min=0, max=1, message="login_type长度必须介于 0 和 1 之间")
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
}