/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataDetailpan;
import com.thinkgem.hys.pd.service.DataDetailpanService;
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
 * 药品盘点单明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataDetailpan")
public class DataDetailpanController extends BaseController {

	@Autowired
	private DataDetailpanService dataDetailpanService;
	
	@ModelAttribute
	public DataDetailpan get(@RequestParam(required=false) String id) {
		DataDetailpan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataDetailpanService.get(id);
		}
		if (entity == null){
			entity = new DataDetailpan();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataDetailpan:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataDetailpan dataDetailpan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataDetailpan> page = dataDetailpanService.findPage(new Page<DataDetailpan>(request, response), dataDetailpan); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/DataDetailpanList";
	}

	@RequiresPermissions("pd:dataDetailpan:view")
	@RequestMapping(value = "form")
	public String form(DataDetailpan dataDetailpan, Model model) {
		model.addAttribute("dataDetailpan", dataDetailpan);
		return "hys/pd/dataDetailpanForm";
	}

	@RequiresPermissions("pd:dataDetailpan:edit")
	@RequestMapping(value = "save")
	public String save(DataDetailpan dataDetailpan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataDetailpan)){
			return form(dataDetailpan, model);
		}
		dataDetailpanService.save(dataDetailpan);
		addMessage(redirectAttributes, "保存药品盘点单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataDetailpan/?repage";
	}
	
	@RequiresPermissions("pd:dataDetailpan:edit")
	@RequestMapping(value = "delete")
	public String delete(DataDetailpan dataDetailpan, RedirectAttributes redirectAttributes) {
		dataDetailpanService.delete(dataDetailpan);
		addMessage(redirectAttributes, "删除药品盘点单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataDetailpan/?repage";
	}

}