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
import com.thinkgem.hys.pd.entity.MedstoYpkcmx;
import com.thinkgem.hys.pd.service.MedstoYpkcmxService;

/**
 * 药品库存明细信息Controller
 * @author zengyanlong
 * @version 2019-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpkcmx")
public class MedstoYpkcmxController extends BaseController {

	@Autowired
	private MedstoYpkcmxService medstoYpkcmxService;
	
	@ModelAttribute
	public MedstoYpkcmx get(@RequestParam(required=false) String id) {
		MedstoYpkcmx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpkcmxService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpkcmx();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:medstoYpkcmx:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpkcmx medstoYpkcmx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpkcmx> page = medstoYpkcmxService.findPage(new Page<MedstoYpkcmx>(request, response), medstoYpkcmx);
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(medstoYpkcmxService.findCensusQuery(medstoYpkcmx)));
		model.addAttribute("page", page);
		return "hys/pd/query/medsto/medstoYpkcmxList";
	}

	@RequiresPermissions("pd:medstoYpkcmx:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpkcmx medstoYpkcmx, Model model) {
		model.addAttribute("medstoYpkcmx", medstoYpkcmx);
		return "hys/pd/medstoYpkcmxForm";
	}

	@RequiresPermissions("pd:medstoYpkcmx:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpkcmx medstoYpkcmx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, medstoYpkcmx)){
			return form(medstoYpkcmx, model);
		}
		medstoYpkcmxService.save(medstoYpkcmx);
		addMessage(redirectAttributes, "保存药品库存明细信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpkcmx/?repage";
	}
	
	@RequiresPermissions("pd:medstoYpkcmx:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpkcmx medstoYpkcmx, RedirectAttributes redirectAttributes) {
		medstoYpkcmxService.delete(medstoYpkcmx);
		addMessage(redirectAttributes, "删除药品库存明细信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpkcmx/?repage";
	}

}