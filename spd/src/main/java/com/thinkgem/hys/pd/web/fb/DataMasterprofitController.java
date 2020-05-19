/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.DataMasterprofit;
import com.thinkgem.hys.pd.service.DataMasterprofitService;
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
 * 医院药品报损丢失单Controller
 * @author zxh
 * @version 2019-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/dataMasterprofit")
public class DataMasterprofitController extends BaseController {

	@Autowired
	private DataMasterprofitService dataMasterprofitService;
	
	@ModelAttribute
	public DataMasterprofit get(@RequestParam(required=false) String id) {
		DataMasterprofit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataMasterprofitService.get(id);
		}
		if (entity == null){
			entity = new DataMasterprofit();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:dataMasterprofit:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataMasterprofit dataMasterprofit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataMasterprofit> page = dataMasterprofitService.findPage(new Page<DataMasterprofit>(request, response), dataMasterprofit); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/dataMasterprofitList";
	}

	@RequiresPermissions("pd:dataMasterprofit:view")
	@RequestMapping(value = "form")
	public String form(DataMasterprofit dataMasterprofit, Model model) {
		model.addAttribute("dataMasterprofit", dataMasterprofit);
		return "hys/pd/dataMasterprofitForm";
	}

	@RequiresPermissions("pd:dataMasterprofit:edit")
	@RequestMapping(value = "save")
	public String save(DataMasterprofit dataMasterprofit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataMasterprofit)){
			return form(dataMasterprofit, model);
		}
		dataMasterprofitService.save(dataMasterprofit);
		addMessage(redirectAttributes, "保存医院药品报损丢失单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMasterprofit/?repage";
	}
	
	@RequiresPermissions("pd:dataMasterprofit:edit")
	@RequestMapping(value = "delete")
	public String delete(DataMasterprofit dataMasterprofit, RedirectAttributes redirectAttributes) {
		dataMasterprofitService.delete(dataMasterprofit);
		addMessage(redirectAttributes, "删除医院药品报损丢失单成功");
		return "redirect:"+Global.getAdminPath()+"/pd/dataMasterprofit/?repage";
	}

}