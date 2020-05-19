/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMatpriceadj;
import com.thinkgem.hys.pd.service.DataMatpriceadjService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
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

/**
 * 药品调价单Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMatpriceadj")
public class DataMatpriceadjController extends BaseController {

	@Autowired
	private DataMatpriceadjService dataMatpriceadjService;
	
	@ModelAttribute
	public DataMatpriceadj get(@RequestParam(required=false) String id) {
		DataMatpriceadj entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMatpriceadjService.get(id);
		}
		if (entity == null){
			entity = new DataMatpriceadj();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMatpriceadj:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMatpriceadj dataMatpriceadj, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMatpriceadj> page = dataMatpriceadjService.findPage(new Page<DataMatpriceadj>(request, response), dataMatpriceadj); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMatpriceadjList";
	}

	@RequiresPermissions("pd:dataMatpriceadj:view")
	@RequestMapping(value = "form")
	public String form(DataMatpriceadj dataMatpriceadj, Model model) {
		model.addAttribute("dataMatpriceadj", dataMatpriceadj);
		return "hys/pd/dataMatpriceadjForm";
	}

	@RequiresPermissions("pd:dataMatpriceadj:edit")
	@RequestMapping(value = "save")
	public String save(DataMatpriceadj dataMatpriceadj, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMatpriceadj)){
			return form(dataMatpriceadj, model);
		}
		dataMatpriceadjService.save(dataMatpriceadj);
		addMessage(redirectAttributes, "保存药品调价单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMatpriceadj/?repage";
	}
	
	@RequiresPermissions("pd:dataMatpriceadj:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMatpriceadj dataMatpriceadj, RedirectAttributes redirectAttributes) {
		dataMatpriceadjService.delete(dataMatpriceadj);
		addMessage(redirectAttributes, "删除药品调价单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMatpriceadj/?repage";
	}

}