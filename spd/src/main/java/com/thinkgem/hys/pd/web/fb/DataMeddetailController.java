/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMeddetail;
import com.thinkgem.hys.pd.service.DataMeddetailService;
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
 * 药品入库明细Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMeddetail")
public class DataMeddetailController extends BaseController {

	@Autowired
	private DataMeddetailService dataMeddetailService;
	
	@ModelAttribute
	public DataMeddetail get(@RequestParam(required=false) String id) {
		DataMeddetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMeddetailService.get(id);
		}
		if (entity == null){
			entity = new DataMeddetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMeddetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMeddetail dataMeddetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMeddetail> page = dataMeddetailService.findPage(new Page<DataMeddetail>(request, response), dataMeddetail); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMeddetailList";
	}

	@RequiresPermissions("pd:dataMeddetail:view")
	@RequestMapping(value = "form")
	public String form(DataMeddetail dataMeddetail, Model model) {
		model.addAttribute("dataMeddetail", dataMeddetail);
		return "hys/pd/dataMeddetailForm";
	}

	@RequiresPermissions("pd:dataMeddetail:edit")
	@RequestMapping(value = "save")
	public String save(DataMeddetail dataMeddetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMeddetail)){
			return form(dataMeddetail, model);
		}
		dataMeddetailService.save(dataMeddetail);
		addMessage(redirectAttributes, "保存医院药品退药单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMeddetail/?repage";
	}
	
	@RequiresPermissions("pd:dataMeddetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMeddetail dataMeddetail, RedirectAttributes redirectAttributes) {
		dataMeddetailService.delete(dataMeddetail);
		addMessage(redirectAttributes, "删除医院药品退药单明细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMeddetail/?repage";
	}

}