/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.auth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.auth.Oauth2ClientDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2Client;

/**
 * authclientService
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Service
@Transactional(readOnly = true)
public class Oauth2ClientService extends CrudService<Oauth2ClientDao, Oauth2Client> {

	public Oauth2Client get(String id) {
		return super.get(id);
	}
	
	public List<Oauth2Client> findList(Oauth2Client oauth2Client) {
		return super.findList(oauth2Client);
	}
	
	public Page<Oauth2Client> findPage(Page<Oauth2Client> page, Oauth2Client oauth2Client) {
		return super.findPage(page, oauth2Client);
	}
	
	@Transactional(readOnly = false)
	public void save(Oauth2Client oauth2Client) {
		super.save(oauth2Client);
	}
	
	@Transactional(readOnly = false)
	public void delete(Oauth2Client oauth2Client) {
		super.delete(oauth2Client);
	}
	
	
	public boolean checkClientId(String clientId){
		Oauth2Client oauth2Client = new Oauth2Client();
		oauth2Client.setClientId(clientId);
		oauth2Client =  dao.findByClientId(oauth2Client);
		if(oauth2Client==null||StringUtils.isEmpty(oauth2Client.getId())){
			return true;
		}else{
			return false;
		}
	}
	
	public Oauth2Client findByClientId(String clientId){
		Oauth2Client oauth2Client = new Oauth2Client();
		oauth2Client.setClientId(clientId);
		oauth2Client =  dao.findByClientId(oauth2Client);
		return oauth2Client;
	}
	
}