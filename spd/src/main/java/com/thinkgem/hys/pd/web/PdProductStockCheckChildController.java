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
import com.thinkgem.hys.pd.entity.PdProductStockCheckChild;
import com.thinkgem.hys.pd.service.PdProductStockCheckChildService;

/**
 * 盘点详细表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStockCheckChild")
public class PdProductStockCheckChildController extends BaseController {

	@Autowired
	private PdProductStockCheckChildService pdProductStockCheckChildService;
	
	@ModelAttribute
	public PdProductStockCheckChild get(@RequestParam(required=false) String id) {
		PdProductStockCheckChild entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockCheckChildService.get(id);
		}
		if (entity == null){
			entity = new PdProductStockCheckChild();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductStockCheckChild:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStockCheckChild pdProductStockCheckChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductStockCheckChild> page = pdProductStockCheckChildService.findPage(new Page<PdProductStockCheckChild>(request, response), pdProductStockCheckChild); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductStockCheckChildList";
	}

	@RequiresPermissions("pd:pdProductStockCheckChild:view")
	@RequestMapping(value = "form")
	public String form(PdProductStockCheckChild pdProductStockCheckChild, Model model) {
		model.addAttribute("pdProductStockCheckChild", pdProductStockCheckChild);
		return "hys/pd/pdProductStockCheckChildForm";
	}

	@RequiresPermissions("pd:pdProductStockCheckChild:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStockCheckChild pdProductStockCheckChild, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductStockCheckChild)){
			return form(pdProductStockCheckChild, model);
		}
		pdProductStockCheckChildService.save(pdProductStockCheckChild);
		addMessage(redirectAttributes, "保存盘点详细表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockCheckChild/?repage";
	}
	
	@RequiresPermissions("pd:pdProductStockCheckChild:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductStockCheckChild pdProductStockCheckChild, RedirectAttributes redirectAttributes) {
		pdProductStockCheckChildService.delete(pdProductStockCheckChild);
		addMessage(redirectAttributes, "删除盘点详细表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStockCheckChild/?repage";
	}

}