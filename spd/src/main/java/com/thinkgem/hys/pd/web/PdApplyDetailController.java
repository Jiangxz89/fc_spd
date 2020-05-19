/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.thinkgem.hys.pd.entity.PdApplyDetail;
import com.thinkgem.hys.pd.entity.PdApplyOrder;
import com.thinkgem.hys.pd.entity.vo.ApplyDetailVo;
import com.thinkgem.hys.pd.service.PdApplyDetailService;
import com.thinkgem.hys.pd.service.PdApplyOrderService;
import com.thinkgem.hys.pd.service.PdProductMPackageService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 申领单详细Controller
 * @author wg
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdApplyDetail")
public class PdApplyDetailController extends BaseController {

	@Autowired
	private PdApplyDetailService pdApplyDetailService;
	@Autowired
	private PdApplyOrderService pdApplyOrderService;
	@Autowired
	private PdProductMPackageService pdProductMPackageService;
	
	@ModelAttribute
	public PdApplyDetail get(@RequestParam(required=false) String id) {
		PdApplyDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdApplyDetailService.get(id);
		}
		if (entity == null){
			entity = new PdApplyDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdApplyDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdApplyDetail pdApplyDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdApplyDetail> page = pdApplyDetailService.findPage(new Page<PdApplyDetail>(request, response), pdApplyDetail); 
		model.addAttribute("page", page);
		return "hys/pd/pdApplyDetailList";
	}

	//@RequiresPermissions("pd:pdApplyDetail:view")
	@RequestMapping(value = "form")
	public String form(PdApplyDetail pdApplyDetail, Model model) {
		model.addAttribute("pdApplyDetail", pdApplyDetail);
		PdApplyOrder order = pdApplyOrderService.get(pdApplyDetail.getApplyNo());
		model.addAttribute("pdApplyOrder", order);
		return "hys/pd/pdApplyDetailForm";
	}
	/**
	 * 异步加载申领单详情页
	 * @param pdApplyDetail
	 * @return
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getPackageData(PdApplyDetail pdApplyDetail,String flag){
		pdApplyDetail.setStoreroomId(UserUtils.getUser().getStoreroomId());
		PdApplyOrder paOrder = pdApplyOrderService.get(pdApplyDetail.getApplyNo());
		if("auditForm".equals(flag)){//申领单审核页面，传入申领科室以便查询库存
			pdApplyDetail.setDeptId(paOrder.getDeptId());
		}
		List<PdApplyDetail> list = pdApplyDetailService.findList(pdApplyDetail);
		Set<String> myset = new HashSet<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		List<PdApplyDetail> prodList = new ArrayList<PdApplyDetail>();
		List<ApplyDetailVo> voList = new ArrayList<ApplyDetailVo>();

		Integer productTotNum = 0;
		BigDecimal sumInPrice = new BigDecimal(0);
		BigDecimal sumOutPrice = new BigDecimal(0);

		for(PdApplyDetail pd : list){
			productTotNum = pd.getApplyCount() + productTotNum;
			if(pd.getPdOutTotalPrice() != null){
				sumInPrice = sumInPrice.add(pd.getPdTotalPrice());
				sumOutPrice = sumOutPrice.add(pd.getPdOutTotalPrice());
			}

			if(StringUtils.isEmpty(pd.getPackageId())){
				prodList.add(pd);
			}else{
				if(myset.contains(pd.getPackageId())){
					continue;
				}

				ApplyDetailVo vo = new ApplyDetailVo();
				vo.setPackageId(pd.getPackageId());
				vo.setPackageName(pd.getPackageName());
				vo.setPackageCount(pd.getPackageCount());
				vo.setPackageNumber(pd.getPackageNumber());
				
				if("auditForm".equals(flag)){//申领单审核页面
					vo.setList(pdProductMPackageService.getProdListByPackageId(UserUtils.getUser().getStoreroomId(),paOrder.getDeptId(), pd.getPackageId()));
				}else if("viewForm".equals(flag)){//申领单查看页面
					PdApplyDetail pap = new PdApplyDetail();
					pap.setPackageId(pd.getPackageId());
					pap.setApplyNo(pdApplyDetail.getApplyNo());
					vo.setDetail(pdApplyDetailService.findList(pap));
				}else{
					vo.setList(pdProductMPackageService.getProdListByPackageId(UserUtils.getUser().getStoreroomId(),null, pd.getPackageId()));
				}
				voList.add(vo);
				myset.add(pd.getPackageId());
			}
		}

		map.put("productTotNum", productTotNum);
		map.put("sumInPrice", sumInPrice);
		map.put("sumOutPrice", sumOutPrice);
		map.put("productList", prodList);
		map.put("packageList", voList);
		return map;
	}

	@RequiresPermissions("pd:pdApplyDetail:edit")
	@RequestMapping(value = "save")
	public String save(PdApplyDetail pdApplyDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdApplyDetail)){
			return form(pdApplyDetail, model);
		}
		pdApplyDetailService.save(pdApplyDetail);
		addMessage(redirectAttributes, "保存申领单详细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdApplyDetail/?repage";
	}
	
	@RequiresPermissions("pd:pdApplyDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PdApplyDetail pdApplyDetail, RedirectAttributes redirectAttributes) {
		pdApplyDetailService.delete(pdApplyDetail);
		addMessage(redirectAttributes, "删除申领单详细成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdApplyDetail/?repage";
	}

}