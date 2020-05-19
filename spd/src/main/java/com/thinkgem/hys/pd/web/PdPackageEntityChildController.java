/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

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
import com.thinkgem.hys.pd.entity.PdPackageEntityChild;
import com.thinkgem.hys.pd.service.PdPackageEntityChildService;

/**
 * 实体包实体产品表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPackageEntityChild")
public class PdPackageEntityChildController extends BaseController {

	@Autowired
	private PdPackageEntityChildService pdPackageEntityChildService;
	
	@ModelAttribute
	public PdPackageEntityChild get(@RequestParam(required=false) String id) {
		PdPackageEntityChild entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPackageEntityChildService.get(id);
		}
		if (entity == null){
			entity = new PdPackageEntityChild();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdPackageEntityChild:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPackageEntityChild pdPackageEntityChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdPackageEntityChild> page = pdPackageEntityChildService.findPage(new Page<PdPackageEntityChild>(request, response), pdPackageEntityChild); 
		model.addAttribute("page", page);
		return "hys/pd/pdPackageEntityChildList";
	}

	@RequiresPermissions("pd:pdPackageEntityChild:view")
	@RequestMapping(value = "form")
	public String form(PdPackageEntityChild pdPackageEntityChild, Model model) {
		model.addAttribute("pdPackageEntityChild", pdPackageEntityChild);
		return "hys/pd/pdPackageEntityChildForm";
	}

	@RequiresPermissions("pd:pdPackageEntityChild:edit")
	@RequestMapping(value = "save")
	public String save(PdPackageEntityChild pdPackageEntityChild, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdPackageEntityChild)){
			return form(pdPackageEntityChild, model);
		}
		pdPackageEntityChildService.save(pdPackageEntityChild);
		addMessage(redirectAttributes, "保存实体包实体产品表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPackageEntityChild/?repage";
	}
	
	@RequiresPermissions("pd:pdPackageEntityChild:edit")
	@RequestMapping(value = "delete")
	public String delete(PdPackageEntityChild pdPackageEntityChild, RedirectAttributes redirectAttributes) {
		pdPackageEntityChildService.delete(pdPackageEntityChild);
		addMessage(redirectAttributes, "删除实体包实体产品表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPackageEntityChild/?repage";
	}

}