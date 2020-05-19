/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMedmaster;
import com.thinkgem.hys.pd.service.DataMedmasterService;
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
 * 药品入库主记录Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMedmaster")
public class DataMedmasterController extends BaseController {

	@Autowired
	private DataMedmasterService dataMedmasterService;
	
	@ModelAttribute
	public DataMedmaster get(@RequestParam(required=false) String id) {
		DataMedmaster entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMedmasterService.get(id);
		}
		if (entity == null){
			entity = new DataMedmaster();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMedmaster:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMedmaster dataMedmaster, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMedmaster> page = dataMedmasterService.findPage(new Page<DataMedmaster>(request, response), dataMedmaster); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMedmasterList";
	}

	@RequiresPermissions("pd:dataMedmaster:view")
	@RequestMapping(value = "form")
	public String form(DataMedmaster dataMedmaster, Model model) {
		model.addAttribute("dataMedmaster", dataMedmaster);
		return "hys/pd/dataMedmasterForm";
	}

	@RequiresPermissions("pd:dataMedmaster:edit")
	@RequestMapping(value = "save")
	public String save(DataMedmaster dataMedmaster, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMedmaster)){
			return form(dataMedmaster, model);
		}
		dataMedmasterService.save(dataMedmaster);
		addMessage(redirectAttributes, "保存药品入库主记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMedmaster/?repage";
	}
	
	@RequiresPermissions("pd:dataMedmaster:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMedmaster dataMedmaster, RedirectAttributes redirectAttributes) {
		dataMedmasterService.delete(dataMedmaster);
		addMessage(redirectAttributes, "删除药品入库主记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMedmaster/?repage";
	}

}