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
import com.thinkgem.hys.pd.entity.MedstoYpbsd;
import com.thinkgem.hys.pd.service.MedstoYpbsdService;

/**
 * 医院药品报损丢失单Controller
 * @author zxh
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpbsd")
public class MedstoYpbsdController extends BaseController {

	@Autowired
	private MedstoYpbsdService medstoYpbsdService;
	
	@ModelAttribute
	public MedstoYpbsd get(@RequestParam(required=false) String id) {
		MedstoYpbsd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpbsdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpbsd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpbsd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpbsd medstoYpbsd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpbsd> page = medstoYpbsdService.findPage(new Page<MedstoYpbsd>(request, response), medstoYpbsd); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpbsdList";
	}

	/**
	 * 药品丢失单明细
	 * @param medstoYpbsd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpbsd:view")
	@RequestMapping(value = "findDrugLostOrderDetaile")
	public String findDrugLostOrderDetaile(MedstoYpbsd medstoYpbsd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYpbsdService.findDrugLostOrderDetaile(medstoYpbsd)));
		Page<MedstoYpbsd> page = medstoYpbsdService.findDrugLostOrderDetaile(new Page<MedstoYpbsd>(request, response), medstoYpbsd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpbsdList";
	}


	@RequiresPermissions("pd:medstoYpbsd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpbsd medstoYpbsd, Model model) {
		model.addAttribute("medstoYpbsd", medstoYpbsd);
		return "hys/pd/medstoYpbsdForm";
	}

	@RequiresPermissions("pd:medstoYpbsd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpbsd medstoYpbsd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpbsd)){
			return form(medstoYpbsd, model);
		}
		medstoYpbsdService.save(medstoYpbsd);
		addMessage(redirectAttributes, "保存医院药品报损丢失单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpbsd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpbsd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpbsd medstoYpbsd, RedirectAttributes redirectAttributes) {
		medstoYpbsdService.delete(medstoYpbsd);
		addMessage(redirectAttributes, "删除医院药品报损丢失单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpbsd/?repage";
	}

}