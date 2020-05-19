/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrol;
import com.thinkgem.hys.pd.entity.PdStoreroomPatrolRecord;
import com.thinkgem.hys.pd.service.PdStoreroomPatrolRecordService;
import com.thinkgem.hys.pd.service.PdStoreroomPatrolService;

/**
 * 库房巡查记录Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStoreroomPatrolRecord")
public class PdStoreroomPatrolRecordController extends BaseController {

	@Autowired
	private PdStoreroomPatrolRecordService pdStoreroomPatrolRecordService;
	@Autowired
	private PdStoreroomPatrolService pdStoreroomPatrolService;
	
	@ModelAttribute
	public PdStoreroomPatrolRecord get(@RequestParam(required=false) String id) {
		PdStoreroomPatrolRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStoreroomPatrolRecordService.get(id);
		}
		if (entity == null){
			entity = new PdStoreroomPatrolRecord();
		}
		return entity;
	}
	
	//@RequiresPermissions("pd:pdStoreroomPatrolRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStoreroomPatrolRecord pdStoreroomPatrolRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdStoreroomPatrolRecord> page = pdStoreroomPatrolRecordService.findPage(new Page<PdStoreroomPatrolRecord>(request, response), pdStoreroomPatrolRecord); 
		model.addAttribute("page", page);
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStoreroomPatrolRecordService.findList(pdStoreroomPatrolRecord)));
		PdStoreroomPatrol pdStoreroomPatrol = pdStoreroomPatrolService.get(pdStoreroomPatrolRecord.getPatrolCode());
		model.addAttribute("pdStoreroomPatrol", pdStoreroomPatrol);
		return "hys/pd/pdStoreroomPatrolRecordList";
	}

	//@RequiresPermissions("pd:pdStoreroomPatrolRecord:view")
	@RequestMapping(value = "form")
	public String form(PdStoreroomPatrolRecord pdStoreroomPatrolRecord, Model model) {
		PdStoreroomPatrol pdStoreroomPatrol = pdStoreroomPatrolService.get(pdStoreroomPatrolRecord.getPatrolCode());
		model.addAttribute("pdStoreroomPatrol", pdStoreroomPatrol);
		model.addAttribute("pdStoreroomPatrolRecord", pdStoreroomPatrolRecord);
		return "hys/pd/pdStoreroomPatrolRecordForm";
	}
	//修改巡查记录
	@RequestMapping(value = "getModifyData")
	@ResponseBody
	public Map<String, Object> getModifyData(PdStoreroomPatrolRecord pdStoreroomPatrolRecord,  HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", pdStoreroomPatrolRecordService.findList(pdStoreroomPatrolRecord));
		map.put("pageList", pdStoreroomPatrolRecordService.findPage(new Page<PdStoreroomPatrolRecord>(request, response), pdStoreroomPatrolRecord));
		return map;
	}

	@RequiresPermissions("pd:pdStoreroomPatrolRecord:edit")
	@RequestMapping(value = "save")
	public String save(PdStoreroomPatrolRecord pdStoreroomPatrolRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStoreroomPatrolRecord)){
			return form(pdStoreroomPatrolRecord, model);
		}
		pdStoreroomPatrolRecordService.save(pdStoreroomPatrolRecord);
		addMessage(redirectAttributes, "保存库房巡查记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomPatrolRecord/?repage";
	}
	
	@RequiresPermissions("pd:pdStoreroomPatrolRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStoreroomPatrolRecord pdStoreroomPatrolRecord, RedirectAttributes redirectAttributes) {
		pdStoreroomPatrolRecordService.delete(pdStoreroomPatrolRecord);
		addMessage(redirectAttributes, "删除库房巡查记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStoreroomPatrolRecord/?repage";
	}

}