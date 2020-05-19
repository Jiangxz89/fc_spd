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
import com.thinkgem.hys.pd.entity.MedstoYplcmlk;
import com.thinkgem.hys.pd.service.MedstoYplcmlkService;

/**
 * 药品临床目录库Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYplcmlk")
public class MedstoYplcmlkController extends BaseController {

	@Autowired
	private MedstoYplcmlkService medstoYplcmlkService;
	
	@ModelAttribute
	public MedstoYplcmlk get(@RequestParam(required=false) String id) {
		MedstoYplcmlk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYplcmlkService.get(id);
		}
		if (entity == null){
			entity = new MedstoYplcmlk();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYplcmlk:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYplcmlk medstoYplcmlk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYplcmlk> page = medstoYplcmlkService.findPage(new Page<MedstoYplcmlk>(request, response), medstoYplcmlk); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYplcmlkList";
	}

	@RequiresPermissions("pd:medstoYplcmlk:view")
	@RequestMapping(value = "form")
	public String form(MedstoYplcmlk medstoYplcmlk, Model model) {
		model.addAttribute("medstoYplcmlk", medstoYplcmlk);
		return "hys/pd/medstoYplcmlkForm";
	}

	@RequiresPermissions("pd:medstoYplcmlk:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYplcmlk medstoYplcmlk, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYplcmlk)){
			return form(medstoYplcmlk, model);
		}
		medstoYplcmlkService.save(medstoYplcmlk);
		addMessage(redirectAttributes, "保存药品临床目录库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYplcmlk/?repage";
	}
	
	@RequiresPermissions("pd:medstoYplcmlk:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYplcmlk medstoYplcmlk, RedirectAttributes redirectAttributes) {
		medstoYplcmlkService.delete(medstoYplcmlk);
		addMessage(redirectAttributes, "删除药品临床目录库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYplcmlk/?repage";
	}

}