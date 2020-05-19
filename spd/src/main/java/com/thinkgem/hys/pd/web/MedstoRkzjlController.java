/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
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
import com.thinkgem.hys.pd.entity.MedstoRkzjl;
import com.thinkgem.hys.pd.service.MedstoRkzjlService;

/**
 * 入库主记录Controller
 * @author zxh
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoRkzjl")
public class MedstoRkzjlController extends BaseController {

	@Autowired
	private MedstoRkzjlService medstoRkzjlService;
	
	@ModelAttribute
	public MedstoRkzjl get(@RequestParam(required=false) String id) {
		MedstoRkzjl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoRkzjlService.get(id);
		}
		if (entity == null){
			entity = new MedstoRkzjl();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoRkzjl:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoRkzjl medstoRkzjl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoRkzjl> page = medstoRkzjlService.findPage(new Page<MedstoRkzjl>(request, response), medstoRkzjl); 
		model.addAttribute("page", page);
		return "hys/pd/medstoRkzjlList";
	}

	/**
	 * 药品入库明细查询
	 * @param medstoRkzjl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpckzd:view")
	@RequestMapping(value = "findDrugInDetaile")
	public String findDrugInDetaile(MedstoRkzjl medstoRkzjl, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoRkzjlService.findDrugInDetaile(medstoRkzjl)));
		Page<MedstoRkzjl> page = medstoRkzjlService.findDrugInDetaile(new Page<MedstoRkzjl>(request, response), medstoRkzjl);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoRkzjlList";
	}

	@RequiresPermissions("pd:medstoRkzjl:view")
	@RequestMapping(value = "form")
	public String form(MedstoRkzjl medstoRkzjl, Model model) {
		model.addAttribute("medstoRkzjl", medstoRkzjl);
		return "hys/pd/medstoRkzjlForm";
	}

	@RequiresPermissions("pd:medstoRkzjl:edit")
	@RequestMapping(value = "save")
	public String save(MedstoRkzjl medstoRkzjl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoRkzjl)){
			return form(medstoRkzjl, model);
		}
		medstoRkzjlService.save(medstoRkzjl);
		addMessage(redirectAttributes, "保存入库主记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoRkzjl/?repage";
	}
	
	@RequiresPermissions("pd:medstoRkzjl:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoRkzjl medstoRkzjl, RedirectAttributes redirectAttributes) {
		medstoRkzjlService.delete(medstoRkzjl);
		addMessage(redirectAttributes, "删除入库主记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoRkzjl/?repage";
	}

}