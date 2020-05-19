/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2User;
import com.thinkgem.jeesite.modules.sys.service.auth.Oauth2UserService;

/**
 * authuserController
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/oauth2User")
public class Oauth2UserController extends BaseController {

	@Autowired
	private Oauth2UserService oauth2UserService;
	
	@ModelAttribute
	public Oauth2User get(@RequestParam(required=false) String id) {
		Oauth2User entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oauth2UserService.get(id);
		}
		if (entity == null){
			entity = new Oauth2User();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:oauth2User:view")
	@RequestMapping(value = {"list", ""})
	public String list(Oauth2User oauth2User, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Oauth2User> page = oauth2UserService.findPage(new Page<Oauth2User>(request, response), oauth2User); 
		model.addAttribute("page", page);
		return "modules/sys/oauth2UserList";
	}

	@RequiresPermissions("sys:oauth2User:view")
	@RequestMapping(value = "form")
	public String form(Oauth2User oauth2User, Model model) {
		model.addAttribute("oauth2User", oauth2User);
		return "modules/sys/oauth2UserForm";
	}

	@RequiresPermissions("sys:oauth2User:edit")
	@RequestMapping(value = "save")
	public String save(Oauth2User oauth2User, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oauth2User)){
			return form(oauth2User, model);
		}
		oauth2UserService.save(oauth2User);
		addMessage(redirectAttributes, "保存authuser成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2User/?repage";
	}
	
	@RequiresPermissions("sys:oauth2User:edit")
	@RequestMapping(value = "delete")
	public String delete(Oauth2User oauth2User, RedirectAttributes redirectAttributes) {
		oauth2UserService.delete(oauth2User);
		addMessage(redirectAttributes, "删除authuser成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2User/?repage";
	}

}