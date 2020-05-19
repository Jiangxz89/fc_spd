/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.auth;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2UserRelate;

/**
 * oauth2_user_relateDAO接口
 * @author wenmingfeng
 * @version 2017-08-08
 */
@MyBatisDao
public interface Oauth2UserRelateDao extends CrudDao<Oauth2UserRelate> {
	
	public Oauth2UserRelate getUsernameByAuthCode(Oauth2UserRelate oauth2UserRelate);
	
	public int addAccessToken(Oauth2UserRelate oauth2UserRelate);
	
	public int insertEntity(Oauth2UserRelate oauth2UserRelate);
	
}