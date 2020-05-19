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
import com.thinkgem.hys.pd.entity.PdSupplierCard;
import com.thinkgem.hys.pd.service.PdSupplierCardService;

/**
 * 供应商证件信息Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdSupplierCard")
public class PdSupplierCardController extends BaseController {

	@Autowired
	private PdSupplierCardService pdSupplierCardService;
	
	@ModelAttribute
	public PdSupplierCard get(@RequestParam(required=false) String id) {
		PdSupplierCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdSupplierCardService.get(id);
		}
		if (entity == null){
			entity = new PdSupplierCard();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdSupplierCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdSupplierCard pdSupplierCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdSupplierCard> page = pdSupplierCardService.findPage(new Page<PdSupplierCard>(request, response), pdSupplierCard); 
		model.addAttribute("page", page);
		return "hys/pd/pdSupplierCardList";
	}

	@RequiresPermissions("pd:pdSupplierCard:view")
	@RequestMapping(value = "form")
	public String form(PdSupplierCard pdSupplierCard, Model model) {
		model.addAttribute("pdSupplierCard", pdSupplierCard);
		return "hys/pd/pdSupplierCardForm";
	}

	@RequiresPermissions("pd:pdSupplierCard:edit")
	@RequestMapping(value = "save")
	public String save(PdSupplierCard pdSupplierCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdSupplierCard)){
			return form(pdSupplierCard, model);
		}
		pdSupplierCardService.save(pdSupplierCard);
		addMessage(redirectAttributes, "保存供应商证件信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdSupplierCard/?repage";
	}
	
	@RequiresPermissions("pd:pdSupplierCard:edit")
	@RequestMapping(value = "delete")
	public String delete(PdSupplierCard pdSupplierCard, RedirectAttributes redirectAttributes) {
		pdSupplierCardService.delete(pdSupplierCard);
		addMessage(redirectAttributes, "删除供应商证件信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdSupplierCard/?repage";
	}

}