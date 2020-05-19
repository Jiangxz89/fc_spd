/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataDetailprofit;
import com.thinkgem.hys.pd.service.DataDetailprofitService;
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
 * 医院药品报损丢失单明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataDetailprofit")
public class DataDetailprofitController extends BaseController {

	@Autowired
	private DataDetailprofitService dataDetailprofitService;
	
	@ModelAttribute
	public DataDetailprofit get(@RequestParam(required=false) String id) {
		DataDetailprofit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataDetailprofitService.get(id);
		}
		if (entity == null){
			entity = new DataDetailprofit();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataDetailprofit:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataDetailprofit dataDetailprofit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataDetailprofit> page = dataDetailprofitService.findPage(new Page<DataDetailprofit>(request, response), dataDetailprofit); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataDetailprofitList";
	}

	@RequiresPermissions("pd:dataDetailprofit:view")
	@RequestMapping(value = "form")
	public String form(DataDetailprofit dataDetailprofit, Model model) {
		model.addAttribute("dataDetailprofit", dataDetailprofit);
		return "hys/pd/dataDetailprofitForm";
	}

	@RequiresPermissions("pd:dataDetailprofit:edit")
	@RequestMapping(value = "save")
	public String save(DataDetailprofit dataDetailprofit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataDetailprofit)){
			return form(dataDetailprofit, model);
		}
		dataDetailprofitService.save(dataDetailprofit);
		addMessage(redirectAttributes, "保存医院药品报损丢失单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataDetailprofit/?repage";
	}
	
	@RequiresPermissions("pd:dataDetailprofit:edit")
	@RequestMapping(value = "delete")
	public String delete(DataDetailprofit dataDetailprofit, RedirectAttributes redirectAttributes) {
		dataDetailprofitService.delete(dataDetailprofit);
		addMessage(redirectAttributes, "删除医院药品报损丢失单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataDetailprofit/?repage";
	}

}