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
import com.thinkgem.hys.pd.entity.MedstoYpthmx;
import com.thinkgem.hys.pd.entity.MedstoYpthzd;
import com.thinkgem.hys.pd.service.MedstoYpthmxService;
import com.thinkgem.hys.pd.service.MedstoYpthzdService;

/**
 * 药品退货明细Controller
 * @author wg
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpthmx")
public class MedstoYpthmxController extends BaseController {

	@Autowired
	private MedstoYpthmxService medstoYpthmxService;
	@Autowired
	private MedstoYpthzdService medstoYpthzdService;
	
	@ModelAttribute
	public MedstoYpthmx get(@RequestParam(required=false) String id) {
		MedstoYpthmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpthmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpthmx();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpthmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpthmx medstoYpthmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpthmx> page = medstoYpthmxService.findPage(new Page<MedstoYpthmx>(request, response), medstoYpthmx); 
		model.addAttribute("page", page);
		return "hys/pd/medstoYpthmxList";
	}

	@RequiresPermissions("pd:medstoYpthmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpthmx medstoYpthmx, Model model) {
		
		MedstoYpthzd medstoYpthzd = medstoYpthzdService.get(medstoYpthmx.getZdXh());
		
		model.addAttribute("medstoYpthmxList", medstoYpthmxService.findList(medstoYpthmx));
		model.addAttribute("medstoYpthmx", medstoYpthmx);
		model.addAttribute("medstoYpthzd", medstoYpthzd);
		return "hys/pd/pdDrugRefundDetail";
	}

	@RequiresPermissions("pd:medstoYpthmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpthmx medstoYpthmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpthmx)){
			return form(medstoYpthmx, model);
		}
		medstoYpthmxService.save(medstoYpthmx);
		addMessage(redirectAttributes, "保存药品退货明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpthmx/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpthmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpthmx medstoYpthmx, RedirectAttributes redirectAttributes) {
		medstoYpthmxService.delete(medstoYpthmx);
		addMessage(redirectAttributes, "删除药品退货明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpthmx/?repage";
	}

}