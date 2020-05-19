/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DictMaterial;
import com.thinkgem.hys.pd.service.DictMaterialService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 中心物资目录Controller
 * @author zxh
 * @version 2019-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dictMaterial")
public class DictMaterialController extends BaseController {

	@Autowired
	private DictMaterialService dictMaterialService;
	
	@ModelAttribute
	public DictMaterial get(@RequestParam(required=false) String id) {
		DictMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictMaterialService.get(id);
		}
		if (entity == null){
			entity = new DictMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dictMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictMaterial dictMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictMaterial> page = dictMaterialService.findPage(new Page<DictMaterial>(request, response), dictMaterial); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dictMaterialList";
	}

	@RequiresPermissions("pd:dictMaterial:view")
	@RequestMapping(value = "form")
	public String form(DictMaterial dictMaterial, Model model) {
		model.addAttribute("dictMaterial", dictMaterial);
		return "hys/pd/dictMaterialForm";
	}

	@RequiresPermissions("pd:dictMaterial:edit")
	@RequestMapping(value = "save")
	public String save(DictMaterial dictMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictMaterial)){
			return form(dictMaterial, model);
		}
		dictMaterialService.save(dictMaterial);
		addMessage(redirectAttributes, "保存中心物资目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictMaterial/?repage";
	}
	
	@RequiresPermissions("pd:dictMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(DictMaterial dictMaterial, RedirectAttributes redirectAttributes) {
		dictMaterialService.delete(dictMaterial);
		addMessage(redirectAttributes, "删除中心物资目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictMaterial/?repage";
	}

}