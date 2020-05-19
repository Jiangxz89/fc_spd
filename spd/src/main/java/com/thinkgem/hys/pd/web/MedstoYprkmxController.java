/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.entity.MedstoYprkmx;
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
import com.thinkgem.hys.pd.service.MedstoYprkmxService;
import com.thinkgem.hys.pd.service.MedstoYprkzdService;

/**
 * 药库入库明细Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYprkmx")
public class MedstoYprkmxController extends BaseController {

	@Autowired
	private MedstoYprkmxService medstoYprkmxService;
	@Autowired
	private MedstoYprkzdService medstoYprkzdService;
	
	@ModelAttribute
	public MedstoYprkmx get(@RequestParam(required=false) String id) {
		MedstoYprkmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYprkmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYprkmx();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYprkmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYprkmx medstoYprkmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYprkmx> page = medstoYprkmxService.findPage(new Page<MedstoYprkmx>(request, response), medstoYprkmx); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugList";
	}

	@RequiresPermissions("pd:medstoYprkmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYprkmx medstoYprkmx, Model model) {
		//MedstoYprkzd medstoYprkzd = medstoYprkzdService.get(medstoYprkmx.getZdXh());
		//List<MedstoYprkmx> list = medstoYprkmxService.findList(medstoYprkmx);
		//model.addAttribute("medstoYprkmx", medstoYprkmx);
		//model.addAttribute("medstoYprkzd", medstoYprkzd);
		//model.addAttribute("detailList", list);
		return "hys/pd/pdDrugRkRecordDetail";
	}

	@RequiresPermissions("pd:medstoYprkmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYprkmx medstoYprkmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYprkmx)){
			return form(medstoYprkmx, model);
		}
		medstoYprkmxService.save(medstoYprkmx);
		addMessage(redirectAttributes, "保存药库入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYprkmx/?repage";
	}
	
	@RequiresPermissions("pd:medstoYprkmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYprkmx medstoYprkmx, RedirectAttributes redirectAttributes) {
		medstoYprkmxService.delete(medstoYprkmx);
		addMessage(redirectAttributes, "删除药库入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYprkmx/?repage";
	}

}