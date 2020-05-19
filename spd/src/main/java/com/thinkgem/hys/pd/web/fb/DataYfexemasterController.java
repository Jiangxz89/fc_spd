/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataYfexemaster;
import com.thinkgem.hys.pd.service.DataYfexemasterService;
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
 * 药房发药单Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataYfexemaster")
public class DataYfexemasterController extends BaseController {

	@Autowired
	private DataYfexemasterService dataYfexemasterService;
	
	@ModelAttribute
	public DataYfexemaster get(@RequestParam(required=false) String id) {
		DataYfexemaster entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataYfexemasterService.get(id);
		}
		if (entity == null){
			entity = new DataYfexemaster();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataYfexemaster:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataYfexemaster dataYfexemaster, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataYfexemaster> page = dataYfexemasterService.findPage(new Page<DataYfexemaster>(request, response), dataYfexemaster); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataYfexemasterList";
	}

	@RequiresPermissions("pd:dataYfexemaster:view")
	@RequestMapping(value = "form")
	public String form(DataYfexemaster dataYfexemaster, Model model) {
		model.addAttribute("dataYfexemaster", dataYfexemaster);
		return "hys/pd/dataYfexemasterForm";
	}

	@RequiresPermissions("pd:dataYfexemaster:edit")
	@RequestMapping(value = "save")
	public String save(DataYfexemaster dataYfexemaster, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataYfexemaster)){
			return form(dataYfexemaster, model);
		}
		dataYfexemasterService.save(dataYfexemaster);
		addMessage(redirectAttributes, "保存药房发药单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataYfexemaster/?repage";
	}
	
	@RequiresPermissions("pd:dataYfexemaster:edit")
	@RequestMapping(value = "delete")
	public String delete(DataYfexemaster dataYfexemaster, RedirectAttributes redirectAttributes) {
		dataYfexemasterService.delete(dataYfexemaster);
		addMessage(redirectAttributes, "删除药房发药单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataYfexemaster/?repage";
	}

}