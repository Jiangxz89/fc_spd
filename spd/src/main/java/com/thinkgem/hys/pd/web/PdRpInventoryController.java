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
import com.thinkgem.hys.pd.entity.PdRpInventory;
import com.thinkgem.hys.pd.service.PdRpInventoryService;

/**
 * 进销存明细Controller
 * @author zxh
 * @version 2018-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdRpInventory")
public class PdRpInventoryController extends BaseController {

	@Autowired
	private PdRpInventoryService pdRpInventoryService;
	
	@ModelAttribute
	public PdRpInventory get(@RequestParam(required=false) String id) {
		PdRpInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdRpInventoryService.get(id);
		}
		if (entity == null){
			entity = new PdRpInventory();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdRpInventory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdRpInventory pdRpInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdRpInventory> page = pdRpInventoryService.findPage(new Page<PdRpInventory>(request, response), pdRpInventory); 
		model.addAttribute("page", page);
		return "hys/pd/pdRpInventoryList";
	}

	@RequiresPermissions("pd:pdRpInventory:view")
	@RequestMapping(value = "form")
	public String form(PdRpInventory pdRpInventory, Model model) {
		model.addAttribute("pdRpInventory", pdRpInventory);
		return "hys/pd/pdRpInventoryForm";
	}

	@RequiresPermissions("pd:pdRpInventory:edit")
	@RequestMapping(value = "save")
	public String save(PdRpInventory pdRpInventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdRpInventory)){
			return form(pdRpInventory, model);
		}
		pdRpInventoryService.save(pdRpInventory);
		addMessage(redirectAttributes, "保存进销存明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRpInventory/?repage";
	}
	
	@RequiresPermissions("pd:pdRpInventory:edit")
	@RequestMapping(value = "delete")
	public String delete(PdRpInventory pdRpInventory, RedirectAttributes redirectAttributes) {
		pdRpInventoryService.delete(pdRpInventory);
		addMessage(redirectAttributes, "删除进销存明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRpInventory/?repage";
	}

}