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
import com.thinkgem.hys.pd.entity.MedstoYkzkc;
import com.thinkgem.hys.pd.service.MedstoYkzkcService;

/**
 * 药库总库存Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYkzkc")
public class MedstoYkzkcController extends BaseController {

	@Autowired
	private MedstoYkzkcService medstoYkzkcService;
	
	@ModelAttribute
	public MedstoYkzkc get(@RequestParam(required=false) String id) {
		MedstoYkzkc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYkzkcService.get(id);
		}
		if (entity == null){
			entity = new MedstoYkzkc();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYkzkc:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYkzkc medstoYkzkc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYkzkc> page = medstoYkzkcService.findPage(new Page<MedstoYkzkc>(request, response), medstoYkzkc); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYkzkcList";
	}

	@RequiresPermissions("pd:medstoYkzkc:view")
	@RequestMapping(value = "form")
	public String form(MedstoYkzkc medstoYkzkc, Model model) {
		model.addAttribute("medstoYkzkc", medstoYkzkc);
		return "hys/pd/medstoYkzkcForm";
	}

	@RequiresPermissions("pd:medstoYkzkc:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYkzkc medstoYkzkc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYkzkc)){
			return form(medstoYkzkc, model);
		}
		medstoYkzkcService.save(medstoYkzkc);
		addMessage(redirectAttributes, "保存药库总库存成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYkzkc/?repage";
	}
	
	@RequiresPermissions("pd:medstoYkzkc:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYkzkc medstoYkzkc, RedirectAttributes redirectAttributes) {
		medstoYkzkcService.delete(medstoYkzkc);
		addMessage(redirectAttributes, "删除药库总库存成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYkzkc/?repage";
	}

}