/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMatpriceadjdetail;
import com.thinkgem.hys.pd.service.DataMatpriceadjdetailService;
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
 * 药品调价单明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMatpriceadjdetail")
public class DataMatpriceadjdetailController extends BaseController {

	@Autowired
	private DataMatpriceadjdetailService dataMatpriceadjdetailService;
	
	@ModelAttribute
	public DataMatpriceadjdetail get(@RequestParam(required=false) String id) {
		DataMatpriceadjdetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMatpriceadjdetailService.get(id);
		}
		if (entity == null){
			entity = new DataMatpriceadjdetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMatpriceadjdetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMatpriceadjdetail dataMatpriceadjdetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMatpriceadjdetail> page = dataMatpriceadjdetailService.findPage(new Page<DataMatpriceadjdetail>(request, response), dataMatpriceadjdetail); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMatpriceadjdetailList";
	}

	@RequiresPermissions("pd:dataMatpriceadjdetail:view")
	@RequestMapping(value = "form")
	public String form(DataMatpriceadjdetail dataMatpriceadjdetail, Model model) {
		model.addAttribute("dataMatpriceadjdetail", dataMatpriceadjdetail);
		return "hys/pd/dataMatpriceadjdetailForm";
	}

	@RequiresPermissions("pd:dataMatpriceadjdetail:edit")
	@RequestMapping(value = "save")
	public String save(DataMatpriceadjdetail dataMatpriceadjdetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMatpriceadjdetail)){
			return form(dataMatpriceadjdetail, model);
		}
		dataMatpriceadjdetailService.save(dataMatpriceadjdetail);
		addMessage(redirectAttributes, "保存药品调价单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMatpriceadjdetail/?repage";
	}
	
	@RequiresPermissions("pd:dataMatpriceadjdetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMatpriceadjdetail dataMatpriceadjdetail, RedirectAttributes redirectAttributes) {
		dataMatpriceadjdetailService.delete(dataMatpriceadjdetail);
		addMessage(redirectAttributes, "删除药品调价单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMatpriceadjdetail/?repage";
	}

}