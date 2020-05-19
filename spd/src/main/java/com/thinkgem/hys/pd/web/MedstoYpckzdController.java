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
import com.thinkgem.hys.pd.entity.MedstoYpckzd;
import com.thinkgem.hys.pd.service.MedstoYpckzdService;

/**
 * 药品出库单Controller
 * @author zxh
 * @version 2019-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpckzd")
public class MedstoYpckzdController extends BaseController {

	@Autowired
	private MedstoYpckzdService medstoYpckzdService;
	
	@ModelAttribute
	public MedstoYpckzd get(@RequestParam(required=false) String id) {
		MedstoYpckzd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpckzdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpckzd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpckzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpckzd medstoYpckzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpckzd> page = medstoYpckzdService.findPage(new Page<MedstoYpckzd>(request, response), medstoYpckzd); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpckzdList";
	}

	/**
	 * 药品出库明细查询
	 * @param medstoYpckzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYpckzd:view")
	@RequestMapping(value = "findDrugOutDetaile")
	public String findDrugOutDetaile(MedstoYpckzd medstoYpckzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYpckzdService.findDrugOutDetaile(medstoYpckzd)));
		Page<MedstoYpckzd> page = medstoYpckzdService.findDrugOutDetaile(new Page<MedstoYpckzd>(request, response), medstoYpckzd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpckzdList";
	}

	@RequiresPermissions("pd:medstoYpckzd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpckzd medstoYpckzd, Model model) {
		model.addAttribute("medstoYpckzd", medstoYpckzd);
		return "hys/pd/medstoYpckzdForm";
	}

	@RequiresPermissions("pd:medstoYpckzd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpckzd medstoYpckzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpckzd)){
			return form(medstoYpckzd, model);
		}
		medstoYpckzdService.save(medstoYpckzd);
		addMessage(redirectAttributes, "保存药品出库单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpckzd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpckzd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpckzd medstoYpckzd, RedirectAttributes redirectAttributes) {
		medstoYpckzdService.delete(medstoYpckzd);
		addMessage(redirectAttributes, "删除药品出库单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpckzd/?repage";
	}

}