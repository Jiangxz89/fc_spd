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
import com.thinkgem.hys.pd.entity.MedstoYpdjd;
import com.thinkgem.hys.pd.service.MedstoYpdjdService;

/**
 * 药品调价单表Controller
 * @author zxh
 * @version 2019-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpdjd")
public class MedstoYpdjdController extends BaseController {

	@Autowired
	private MedstoYpdjdService medstoYpdjdService;
	
	@ModelAttribute
	public MedstoYpdjd get(@RequestParam(required=false) String id) {
		MedstoYpdjd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpdjdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpdjd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpdjd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpdjd medstoYpdjd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpdjd> page = medstoYpdjdService.findPage(new Page<MedstoYpdjd>(request, response), medstoYpdjd); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpdjdList";
	}

	/**
	 * 药品调价单明细
	 * @param medstoYpdjd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpdjd:view")
	@RequestMapping(value = "findDrugPriceAdjustment")
	public String findDrugPriceAdjustment(MedstoYpdjd medstoYpdjd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYpdjdService.findDrugPriceAdjustment(medstoYpdjd)));
		Page<MedstoYpdjd> page = medstoYpdjdService.findDrugPriceAdjustment(new Page<MedstoYpdjd>(request, response), medstoYpdjd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpdjdList";
	}

	@RequiresPermissions("pd:medstoYpdjd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpdjd medstoYpdjd, Model model) {
		model.addAttribute("medstoYpdjd", medstoYpdjd);
		return "hys/pd/medstoYpdjdForm";
	}

	@RequiresPermissions("pd:medstoYpdjd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpdjd medstoYpdjd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpdjd)){
			return form(medstoYpdjd, model);
		}
		medstoYpdjdService.save(medstoYpdjd);
		addMessage(redirectAttributes, "保存药品调价单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpdjd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpdjd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpdjd medstoYpdjd, RedirectAttributes redirectAttributes) {
		medstoYpdjdService.delete(medstoYpdjd);
		addMessage(redirectAttributes, "删除药品调价单表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpdjd/?repage";
	}

}