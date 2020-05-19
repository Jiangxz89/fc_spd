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
import com.thinkgem.hys.pd.entity.MedstoYppdd;
import com.thinkgem.hys.pd.service.MedstoYppddService;

/**
 * 药品盘点表Controller
 * @author zxh
 * @version 2019-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYppdd")
public class MedstoYppddController extends BaseController {

	@Autowired
	private MedstoYppddService medstoYppddService;
	
	@ModelAttribute
	public MedstoYppdd get(@RequestParam(required=false) String id) {
		MedstoYppdd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYppddService.get(id);
		}
		if (entity == null){
			entity = new MedstoYppdd();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYppdd:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYppdd medstoYppdd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYppdd> page = medstoYppddService.findPage(new Page<MedstoYppdd>(request, response), medstoYppdd); 
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYppddList";
	}

	/**
	 * 药品盘点单明细
	 * @param medstoYppdd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:medstoYppdd:view")
	@RequestMapping(value = "findDrugInventoryDetailed")
	public String findDrugInventoryDetailed(MedstoYppdd medstoYppdd, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYppddService.findDrugInventoryDetailed(medstoYppdd)));
		Page<MedstoYppdd> page = medstoYppddService.findDrugInventoryDetailed(new Page<MedstoYppdd>(request, response), medstoYppdd);
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYppddList";
	}

	@RequiresPermissions("pd:medstoYppdd:view")
	@RequestMapping(value = "form")
	public String form(MedstoYppdd medstoYppdd, Model model) {
		model.addAttribute("medstoYppdd", medstoYppdd);
		return "hys/pd/medstoYppddForm";
	}

	@RequiresPermissions("pd:medstoYppdd:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYppdd medstoYppdd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYppdd)){
			return form(medstoYppdd, model);
		}
		medstoYppddService.save(medstoYppdd);
		addMessage(redirectAttributes, "保存药品盘点表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYppdd/?repage";
	}
	
	@RequiresPermissions("pd:medstoYppdd:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYppdd medstoYppdd, RedirectAttributes redirectAttributes) {
		medstoYppddService.delete(medstoYppdd);
		addMessage(redirectAttributes, "删除药品盘点表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYppdd/?repage";
	}

}