/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataYfexedetail;
import com.thinkgem.hys.pd.service.DataYfexedetailService;
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
 * 药房发药单明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataYfexedetail")
public class DataYfexedetailController extends BaseController {

	@Autowired
	private DataYfexedetailService dataYfexedetailService;
	
	@ModelAttribute
	public DataYfexedetail get(@RequestParam(required=false) String id) {
		DataYfexedetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataYfexedetailService.get(id);
		}
		if (entity == null){
			entity = new DataYfexedetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataYfexedetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataYfexedetail dataYfexedetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataYfexedetail> page = dataYfexedetailService.findPage(new Page<DataYfexedetail>(request, response), dataYfexedetail); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataYfexedetailList";
	}

	@RequiresPermissions("pd:dataYfexedetail:view")
	@RequestMapping(value = "form")
	public String form(DataYfexedetail dataYfexedetail, Model model) {
		model.addAttribute("dataYfexedetail", dataYfexedetail);
		return "hys/pd/dataYfexedetailForm";
	}

	@RequiresPermissions("pd:dataYfexedetail:edit")
	@RequestMapping(value = "save")
	public String save(DataYfexedetail dataYfexedetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataYfexedetail)){
			return form(dataYfexedetail, model);
		}
		dataYfexedetailService.save(dataYfexedetail);
		addMessage(redirectAttributes, "保存药房发药单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataYfexedetail/?repage";
	}
	
	@RequiresPermissions("pd:dataYfexedetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DataYfexedetail dataYfexedetail, RedirectAttributes redirectAttributes) {
		dataYfexedetailService.delete(dataYfexedetail);
		addMessage(redirectAttributes, "删除药房发药单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataYfexedetail/?repage";
	}

}