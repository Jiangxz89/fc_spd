/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMasterpan;
import com.thinkgem.hys.pd.service.DataMasterpanService;
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
 * 药品盘点单Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMasterpan")
public class DataMasterpanController extends BaseController {

	@Autowired
	private DataMasterpanService dataMasterpanService;
	
	@ModelAttribute
	public DataMasterpan get(@RequestParam(required=false) String id) {
		DataMasterpan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMasterpanService.get(id);
		}
		if (entity == null){
			entity = new DataMasterpan();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMasterpan:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMasterpan dataMasterpan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMasterpan> page = dataMasterpanService.findPage(new Page<DataMasterpan>(request, response), dataMasterpan); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMasterpanList";
	}

	@RequiresPermissions("pd:dataMasterpan:view")
	@RequestMapping(value = "form")
	public String form(DataMasterpan dataMasterpan, Model model) {
		model.addAttribute("dataMasterpan", dataMasterpan);
		return "hys/pd/dataMasterpanForm";
	}

	@RequiresPermissions("pd:dataMasterpan:edit")
	@RequestMapping(value = "save")
	public String save(DataMasterpan dataMasterpan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMasterpan)){
			return form(dataMasterpan, model);
		}
		dataMasterpanService.save(dataMasterpan);
		addMessage(redirectAttributes, "保存药品盘点单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMasterpan/?repage";
	}
	
	@RequiresPermissions("pd:dataMasterpan:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMasterpan dataMasterpan, RedirectAttributes redirectAttributes) {
		dataMasterpanService.delete(dataMasterpan);
		addMessage(redirectAttributes, "删除药品盘点单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMasterpan/?repage";
	}

}