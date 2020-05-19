/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.IOException;

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
import com.thinkgem.hys.pd.entity.PdProductMPackage;
import com.thinkgem.hys.pd.service.PdProductMPackageService;

/**
 * 产品定数包中间表Controller
 * @author sutianqi
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductMPackage")
public class PdProductMPackageController extends BaseController {

	@Autowired
	private PdProductMPackageService pdProductMPackageService;
	
	@ModelAttribute
	public PdProductMPackage get(@RequestParam(required=false) String id) {
		PdProductMPackage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductMPackageService.get(id);
		}
		if (entity == null){
			entity = new PdProductMPackage();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductMPackage:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductMPackage pdProductMPackage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductMPackage> page = pdProductMPackageService.findPage(new Page<PdProductMPackage>(request, response), pdProductMPackage); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductMPackageList";
	}

	@RequiresPermissions("pd:pdProductMPackage:view")
	@RequestMapping(value = "form")
	public String form(PdProductMPackage pdProductMPackage, Model model) {
		model.addAttribute("pdProductMPackage", pdProductMPackage);
		return "hys/pd/pdProductMPackageForm";
	}

	@RequiresPermissions("pd:pdProductMPackage:edit")
	@RequestMapping(value = "save")
	public String save(PdProductMPackage pdProductMPackage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductMPackage)){
			return form(pdProductMPackage, model);
		}
		pdProductMPackageService.save(pdProductMPackage);
		addMessage(redirectAttributes, "保存产品定数包中间表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductMPackage/?repage";
	}
	
	@RequiresPermissions("pd:pdProductMPackage:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductMPackage pdProductMPackage, RedirectAttributes redirectAttributes) {
		pdProductMPackageService.delete(pdProductMPackage);
		addMessage(redirectAttributes, "删除产品定数包中间表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductMPackage/?repage";
	}
	
	/**
	 * AJAX请求删除
	 * */
	@RequiresPermissions("pd:pdProductMPackage:edit")
	@RequestMapping(value = "delete2")
	public void delete2(HttpServletRequest request , HttpServletResponse response) {
		
		String id = request.getParameter("id");
		PdProductMPackage p = new PdProductMPackage(id);
		pdProductMPackageService.delete(p);
		
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}