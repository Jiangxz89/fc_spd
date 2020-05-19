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
import com.thinkgem.hys.pd.entity.PdProductStockFlow;
import com.thinkgem.hys.pd.service.PdProductStockFlowService;

/**
 * 库存流水Controller
 * @author lww
 * @version 2018-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStockFlow")
public class PdProductStockFlowController extends BaseController {

	@Autowired
	private PdProductStockFlowService pdProductStockFlowService;
	
	@ModelAttribute
	public PdProductStockFlow get(@RequestParam(required=false) String id) {
		PdProductStockFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockFlowService.get(id);
		}
		if (entity == null){
			entity = new PdProductStockFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductStockFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStockFlow pdProductStockFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductStockFlow> page = pdProductStockFlowService.findPage(new Page<PdProductStockFlow>(request, response), pdProductStockFlow); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductStockFlowList";
	}

	@RequiresPermissions("pd:pdProductStockFlow:view")
	@RequestMapping(value = "form")
	public String form(PdProductStockFlow pdProductStockFlow, Model model) {
		model.addAttribute("pdProductStockFlow", pdProductStockFlow);
		return "hys/pd/pdProductStockFlowForm";
	}

	@RequiresPermissions("pd:pdProductStockFlow:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStockFlow pdProductStockFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductStockFlow)){
			return form(pdProductStockFlow, model);
		}
		pdProductStockFlowService.save(pdProductStockFlow);
		addMessage(redirectAttributes, "保存库存流水成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockFlow/?repage";
	}
	
	@RequiresPermissions("pd:pdProductStockFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductStockFlow pdProductStockFlow, RedirectAttributes redirectAttributes) {
		pdProductStockFlowService.delete(pdProductStockFlow);
		addMessage(redirectAttributes, "删除库存流水成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockFlow/?repage";
	}

}