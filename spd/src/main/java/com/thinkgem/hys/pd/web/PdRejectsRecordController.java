/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.hys.pd.entity.PdRejectsRecord;
import com.thinkgem.hys.pd.entity.PdStockLog;
import com.thinkgem.hys.pd.service.PdRejectsRecordService;
import com.thinkgem.hys.pd.service.PdStockLogService;

/**
 * 不良品记录Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdRejectsRecord")
public class PdRejectsRecordController extends BaseController {

	@Autowired
	private PdRejectsRecordService pdRejectsRecordService;
	@Autowired
	private PdStockLogService pdStockLogService;
	
	@ModelAttribute
	public PdRejectsRecord get(@RequestParam(required=false) String id) {
		PdRejectsRecord entity = new PdRejectsRecord();
		if (StringUtils.isNotBlank(id)){
			entity.setId(id);
			entity = pdRejectsRecordService.findList(entity).get(0);
		}
		if (entity == null){
			entity = new PdRejectsRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdRejectsRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdRejectsRecord pdRejectsRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdRejectsRecord.setStoreroomId(UserUtils.getUser().getStoreroomId());
		Page<PdRejectsRecord> page = pdRejectsRecordService.findPage(new Page<PdRejectsRecord>(request, response), pdRejectsRecord);
		model.addAttribute("page", page);
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdRejectsRecordService.findList(pdRejectsRecord)));
		return "hys/pd/pdRejectsRecordList";
	}

	@RequiresPermissions("pd:pdRejectsRecord:view")
	@RequestMapping(value = "form")
	public String form(PdRejectsRecord pdRejectsRecord, Model model) {
		model.addAttribute("pdRejectsRecord", pdRejectsRecord);
		//物流信息pdStockLog
		PdStockLog pdStockLog = new PdStockLog();
		pdStockLog.setProductBarCode(pdRejectsRecord.getProductBarCode());
		pdStockLog.setBatchNo(pdRejectsRecord.getBatchNo());
		pdStockLog.setProductId(pdRejectsRecord.getProductId());
		List<PdStockLog> pdStockLogList = pdStockLogService.findList(pdStockLog);
		model.addAttribute("pdStockLogList", pdStockLogList);
		return "hys/pd/pdRejectsRecordForm";
	}

	@RequiresPermissions("pd:pdRejectsRecord:edit")
	@RequestMapping(value = "save")
	public String save(PdRejectsRecord pdRejectsRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdRejectsRecord)){
			return form(pdRejectsRecord, model);
		}
		pdRejectsRecord.setStoreroomId(UserUtils.getUser().getStoreroomId());
		pdRejectsRecordService.save(pdRejectsRecord);
		addMessage(redirectAttributes, "保存不良品记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRejectsRecord/?repage";
	}
	
	@RequiresPermissions("pd:pdRejectsRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PdRejectsRecord pdRejectsRecord, RedirectAttributes redirectAttributes) {
		pdRejectsRecordService.delete(pdRejectsRecord);
		addMessage(redirectAttributes, "删除不良品记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdRejectsRecord/?repage";
	}

}