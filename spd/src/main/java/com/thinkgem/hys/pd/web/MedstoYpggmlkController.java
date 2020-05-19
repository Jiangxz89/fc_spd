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
import com.thinkgem.hys.pd.entity.MedstoYpggmlk;
import com.thinkgem.hys.pd.service.MedstoYpggmlkService;

/**
 * 药品规格库Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpggmlk")
public class MedstoYpggmlkController extends BaseController {

	@Autowired
	private MedstoYpggmlkService medstoYpggmlkService;
	
	@ModelAttribute
	public MedstoYpggmlk get(@RequestParam(required=false) String id) {
		MedstoYpggmlk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpggmlkService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpggmlk();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpggmlk:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpggmlk medstoYpggmlk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpggmlk> page = medstoYpggmlkService.findPage(new Page<MedstoYpggmlk>(request, response), medstoYpggmlk); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpggmlkList";
	}

	@RequiresPermissions("pd:medstoYpggmlk:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpggmlk medstoYpggmlk, Model model) {
		model.addAttribute("medstoYpggmlk", medstoYpggmlk);
		return "hys/pd/medstoYpggmlkForm";
	}

	@RequiresPermissions("pd:medstoYpggmlk:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpggmlk medstoYpggmlk, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpggmlk)){
			return form(medstoYpggmlk, model);
		}
		medstoYpggmlkService.save(medstoYpggmlk);
		addMessage(redirectAttributes, "保存药品规格库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpggmlk/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpggmlk:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpggmlk medstoYpggmlk, RedirectAttributes redirectAttributes) {
		medstoYpggmlkService.delete(medstoYpggmlk);
		addMessage(redirectAttributes, "删除药品规格库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpggmlk/?repage";
	}

}