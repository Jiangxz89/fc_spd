/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdDosage;
import com.thinkgem.hys.pd.entity.PdDosageDetail;
import com.thinkgem.hys.pd.service.PdDosageDetailService;
import com.thinkgem.hys.pd.service.PdDosageService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import java.util.HashMap;
import java.util.List;

/**
 * 器械用量详情Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdDosageDetail")
public class PdDosageDetailController extends BaseController {

	@Autowired
	private PdDosageDetailService pdDosageDetailService;
	@Autowired
	private PdDosageService pdDosageService;
	
	@ModelAttribute
	public PdDosageDetail get(@RequestParam(required=false) String id) {
		PdDosageDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdDosageDetailService.get(id);
		}
		if (entity == null){
			entity = new PdDosageDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdDosageDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdDosageDetail pdDosageDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdDosageDetail> page = pdDosageDetailService.findPage(new Page<PdDosageDetail>(request, response), pdDosageDetail); 
		model.addAttribute("page", page);
		return "hys/pd/pdDosageDetailList";
	}

	/**
	 * 查看用量详情
	 * @param pdDosageDetail
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdDosageDetail:view")
	@RequestMapping(value = "form")
	public String form(PdDosageDetail pdDosageDetail, Model model) {
		PdDosage pdDosage = pdDosageService.get(pdDosageDetail.getDosageNo());
		model.addAttribute("pdDosage", pdDosage);
		pdDosageDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		List<PdDosageDetail> detailList = pdDosageDetailService.findList(pdDosageDetail);
		model.addAttribute("detailList", detailList);
		return "hys/pd/pdDosageDetailForm";
	}

	@RequiresPermissions("pd:pdDosageDetail:edit")
	@RequestMapping(value = "save")
	public String save(PdDosageDetail pdDosageDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdDosageDetail)){
			//return form(pdDosageDetail, model);
		}
		pdDosageDetailService.save(pdDosageDetail);
		addMessage(redirectAttributes, "保存器械用量详情成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosageDetail/?repage";
	}
	
	@RequiresPermissions("pd:pdDosageDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PdDosageDetail pdDosageDetail, RedirectAttributes redirectAttributes) {
		pdDosageDetailService.delete(pdDosageDetail);
		addMessage(redirectAttributes, "删除器械用量详情成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosageDetail/?repage";
	}

	/**
	 * 执行收费
	 * @param pdDosageDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "inChargeForm")
	public String inChargeForm(PdDosageDetail pdDosageDetail, Model model) {
		PdDosage pdDosage = pdDosageService.get(pdDosageDetail.getDosageNo());
		model.addAttribute("pdDosage", pdDosage);
		pdDosageDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		pdDosageDetail.setIsCharge("0");//要收费的产品
		List<PdDosageDetail> detailList = pdDosageDetailService.findList(pdDosageDetail);
		model.addAttribute("detailList", detailList);
		model.addAttribute("resultMap", dealData(detailList));
		model.addAttribute("detailListSize", detailList.size());
		return "hys/pd/pdDosageInChargeForm";
	}

	/**
	 * 取消收费
	 * @param pdDosageDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "offChargeForm")
	public String offChargeForm(PdDosageDetail pdDosageDetail, Model model) {
		PdDosage pdDosage = pdDosageService.get(pdDosageDetail.getDosageNo());
		model.addAttribute("pdDosage", pdDosage);
		pdDosageDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		pdDosageDetail.setIsCharge(MinaGlobalConstants.IS_CHARGE);//取消收费
		List<PdDosageDetail> detailList = pdDosageDetailService.findList(pdDosageDetail);
		model.addAttribute("detailList", detailList);
		model.addAttribute("resultMap", dealData(detailList));
        model.addAttribute("detailListSize", detailList.size());
		return "hys/pd/pdDosageOffChargeForm";
	}

	/**
	 * 库存可还回列表
	 * @param pdDosageDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "dosageRtList")
	public String dosageRtList(PdDosageDetail pdDosageDetail, Model model) {
		PdDosage pdDosage = pdDosageService.get(pdDosageDetail.getDosageNo());
		model.addAttribute("pdDosage", pdDosage);
		pdDosageDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		pdDosageDetail.setIsCharge("0");//要退回库存的产品
		List<PdDosageDetail> detailList = pdDosageDetailService.findList(pdDosageDetail);
		model.addAttribute("detailList", detailList);
		model.addAttribute("resultMap", dealData(detailList));
		model.addAttribute("detailListSize", detailList.size());
		return "hys/pd/dosageRtListForm";
	}

	/**
	 * 保存收费信息
	 * @param isCharge
	 * @param ids
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveCharge")
	public String saveCharge(String isCharge, String ids,String dosageNo, Model model, RedirectAttributes redirectAttributes) {
		//pdDosageDetailService.updateIsCharge(isCharge, ids);
		try {
			pdDosageDetailService.exeChargeOrRefund(ids, isCharge, dosageNo);
			addMessage(redirectAttributes, "保存成功");
		} catch(RuntimeException e) {
			addMessage(redirectAttributes, "保存失败");
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/pd/pdDosage/?repage";
	}
	
	private HashMap<String,Object> dealData(List<PdDosageDetail> list){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("p_num", 0);
		map.put("p_money", 0);
		if(list != null && list.size()>0){
			int allNum = 0;
			double allMoney = 0.00;
			for(PdDosageDetail pd:list){
				allNum += Integer.valueOf(pd.getDosageCount());
				if (pd.getPdProduct() != null && pd.getPdProduct().getSellingPrice() != null) {
					allMoney += Double.valueOf(pd.getDosageCount()) * Double.valueOf(pd.getPdProduct().getSellingPrice());
				}else{
					allMoney += Double.valueOf(pd.getDosageCount()) * 0;
				}
			}
			map.put("p_num", allNum);
			map.put("p_money", allMoney);
		}
		return map;
	}
}