package com.easemob.im.api.impl;

import com.easemob.im.api.AuthTokenAPI;
import com.easemob.im.common.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
