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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.SnoGerUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.MedstoYpcgmx;
import com.thinkgem.hys.pd.entity.MedstoYpcgzd;
import com.thinkgem.hys.pd.service.MedstoYpcgmxService;
import com.thinkgem.hys.pd.service.MedstoYpcgzdService;

/**
 * 药库采购单Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpcgzd")
public class MedstoYpcgzdController extends BaseController {

	@Autowired
	private MedstoYpcgzdService medstoYpcgzdService;
	@Autowired
	private MedstoYpcgmxService medstoYpcgmxService;
	
	@ModelAttribute
	public MedstoYpcgzd get(@RequestParam(required=false) String id) {
		MedstoYpcgzd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcgzdService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcgzd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpcgzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcgzd medstoYpcgzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (MinaGlobalConstants.STOREROOM_TYPE_1.equals(user.getStoreroomType())) {
			medstoYpcgzd.setKsdm(user.getStoreroomId());
		}
		Page<MedstoYpcgzd> page = medstoYpcgzdService.findPage(new Page<MedstoYpcgzd>(request, response), medstoYpcgzd); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugPurchaseOrderList";
	}

	@RequiresPermissions("pd:medstoYpcgzd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcgzd medstoYpcgzd, Model model) {
		medstoYpcgzd.setXh(SnoGerUtil.getSerialNumber(18));
		model.addAttribute("medstoYpcgzd", medstoYpcgzd);
		return "hys/pd/pdDrugPurchaseOrderForm";
	}

	@RequiresPermissions("pd:medstoYpcgzd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcgzd medstoYpcgzd, Model model, RedirectAttributes redirectAttributes) {
		medstoYpcgzdService.saveYpcgzd(medstoYpcgzd);
		addMessage(redirectAttributes, "保存药库采购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcgzd/?repage";
	}
	//手动同步到中心平台
	@RequestMapping(value = "syncData")
	public String syncData(MedstoYpcgzd medstoYpcgzd, Model model, RedirectAttributes redirectAttributes) {
		MedstoYpcgmx mx = new MedstoYpcgmx();
		mx.setCgxh(medstoYpcgzd.getXh());
		medstoYpcgzd.setPurchaseDetailList(medstoYpcgmxService.findList(mx));
		String rst = medstoYpcgzdService.syncData(medstoYpcgzd);
		addMessage(redirectAttributes, rst);
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcgzd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpcgzd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcgzd medstoYpcgzd, RedirectAttributes redirectAttributes) {
		medstoYpcgzdService.delete(medstoYpcgzd);
		addMessage(redirectAttributes, "删除药库采购单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcgzd/?repage";
	}

}