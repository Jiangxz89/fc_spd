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

import com.thinkgem.hys.pd.entity.MedstoYpcgmx;
import com.thinkgem.hys.pd.entity.MedstoYpcgzd;
import com.thinkgem.hys.pd.service.MedstoYpcgmxService;
import com.thinkgem.hys.pd.service.MedstoYpcgzdService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 药库采购明细Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpcgmx")
public class MedstoYpcgmxController extends BaseController {

	@Autowired
	private MedstoYpcgmxService medstoYpcgmxService;
	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	
	@ModelAttribute
	public MedstoYpcgmx get(@RequestParam(required=false) String id) {
		MedstoYpcgmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcgmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcgmx();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpcgmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcgmx medstoYpcgmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<MedstoYpcgmx> page = medstoYpcgmxService.findPage(new Page<MedstoYpcgmx>(request, response), medstoYpcgmx); 
		MedstoYpcgzd zd = medstoYpcgzdService.get(medstoYpcgmx.getCgxh());
		model.addAttribute("medstoYpcgzd", zd);
		model.addAttribute("pageList", medstoYpcgmxService.findList(medstoYpcgmx));
		return "hys/pd/pdDrugPurchaseOrderDetail";
	}

	@RequiresPermissions("pd:medstoYpcgmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcgmx medstoYpcgmx, Model model) {
		model.addAttribute("medstoYpcgmx", medstoYpcgmx);
		return "hys/pd/medstoYpcgmxForm";
	}

	@RequiresPermissions("pd:medstoYpcgmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcgmx medstoYpcgmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpcgmx)){
			return form(medstoYpcgmx, model);
		}
		medstoYpcgmxService.save(medstoYpcgmx);
		addMessage(redirectAttributes, "保存药库采购明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcgmx/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpcgmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcgmx medstoYpcgmx, RedirectAttributes redirectAttributes) {
		medstoYpcgmxService.delete(medstoYpcgmx);
		addMessage(redirectAttributes, "删除药库采购明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcgmx/?repage";
	}

}