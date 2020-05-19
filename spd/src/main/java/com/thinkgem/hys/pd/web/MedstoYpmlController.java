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
import com.thinkgem.hys.pd.entity.MedstoYpml;
import com.thinkgem.hys.pd.service.MedstoYpmlService;

/**
 * 药品目录表Controller
 * @author zxh
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpml")
public class MedstoYpmlController extends BaseController {

	@Autowired
	private MedstoYpmlService medstoYpmlService;
	
	@ModelAttribute
	public MedstoYpml get(@RequestParam(required=false) String id) {
		MedstoYpml entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpmlService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpml();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpml:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpml medstoYpml, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpml> page = medstoYpmlService.findPage(new Page<MedstoYpml>(request, response), medstoYpml); 
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpmlList";
	}

	/**
	 * 药品基础目录
	 * @param medstoYpml
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpml:view")
	@RequestMapping(value = "findDrugBasicsCatalog")
	public String findDrugBasicsCatalog(MedstoYpml medstoYpml, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpml> page = medstoYpmlService.findPage(new Page<MedstoYpml>(request, response), medstoYpml);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpmlList";
	}

	@RequiresPermissions("pd:medstoYpml:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpml medstoYpml, Model model) {
		medstoYpml = medstoYpmlService.get(medstoYpml.getXh());
		model.addAttribute("medstoYpml", medstoYpml);
		return "hys/pd/query/medsto/medstoYpmlForm";
	}

	@RequiresPermissions("pd:medstoYpml:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpml medstoYpml, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpml)){
			return form(medstoYpml, model);
		}
		medstoYpmlService.save(medstoYpml);
		addMessage(redirectAttributes, "保存药品目录表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpml/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpml:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpml medstoYpml, RedirectAttributes redirectAttributes) {
		medstoYpmlService.delete(medstoYpml);
		addMessage(redirectAttributes, "删除药品目录表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpml/?repage";
	}

}