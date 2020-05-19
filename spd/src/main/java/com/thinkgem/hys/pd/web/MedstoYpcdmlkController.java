/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.hys.pd.entity.MedstoYpcdmlk;
import com.thinkgem.hys.pd.service.MedstoYpcdmlkService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 药品厂家库Controller
 * @author sutianqi
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/medstoYpcdmlk")
public class MedstoYpcdmlkController extends BaseController {

	@Autowired
	private MedstoYpcdmlkService medstoYpcdmlkService;
	
	@ModelAttribute
	public MedstoYpcdmlk get(@RequestParam(required=false) String id) {
		MedstoYpcdmlk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = medstoYpcdmlkService.get(id);
		}
		if (entity == null){
			entity = new MedstoYpcdmlk();
		}
		return entity;
	}
	
//	@RequiresPermissions("pd:medstoYpcdmlk:view")
	@RequestMapping(value = {"list", ""})
	public String list(MedstoYpcdmlk medstoYpcdmlk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpcdmlk> page = medstoYpcdmlkService.findPage(new Page<MedstoYpcdmlk>(request, response), medstoYpcdmlk); 
		model.addAttribute("page", page);
		return "hys/pd/pdDrugManageList";
	}

//	@RequiresPermissions("pd:medstoYpcdmlk:view")
	@RequestMapping(value = "form")
	public String form(MedstoYpcdmlk medstoYpcdmlk, Model model, HttpServletRequest request) {
		String flag = request.getParameter("oprt");
		model.addAttribute("oprt", flag);
		model.addAttribute("medstoYpcdmlk", medstoYpcdmlk);
		return "hys/pd/pdDrugManageAddForm";
	}

//	@RequiresPermissions("pd:medstoYpcdmlk:edit")
	@RequestMapping(value = "save")
	public String save(MedstoYpcdmlk medstoYpcdmlk, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		/*if (!beanValidator(model, medstoYpcdmlk)){
			return form(medstoYpcdmlk, model, request);
		}*/
		medstoYpcdmlkService.saveDrug(medstoYpcdmlk);
		addMessage(redirectAttributes, "保存药品厂家库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcdmlk/?repage";
	}
	
//	@RequiresPermissions("pd:medstoYpcdmlk:edit")
	@RequestMapping(value = "delete")
	public String delete(MedstoYpcdmlk medstoYpcdmlk, RedirectAttributes redirectAttributes) {
		medstoYpcdmlkService.delete(medstoYpcdmlk);
		addMessage(redirectAttributes, "删除药品厂家库成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcdmlk/?repage";
	}

	//药品采购添加药品
	@RequestMapping(value = "addDrug")
	public String addDrug(MedstoYpcdmlk medstoYpcdmlk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MedstoYpcdmlk> page = medstoYpcdmlkService.findPage(new Page<MedstoYpcdmlk>(request, response), medstoYpcdmlk); 
		model.addAttribute("page", page);
		return "hys/pd/pdAddDrugBox";
	}
	
	/**
	 * 产品批量添加供应商
	 * @param ids
	 * @param supplierId
	 * @param supplierName
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "addSuppliers")
	public String addSupplers(String ids, String supplierId, String supplierName, RedirectAttributes redirectAttributes) {
		try {
			supplierName = URLDecoder.decode(supplierName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		medstoYpcdmlkService.updateSupplier(ids, supplierId, supplierName);
		addMessage(redirectAttributes, "添加供应商成功");
		return "redirect:"+Global.getAdminPath()+"/pd/medstoYpcdmlk/?repage";
	}
}