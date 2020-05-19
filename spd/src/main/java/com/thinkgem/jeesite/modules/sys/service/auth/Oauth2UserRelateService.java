/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.auth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.auth.Oauth2UserRelateDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2UserRelate;

/**
 * oauth2_user_relateService
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Service
@Transactional(readOnly = true)
public class Oauth2UserRelateService extends CrudService<Oauth2UserRelateDao, Oauth2UserRelate> {

	public Oauth2UserRelate get(String id) {
		return super.get(id);
	}
	
	public List<Oauth2UserRelate> findList(Oauth2UserRelate oauth2UserRelate) {
		return super.findList(oauth2UserRelate);
	}
	
	public Page<Oauth2UserRelate> findPage(Page<Oauth2UserRelate> page, Oauth2UserRelate oauth2UserRelate) {
		return super.findPage(page, oauth2UserRelate);
	}
	
	@Transactional(readOnly = false)
	public void save(Oauth2UserRelate oauth2UserRelate) {
		super.save(oauth2UserRelate);
	}
	
	@Transactional(readOnly = false)
	public void delete(Oauth2UserRelate oauth2UserRelate) {
		super.delete(oauth2UserRelate);
	}
	
}