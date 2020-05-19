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
import com.thinkgem.hys.pd.entity.PdEncodingRuleDetail;
import com.thinkgem.hys.pd.service.PdEncodingRuleDetailService;

/**
 * 编码规则详情信息表Controller
 * @author zxh
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdEncodingRuleDetail")
public class PdEncodingRuleDetailController extends BaseController {

	@Autowired
	private PdEncodingRuleDetailService pdEncodingRuleDetailService;
	
	@ModelAttribute
	public PdEncodingRuleDetail get(@RequestParam(required=false) String id) {
		PdEncodingRuleDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdEncodingRuleDetailService.get(id);
		}
		if (entity == null){
			entity = new PdEncodingRuleDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdEncodingRuleDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdEncodingRuleDetail pdEncodingRuleDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdEncodingRuleDetail> page = pdEncodingRuleDetailService.findPage(new Page<PdEncodingRuleDetail>(request, response), pdEncodingRuleDetail); 
		model.addAttribute("page", page);
		return "hys/pd/pdEncodingRuleDetailList";
	}

	@RequiresPermissions("pd:pdEncodingRuleDetail:view")
	@RequestMapping(value = "form")
	public String form(PdEncodingRuleDetail pdEncodingRuleDetail, Model model) {
		model.addAttribute("pdEncodingRuleDetail", pdEncodingRuleDetail);
		return "hys/pd/pdEncodingRuleDetailForm";
	}

	@RequiresPermissions("pd:pdEncodingRuleDetail:edit")
	@RequestMapping(value = "save")
	public String save(PdEncodingRuleDetail pdEncodingRuleDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdEncodingRuleDetail)){
			return form(pdEncodingRuleDetail, model);
		}
		pdEncodingRuleDetailService.save(pdEncodingRuleDetail);
		addMessage(redirectAttributes, "保存编码规则详情信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingRuleDetail/?repage";
	}
	
	@RequiresPermissions("pd:pdEncodingRuleDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PdEncodingRuleDetail pdEncodingRuleDetail, RedirectAttributes redirectAttributes) {
		pdEncodingRuleDetailService.delete(pdEncodingRuleDetail);
		addMessage(redirectAttributes, "删除编码规则详情信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingRuleDetail/?repage";
	}

}