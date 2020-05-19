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
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2Client;
import com.thinkgem.jeesite.modules.sys.service.auth.Oauth2ClientService;

/**
 * authclientController
 * @author wenmingfeng
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/oauth2Client")
public class Oauth2ClientController extends BaseController {

	@Autowired
	private Oauth2ClientService oauth2ClientService;
	
	@ModelAttribute
	public Oauth2Client get(@RequestParam(required=false) String id) {
		Oauth2Client entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oauth2ClientService.get(id);
		}
		if (entity == null){
			entity = new Oauth2Client();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:oauth2Client:view")
	@RequestMapping(value = {"list", ""})
	public String list(Oauth2Client oauth2Client, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Oauth2Client> page = oauth2ClientService.findPage(new Page<Oauth2Client>(request, response), oauth2Client); 
		model.addAttribute("page", page);
		return "modules/sys/oauth2ClientList";
	}

	@RequiresPermissions("sys:oauth2Client:view")
	@RequestMapping(value = "form")
	public String form(Oauth2Client oauth2Client, Model model) {
		model.addAttribute("oauth2Client", oauth2Client);
		return "modules/sys/oauth2ClientForm";
	}

	@RequiresPermissions("sys:oauth2Client:edit")
	@RequestMapping(value = "save")
	public String save(Oauth2Client oauth2Client, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oauth2Client)){
			return form(oauth2Client, model);
		}
		oauth2ClientService.save(oauth2Client);
		addMessage(redirectAttributes, "保存authclient成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2Client/?repage";
	}
	
	@RequiresPermissions("sys:oauth2Client:edit")
	@RequestMapping(value = "delete")
	public String delete(Oauth2Client oauth2Client, RedirectAttributes redirectAttributes) {
		oauth2ClientService.delete(oauth2Client);
		addMessage(redirectAttributes, "删除authclient成功");
		return "redirect:"+Global.getAdminPath()+"/sys/oauth2Client/?repage";
	}

}