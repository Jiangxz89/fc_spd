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
import com.thinkgem.hys.pd.entity.PdRejected;
import com.thinkgem.hys.pd.service.PdRejectedService;

/**
 * 退货信息Controller
 * @author yueguoyun
 * @version 2018-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdRejected")
public class PdRejectedController extends BaseController {

	@Autowired
	private PdRejectedService pdRejectedService;
	
	@ModelAttribute
	public PdRejected get(@RequestParam(required=false) String id) {
		PdRejected entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdRejectedService.get(id);
		}
		if (entity == null){
			entity = new PdRejected();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdRejected:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdRejected pdRejected, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdRejected> page = pdRejectedService.findPage(new Page<PdRejected>(request, response), pdRejected); 
		model.addAttribute("page", page);
		return "hys/pd/pdRejectedList";
	}

	@RequiresPermissions("pd:pdRejected:view")
	@RequestMapping(value = "form")
	public String form(PdRejected pdRejected, Model model) {
		model.addAttribute("pdRejected", pdRejected);
		return "hys/pd/pdRejectedForm";
	}

	@RequiresPermissions("pd:pdRejected:edit")
	@RequestMapping(value = "save")
	public String save(PdRejected pdRejected, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdRejected)){
			return form(pdRejected, model);
		}
		pdRejectedService.save(pdRejected);
		addMessage(redirectAttributes, "保存退货信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRejected/?repage";
	}
	
	@RequiresPermissions("pd:pdRejected:edit")
	@RequestMapping(value = "delete")
	public String delete(PdRejected pdRejected, RedirectAttributes redirectAttributes) {
		pdRejectedService.delete(pdRejected);
		addMessage(redirectAttributes, "删除退货信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRejected/?repage";
	}

}