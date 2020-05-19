/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web.fb;

import com.thinkgem.hys.pd.entity.BaseFeeprice;
import com.thinkgem.hys.pd.service.BaseFeepriceService;
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
 * 本院收费项目目录Controller
 * @author zxh
 * @version 2019-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/baseFeeprice")
public class BaseFeepriceController extends BaseController {

	@Autowired
	private BaseFeepriceService baseFeepriceService;
	
	@ModelAttribute
	public BaseFeeprice get(@RequestParam(required=false) String id) {
		BaseFeeprice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = baseFeepriceService.get(id);
		}
		if (entity == null){
			entity = new BaseFeeprice();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:baseFeeprice:view")
	@RequestMapping(value = {"list", ""})
	public String list(BaseFeeprice baseFeeprice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BaseFeeprice> page = baseFeepriceService.findPage(new Page<BaseFeeprice>(request, response), baseFeeprice); 
		model.addAttribute("page", page);
		return "hys/pd/query/fb/baseFeepriceList";
	}

	@RequiresPermissions("pd:baseFeeprice:view")
	@RequestMapping(value = "form")
	public String form(BaseFeeprice baseFeeprice, Model model) {
		model.addAttribute("baseFeeprice", baseFeeprice);
		return "hys/pd/baseFeepriceForm";
	}

	@RequiresPermissions("pd:baseFeeprice:edit")
	@RequestMapping(value = "save")
	public String save(BaseFeeprice baseFeeprice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, baseFeeprice)){
			return form(baseFeeprice, model);
		}
		baseFeepriceService.save(baseFeeprice);
		addMessage(redirectAttributes, "保存本院收费项目目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseFeeprice/?repage";
	}
	
	@RequiresPermissions("pd:baseFeeprice:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseFeeprice baseFeeprice, RedirectAttributes redirectAttributes) {
		baseFeepriceService.delete(baseFeeprice);
		addMessage(redirectAttributes, "删除本院收费项目目录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/baseFeeprice/?repage";
	}

}