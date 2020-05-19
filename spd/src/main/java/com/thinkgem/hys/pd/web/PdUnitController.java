/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.entity.PdGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.hys.pd.entity.PdUnit;
import com.thinkgem.hys.pd.service.PdUnitService;

import java.util.List;

/**
 * 产品单位表Controller
 * @author jiangxz
 * @version 2019-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdUnit")
public class PdUnitController extends BaseController {

	@Autowired
	private PdUnitService pdUnitService;
	
	@ModelAttribute
	public PdUnit get(@RequestParam(required=false) String id) {
		PdUnit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdUnitService.get(id);
		}
		if (entity == null){
			entity = new PdUnit();
		}
		return entity;
	}

	/**
	 * 列表
	 * @param pdUnit
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(PdUnit pdUnit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdUnit> page = pdUnitService.findPage(new Page<PdUnit>(request, response), pdUnit); 
		model.addAttribute("page", page);
		return "hys/pd/pdUnitList";
	}

//	@RequestMapping(value = "form")
//	public String form(PdUnit pdUnit, Model model) {
//		model.addAttribute("pdUnit", pdUnit);
//		return "hys/pd/pdUnitForm";
//	}

	/**
	 * 新增
	 * @param pdUnit
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(PdUnit pdUnit, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, pdUnit)){
//			return form(pdUnit, model);
//		}
		pdUnitService.save(pdUnit);
		addMessage(redirectAttributes, "保存产品单位成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdUnit/?repage";
	}

	/**
	 * 删除
	 * @param pdUnit
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(PdUnit pdUnit, RedirectAttributes redirectAttributes) {
		pdUnitService.delete(pdUnit);
		addMessage(redirectAttributes, "删除产品单位表成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdUnit/?repage";
	}


	/**
	 * @param pdUnit
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(PdUnit pdUnit, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String[] id = ids.substring(1).split(",");

		String delAllIds = "";

		boolean bo = true;
		PdUnit prod = new PdUnit();
		for(int i = 0 ; i < id.length ; i ++){
			pdUnit.setId(id[i]);

//			prod.setGroupId(id[i]);
//			List<PdProduct> findList = pdProductService.findList(prod);
//			if(findList.size() == 0){
			pdUnitService.delete(pdUnit);
//			}else{
//				bo = false;
//				delAllIds += id[i]+",";
//			}
		}
//		if(bo){
			addMessage(redirectAttributes, "删除单位成功");
			return "redirect:"+Global.getAdminPath()+"/pd/pdUnit/?repage";
//		}else{
//			String idArr = delAllIds.substring(0,delAllIds.length()-1);
//			return "redirect:"+Global.getAdminPath()+"/pd/pdGroup/?idKey="+idArr+"&delKey=error";
//		}
	}


	/**
	 * 修改
	 * @param pdGroup
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "update")
	public String update(PdUnit pdUnit, RedirectAttributes redirectAttributes) {
		List<PdUnit> pdUnits = pdUnitService.verify(pdUnit);
		if(pdUnits!=null && pdUnits.size()>0){
			addMessage(redirectAttributes, "保存单位失败,组别已存在");
		}else{
			pdUnitService.update(pdUnit);
			addMessage(redirectAttributes, "更新单位表成功");
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdUnit/?repage";
	}

	/**
	 * 校验单位名称是否已存在
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUnitName")
	public String checkGroupName( Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//查询产品组别是否已经存在
		String name = request.getParameter("name");
//		String id = request.getParameter("id");
		try {
			PdUnit pdUnit = new PdUnit();
			pdUnit.setName(name);
//			if(id!=null && !"".equals(id)){
//				pdUnit.setId(id);
//			}
			List<PdUnit> pdUnits = pdUnitService.verify(pdUnit);
			if(pdUnits!=null && pdUnits.size()>0){
				return "false";
			}else{
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}