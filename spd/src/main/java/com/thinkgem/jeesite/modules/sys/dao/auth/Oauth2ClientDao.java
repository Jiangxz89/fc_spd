/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.auth;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2Client;

/**
 * authclientDAO接口
 * @author wenmingfeng
 * @version 2017-08-08
 */
@MyBatisDao
public interface Oauth2ClientDao extends CrudDao<Oauth2Client> {
	
	public Oauth2Client findByClientId(Oauth2Client oauth2Client);
	
}