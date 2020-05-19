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
import com.thinkgem.hys.pd.entity.MedstoYptyd;
import com.thinkgem.hys.pd.service.MedstoYptydService;

/**
 * 医院药品退药单表Controller
 * @author zxh
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYptyd")
public class MedstoYptydController extends BaseController {

	@Autowired
	private MedstoYptydService medstoYptydService;
	
	@ModelAttribute
	public MedstoYptyd get(@RequestParam(required=false) String id) {
		MedstoYptyd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYptydService.get(id);
		}
		if (entity == null){
			entity = new MedstoYptyd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYptyd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYptyd medstoYptyd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYptyd> page = medstoYptydService.findPage(new Page<MedstoYptyd>(request, response), medstoYptyd); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYptydList";
	}

	/**
	 * 药品退药单查询
	 * @param medstoYptyd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYptyd:view")
	@RequestMapping(value = "findDrugReturnBillDetaile")
	public String findDrugReturnBillDetaile(MedstoYptyd medstoYptyd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYptydService.findDrugReturnBillDetaile(medstoYptyd)));
		Page<MedstoYptyd> page = medstoYptydService.findDrugReturnBillDetaile(new Page<MedstoYptyd>(request, response), medstoYptyd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYptydList";
	}

	@RequiresPermissions("pd:medstoYptyd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYptyd medstoYptyd, Model model) {
		model.addAttribute("medstoYptyd", medstoYptyd);
		return "hys/pd/medstoYptydForm";
	}

	@RequiresPermissions("pd:medstoYptyd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYptyd medstoYptyd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYptyd)){
			return form(medstoYptyd, model);
		}
		medstoYptydService.save(medstoYptyd);
		addMessage(redirectAttributes, "保存医院药品退药单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYptyd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYptyd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYptyd medstoYptyd, RedirectAttributes redirectAttributes) {
		medstoYptydService.delete(medstoYptyd);
		addMessage(redirectAttributes, "删除医院药品退药单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYptyd/?repage";
	}

}