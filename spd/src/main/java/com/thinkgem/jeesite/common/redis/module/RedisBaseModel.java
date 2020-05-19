package com.thinkgem.jeesite.common.redis.module;

import java.io.Serializable;

public abstract class RedisBaseModel implements Serializable  {

	private static final long serialVersionUID = 8856706096856265302L;
	
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
