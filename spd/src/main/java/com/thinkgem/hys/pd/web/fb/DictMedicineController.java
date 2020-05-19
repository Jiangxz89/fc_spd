/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DictMedicine;
import com.thinkgem.hys.pd.service.DictMedicineService;
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
 * 中心药品目录Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dictMedicine")
public class DictMedicineController extends BaseController {

	@Autowired
	private DictMedicineService dictMedicineService;
	
	@ModelAttribute
	public DictMedicine get(@RequestParam(required=false) String id) {
		DictMedicine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictMedicineService.get(id);
		}
		if (entity == null){
			entity = new DictMedicine();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dictMedicine:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictMedicine dictMedicine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictMedicine> page = dictMedicineService.findPage(new Page<DictMedicine>(request, response), dictMedicine); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dictMedicineList";
	}

	@RequiresPermissions("pd:dictMedicine:view")
	@RequestMapping(value = "form")
	public String form(DictMedicine dictMedicine, Model model) {
		model.addAttribute("dictMedicine", dictMedicine);
		return "hys/pd/dictMedicineForm";
	}

	@RequiresPermissions("pd:dictMedicine:edit")
	@RequestMapping(value = "save")
	public String save(DictMedicine dictMedicine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictMedicine)){
			return form(dictMedicine, model);
		}
		dictMedicineService.save(dictMedicine);
		addMessage(redirectAttributes, "保存中心药品目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictMedicine/?repage";
	}
	
	@RequiresPermissions("pd:dictMedicine:edit")
	@RequestMapping(value = "delete")
	public String delete(DictMedicine dictMedicine, RedirectAttributes redirectAttributes) {
		dictMedicineService.delete(dictMedicine);
		addMessage(redirectAttributes, "删除中心药品目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictMedicine/?repage";
	}

}