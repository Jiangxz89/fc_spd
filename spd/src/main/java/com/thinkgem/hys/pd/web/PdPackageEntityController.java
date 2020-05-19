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
import com.thinkgem.hys.pd.entity.PdPackageEntity;
import com.thinkgem.hys.pd.service.PdPackageEntityService;

/**
 * 实体包表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPackageEntity")
public class PdPackageEntityController extends BaseController {

	@Autowired
	private PdPackageEntityService pdPackageEntityService;
	
	@ModelAttribute
	public PdPackageEntity get(@RequestParam(required=false) String id) {
		PdPackageEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPackageEntityService.get(id);
		}
		if (entity == null){
			entity = new PdPackageEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdPackageEntity:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPackageEntity pdPackageEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdPackageEntity> page = pdPackageEntityService.findPage(new Page<PdPackageEntity>(request, response), pdPackageEntity); 
		model.addAttribute("page", page);
		return "hys/pd/pdPackageEntityList";
	}

	@RequiresPermissions("pd:pdPackageEntity:view")
	@RequestMapping(value = "form")
	public String form(PdPackageEntity pdPackageEntity, Model model) {
		model.addAttribute("pdPackageEntity", pdPackageEntity);
		return "hys/pd/pdPackageEntityForm";
	}

	@RequiresPermissions("pd:pdPackageEntity:edit")
	@RequestMapping(value = "save")
	public String save(PdPackageEntity pdPackageEntity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdPackageEntity)){
			return form(pdPackageEntity, model);
		}
		pdPackageEntityService.save(pdPackageEntity);
		addMessage(redirectAttributes, "保存实体包表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPackageEntity/?repage";
	}
	
	@RequiresPermissions("pd:pdPackageEntity:edit")
	@RequestMapping(value = "delete")
	public String delete(PdPackageEntity pdPackageEntity, RedirectAttributes redirectAttributes) {
		pdPackageEntityService.delete(pdPackageEntity);
		addMessage(redirectAttributes, "删除实体包表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPackageEntity/?repage";
	}

}