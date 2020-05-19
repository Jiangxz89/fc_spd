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
import com.thinkgem.hys.pd.entity.PdEncodingIdentifier;
import com.thinkgem.hys.pd.service.PdEncodingIdentifierService;

/**
 * 应用标识符表Controller
 * @author zxh
 * @version 2019-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdEncodingIdentifier")
public class PdEncodingIdentifierController extends BaseController {

	@Autowired
	private PdEncodingIdentifierService pdEncodingIdentifierService;
	
	@ModelAttribute
	public PdEncodingIdentifier get(@RequestParam(required=false) String id) {
		PdEncodingIdentifier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdEncodingIdentifierService.get(id);
		}
		if (entity == null){
			entity = new PdEncodingIdentifier();
		}
		return entity;
	}

	@RequiresPermissions("pd:pdEncodingIdentifier:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdEncodingIdentifier pdEncodingIdentifier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdEncodingIdentifier> page = pdEncodingIdentifierService.findPage(new Page<PdEncodingIdentifier>(request, response), pdEncodingIdentifier); 
		model.addAttribute("page", page);
		return "hys/pd/pdEncodingIdentifierList";
	}

	/**
	 * 编码规则添加应用标识符
	 * @param pdEncodingIdentifier
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdEncodingIdentifier:view")
	@RequestMapping(value = "getList")
	public String getList(PdEncodingIdentifier pdEncodingIdentifier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdEncodingIdentifier> page = pdEncodingIdentifierService.findPage(new Page<PdEncodingIdentifier>(request, response), pdEncodingIdentifier);
		model.addAttribute("page", page);
		return "hys/pd/box/pdEncodingIdentifierBox";
	}

	@RequiresPermissions("pd:pdEncodingIdentifier:view")
	@RequestMapping(value = "form")
	public String form(PdEncodingIdentifier pdEncodingIdentifier, Model model) {
		model.addAttribute("pdEncodingIdentifier", pdEncodingIdentifier);
		return "hys/pd/pdEncodingIdentifierForm";
	}

	@RequiresPermissions("pd:pdEncodingIdentifier:edit")
	@RequestMapping(value = "save")
	public String save(PdEncodingIdentifier pdEncodingIdentifier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdEncodingIdentifier)){
			return form(pdEncodingIdentifier, model);
		}
		pdEncodingIdentifierService.save(pdEncodingIdentifier);
		addMessage(redirectAttributes, "保存应用标识符表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingIdentifier/?repage";
	}
	
	@RequiresPermissions("pd:pdEncodingIdentifier:edit")
	@RequestMapping(value = "delete")
	public String delete(PdEncodingIdentifier pdEncodingIdentifier, RedirectAttributes redirectAttributes) {
		pdEncodingIdentifierService.delete(pdEncodingIdentifier);
		addMessage(redirectAttributes, "删除应用标识符表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingIdentifier/?repage";
	}

}