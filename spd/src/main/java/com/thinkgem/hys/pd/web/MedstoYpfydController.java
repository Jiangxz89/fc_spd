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
import com.thinkgem.hys.pd.entity.MedstoYpfyd;
import com.thinkgem.hys.pd.service.MedstoYpfydService;

/**
 * 药房发药单表Controller
 * @author zxh
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpfyd")
public class MedstoYpfydController extends BaseController {

	@Autowired
	private MedstoYpfydService medstoYpfydService;
	
	@ModelAttribute
	public MedstoYpfyd get(@RequestParam(required=false) String id) {
		MedstoYpfyd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpfydService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpfyd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpfyd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpfyd medstoYpfyd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpfyd> page = medstoYpfydService.findPage(new Page<MedstoYpfyd>(request, response), medstoYpfyd); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpfydList";
	}

	/**
	 * 发药单详情
	 * @param medstoYpfyd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpfyd:view")
	@RequestMapping(value = "findDispensingListDetail")
	public String findDispensingListDetail(MedstoYpfyd medstoYpfyd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYpfydService.findDispensingListDetail(medstoYpfyd)));
		Page<MedstoYpfyd> page = medstoYpfydService.findDispensingListDetail(new Page<MedstoYpfyd>(request, response), medstoYpfyd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpfydList";
	}

	@RequiresPermissions("pd:medstoYpfyd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpfyd medstoYpfyd, Model model) {
		model.addAttribute("medstoYpfyd", medstoYpfyd);
		return "hys/pd/medstoYpfydForm";
	}

	@RequiresPermissions("pd:medstoYpfyd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpfyd medstoYpfyd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpfyd)){
			return form(medstoYpfyd, model);
		}
		medstoYpfydService.save(medstoYpfyd);
		addMessage(redirectAttributes, "保存药房发药单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpfyd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpfyd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpfyd medstoYpfyd, RedirectAttributes redirectAttributes) {
		medstoYpfydService.delete(medstoYpfyd);
		addMessage(redirectAttributes, "删除药房发药单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpfyd/?repage";
	}

}