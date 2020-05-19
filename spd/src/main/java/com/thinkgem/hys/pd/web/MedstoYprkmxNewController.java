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
import com.thinkgem.hys.pd.entity.MedstoYprkmxNew;
import com.thinkgem.hys.pd.service.MedstoYprkmxNewService;

/**
 * 药品入库明细Controller
 * @author zxh
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYprkmxNew")
public class MedstoYprkmxNewController extends BaseController {

	@Autowired
	private MedstoYprkmxNewService medstoYprkmxNewService;
	
	@ModelAttribute
	public MedstoYprkmxNew get(@RequestParam(required=false) String id) {
		MedstoYprkmxNew entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYprkmxNewService.get(id);
		}
		if (entity == null){
			entity = new MedstoYprkmxNew();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYprkmxNew:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYprkmxNew medstoYprkmxNew, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYprkmxNew> page = medstoYprkmxNewService.findPage(new Page<MedstoYprkmxNew>(request, response), medstoYprkmxNew); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYprkmxNewList";
	}

	@RequiresPermissions("pd:medstoYprkmxNew:view")
	@RequestMapping(value = "form")
	public String form(MedstoYprkmxNew medstoYprkmxNew, Model model) {
		model.addAttribute("medstoYprkmxNew", medstoYprkmxNew);
		return "hys/pd/medstoYprkmxNewForm";
	}

	@RequiresPermissions("pd:medstoYprkmxNew:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYprkmxNew medstoYprkmxNew, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYprkmxNew)){
			return form(medstoYprkmxNew, model);
		}
		medstoYprkmxNewService.save(medstoYprkmxNew);
		addMessage(redirectAttributes, "保存药品入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYprkmxNew/?repage";
	}
	
	@RequiresPermissions("pd:medstoYprkmxNew:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYprkmxNew medstoYprkmxNew, RedirectAttributes redirectAttributes) {
		medstoYprkmxNewService.delete(medstoYprkmxNew);
		addMessage(redirectAttributes, "删除药品入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYprkmxNew/?repage";
	}

}