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
import com.thinkgem.hys.pd.entity.MedstoYpckmx;
import com.thinkgem.hys.pd.service.MedstoYpckmxService;

/**
 * 药品出库明细Controller
 * @author zxh
 * @version 2019-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpckmx")
public class MedstoYpckmxController extends BaseController {

	@Autowired
	private MedstoYpckmxService medstoYpckmxService;
	
	@ModelAttribute
	public MedstoYpckmx get(@RequestParam(required=false) String id) {
		MedstoYpckmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpckmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpckmx();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpckmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpckmx medstoYpckmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpckmx> page = medstoYpckmxService.findPage(new Page<MedstoYpckmx>(request, response), medstoYpckmx); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpckmxList";
	}

	@RequiresPermissions("pd:medstoYpckmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpckmx medstoYpckmx, Model model) {
		model.addAttribute("medstoYpckmx", medstoYpckmx);
		return "hys/pd/medstoYpckmxForm";
	}

	@RequiresPermissions("pd:medstoYpckmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpckmx medstoYpckmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpckmx)){
			return form(medstoYpckmx, model);
		}
		medstoYpckmxService.save(medstoYpckmx);
		addMessage(redirectAttributes, "保存药品出库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpckmx/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpckmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpckmx medstoYpckmx, RedirectAttributes redirectAttributes) {
		medstoYpckmxService.delete(medstoYpckmx);
		addMessage(redirectAttributes, "删除药品出库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpckmx/?repage";
	}

}