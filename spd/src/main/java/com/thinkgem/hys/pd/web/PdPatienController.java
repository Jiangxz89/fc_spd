/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.entity.PdPatien;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.service.PdPatienService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * pdPatienController
 * @author jiangxz
 * @version 2019-10-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdPatien")
public class PdPatienController extends BaseController {

	@Autowired
	private PdPatienService pdPatienService;
	
	@ModelAttribute
	public PdPatien get(@RequestParam(required=false) String id) {
		PdPatien entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdPatienService.get(id);
		}
		if (entity == null){
			entity = new PdPatien();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdPatien:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdPatien pdPatien, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdPatien> page = pdPatienService.findPage(new Page<PdPatien>(request, response), pdPatien);
		model.addAttribute("pdPatien", pdPatien);
		model.addAttribute("page", page);
		return "hys/pd/pdPatienList";
	}

	@RequiresPermissions("pd:pdPatien:view")
	@RequestMapping(value = "form")
	public String form(PdPatien pdPatien, Model model, HttpServletRequest request) {
        String flag = request.getParameter("flag");
        String id = request.getParameter("id");
        if(StringUtils.isNotEmpty(id)){
            pdPatien = pdPatienService.get(id);
        }

		model.addAttribute("pdPatien", pdPatien);
        model.addAttribute("flag", flag);

		return "hys/pd/pdPatienForm";
	}

	@RequiresPermissions("pd:pdPatien:edit")
	@RequestMapping(value = "save")
	public String save(PdPatien pdPatien, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, pdPatien)){
//			return form(pdPatien, model);
//		}

		pdPatienService.save(pdPatien);
		addMessage(redirectAttributes, "保存病人信息成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdPatien/?repage";
	}

    @RequiresPermissions("pd:pdPatien:edit")
    @RequestMapping(value = "update")
    public String update(PdPatien pdPatien, Model model, RedirectAttributes redirectAttributes) {

        pdPatienService.update(pdPatien);
        addMessage(redirectAttributes, "更新病人信息成功");
        return "redirect:"+Global.getAdminPath()+"/pd/pdPatien/?repage";
    }
	@RequiresPermissions("pd:pdPatien:edit")
	@RequestMapping(value = "delete")
	public void delete(PdPatien pdPatien, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		pdPatien.setId(request.getParameter("id"));
		pdPatienService.delete(pdPatien);

		addMessage(redirectAttributes, "删除病人成功");

//		return "redirect:"+Global.getAdminPath()+"/pd/pdPatien/?repage";
	}

    @RequestMapping(value = "choosePatienList")
    public String choosePatienList(PdPatien pdPatien, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PdPatien> page = pdPatienService.findPage(new Page<PdPatien>(request, response), pdPatien);
        model.addAttribute("pdPatien", pdPatien);
        model.addAttribute("page", page);
        return "hys/pd/box/pdPatienBox";
    }
}