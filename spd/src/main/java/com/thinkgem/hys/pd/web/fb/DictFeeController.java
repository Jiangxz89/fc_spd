/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DictFee;
import com.thinkgem.hys.pd.service.DictFeeService;
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
 * 中心收费项目目录Controller
 * @author zxh
 * @version 2019-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dictFee")
public class DictFeeController extends BaseController {

	@Autowired
	private DictFeeService dictFeeService;
	
	@ModelAttribute
	public DictFee get(@RequestParam(required=false) String id) {
		DictFee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictFeeService.get(id);
		}
		if (entity == null){
			entity = new DictFee();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dictFee:view")
	@RequestMapping(value = {"list", ""})
	public String list(DictFee dictFee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DictFee> page = dictFeeService.findPage(new Page<DictFee>(request, response), dictFee); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dictFeeList";
	}

	@RequiresPermissions("pd:dictFee:view")
	@RequestMapping(value = "form")
	public String form(DictFee dictFee, Model model) {
		model.addAttribute("dictFee", dictFee);
		return "hys/pd/dictFeeForm";
	}

	@RequiresPermissions("pd:dictFee:edit")
	@RequestMapping(value = "save")
	public String save(DictFee dictFee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dictFee)){
			return form(dictFee, model);
		}
		dictFeeService.save(dictFee);
		addMessage(redirectAttributes, "保存中心收费项目目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictFee/?repage";
	}
	
	@RequiresPermissions("pd:dictFee:edit")
	@RequestMapping(value = "delete")
	public String delete(DictFee dictFee, RedirectAttributes redirectAttributes) {
		dictFeeService.delete(dictFee);
		addMessage(redirectAttributes, "删除中心收费项目目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dictFee/?repage";
	}

}