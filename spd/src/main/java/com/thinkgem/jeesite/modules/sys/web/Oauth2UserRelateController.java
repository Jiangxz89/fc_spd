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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2UserRelate;
import com.thinkgem.jeesite.modules.sys.service.auth.Oauth2UserRelateService;

/**
 * oauth2_user_relateController
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/oauth2UserRelate")
public class Oauth2UserRelateController extends BaseController {

	@Autowired
	private Oauth2UserRelateService oauth2UserRelateService;
	
	@ModelAttribute
	public Oauth2UserRelate get(@RequestParam(required=false) String id) {
		Oauth2UserRelate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oauth2UserRelateService.get(id);
		}
		if (entity == null){
			entity = new Oauth2UserRelate();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:oauth2UserRelate:view")
	@RequestMapping(value = {"list", ""})
	public String list(Oauth2UserRelate oauth2UserRelate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Oauth2UserRelate> page = oauth2UserRelateService.findPage(new Page<Oauth2UserRelate>(request, response), oauth2UserRelate); 
		model.addAttribute("page", page);
		return "modules/sys/oauth2UserRelateList";
	}

	@RequiresPermissions("sys:oauth2UserRelate:view")
	@RequestMapping(value = "form")
	public String form(Oauth2UserRelate oauth2UserRelate, Model model) {
		model.addAttribute("oauth2UserRelate", oauth2UserRelate);
		return "modules/sys/oauth2UserRelateForm";
	}

	@RequiresPermissions("sys:oauth2UserRelate:edit")
	@RequestMapping(value = "save")
	public String save(Oauth2UserRelate oauth2UserRelate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oauth2UserRelate)){
			return form(oauth2UserRelate, model);
		}
		oauth2UserRelateService.save(oauth2UserRelate);
		addMessage(redirectAttributes, "保存oauth2_user_relate成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2UserRelate/?repage";
	}
	
	@RequiresPermissions("sys:oauth2UserRelate:edit")
	@RequestMapping(value = "delete")
	public String delete(Oauth2UserRelate oauth2UserRelate, RedirectAttributes redirectAttributes) {
		oauth2UserRelateService.delete(oauth2UserRelate);
		addMessage(redirectAttributes, "删除oauth2_user_relate成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2UserRelate/?repage";
	}

}