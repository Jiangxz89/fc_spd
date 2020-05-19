/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.auth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.auth.Oauth2UserDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2User;

/**
 * authuserService
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Service
@Transactional(readOnly = true)
public class Oauth2UserService extends CrudService<Oauth2UserDao, Oauth2User> {

	public Oauth2User get(String id) {
		return super.get(id);
	}
	
	public List<Oauth2User> findList(Oauth2User oauth2User) {
		return super.findList(oauth2User);
	}
	
	public Page<Oauth2User> findPage(Page<Oauth2User> page, Oauth2User oauth2User) {
		return super.findPage(page, oauth2User);
	}
	
	@Transactional(readOnly = false)
	public void save(Oauth2User oauth2User) {
		super.save(oauth2User);
	}
	
	@Transactional(readOnly = false)
	public void delete(Oauth2User oauth2User) {
		super.delete(oauth2User);
	}
	
}