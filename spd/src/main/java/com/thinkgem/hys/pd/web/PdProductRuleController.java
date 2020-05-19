/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.entity.PdProductRule;
import com.thinkgem.hys.pd.service.PdProductRuleService;
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
 * 产品绑定编码规则Controller
 * @author zxh
 * @version 2019-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductRule")
public class PdProductRuleController extends BaseController {

	@Autowired
	private PdProductRuleService pdProductRuleService;
	
	@ModelAttribute
	public PdProductRule get(@RequestParam(required=false) String id) {
		PdProductRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductRuleService.get(id);
		}
		if (entity == null){
			entity = new PdProductRule();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductRule pdProductRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductRule> page = pdProductRuleService.findPage(new Page<PdProductRule>(request, response), pdProductRule); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductRuleList";
	}

	@RequiresPermissions("pd:pdProductRule:view")
	@RequestMapping(value = "form")
	public String form(PdProductRule pdProductRule, Model model) {
		model.addAttribute("pdProductRule", pdProductRule);
		return "hys/pd/pdProductRuleForm";
	}

	@RequiresPermissions("pd:pdProductRule:edit")
	@RequestMapping(value = "save")
	public String save(PdProductRule pdProductRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductRule)){
			return form(pdProductRule, model);
		}
		pdProductRuleService.save(pdProductRule);
		addMessage(redirectAttributes, "保存产品绑定编码规则成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductRule/?repage";
	}
	
	@RequiresPermissions("pd:pdProductRule:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductRule pdProductRule, RedirectAttributes redirectAttributes) {
		pdProductRuleService.delete(pdProductRule);
		addMessage(redirectAttributes, "删除产品绑定编码规则成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductRule/?repage";
	}

}