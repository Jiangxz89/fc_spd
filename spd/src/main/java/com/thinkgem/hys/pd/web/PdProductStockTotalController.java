/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductStockTotal;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.pd.service.PdProductStockTotalService;
import com.thinkgem.hys.pd.service.PdStoreroomService;


/**
 * 库存记录Controller
 * @author changjundong
 * @version 2018-03-07
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStockTotal")
public class PdProductStockTotalController extends BaseController {

	@Autowired
	private PdProductStockTotalService pdProductStockTotalService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@ModelAttribute
	public PdProductStockTotal get(@RequestParam(required=false) String id) {
		PdProductStockTotal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockTotalService.get(id);
		}
		if (entity == null){
			entity = new PdProductStockTotal();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStockTotal pdProductStockTotal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductStockTotal> page = pdProductStockTotalService.findPage(new Page<PdProductStockTotal>(request, response), pdProductStockTotal); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductStockList";
	}
	
	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = {"queryPdStock"})
	public String queryPdStock(PdProductStockTotal pdProductStockTotal, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();		
		String isSel = pdProductStockTotal.getIsSel();
		String storeroomId = user.getStoreroomId();	
		String selSid = pdProductStockTotal.getStoreroomId();
		String categoryStr = pdProductStockTotal.getCategoryStr();
		String groupStr = pdProductStockTotal.getGroupStr();
        if(StringUtils.isNotEmpty(categoryStr)){
            pdProductStockTotal.setCategoryList(Arrays.asList(categoryStr.split(",")));
        }
        if(StringUtils.isNotEmpty(groupStr)){
            pdProductStockTotal.setGroupList(Arrays.asList(groupStr.split(",")));
        }

		if(StringUtils.isEmpty(selSid)){
			pdProductStockTotal.setStoreroomId(storeroomId);
			model.addAttribute("storeroomId", storeroomId);
		}else{
			if("0".equals(selSid))pdProductStockTotal.setStoreroomId(null);
			model.addAttribute("storeroomId", selSid);
		}		
		List<PdStoreroom> list = pdStoreroomService.findList(new PdStoreroom());
		model.addAttribute("storeroomList", list);			
		Page<PdProductStockTotal> page = pdProductStockTotalService.findListForQuery(new Page<PdProductStockTotal>(request, response), pdProductStockTotal); 
		//不分页数据提示数量
		PdProductStockTotal stockTotal = new PdProductStockTotal();
		if(StringUtils.isNotEmpty(pdProductStockTotal.getStoreroomId()))stockTotal.setStoreroomId(pdProductStockTotal.getStoreroomId());
		PdProduct product = new PdProduct();
		PdProduct pd = pdProductStockTotal.getPdProduct();
		if(pd!=null){
			if(StringUtils.isNotEmpty(pd.getName())){
				product.setName(pdProductStockTotal.getPdProduct().getName());
				stockTotal.setPdProduct(product);
			}
			if(StringUtils.isNotEmpty(pd.getNumber())){
				product.setNumber(pdProductStockTotal.getPdProduct().getNumber());
				stockTotal.setPdProduct(product);
			}
		}					
		List<PdProductStockTotal> aList = pdProductStockTotalService.findListForQuery(stockTotal);
		int gCount=0;int jCount=0;int isLcount=0;int pCount=0;int limtCount=0;
		if(aList!=null&&!aList.isEmpty()){
			for (PdProductStockTotal p : aList) {
				if(MinaGlobalConstants.PD_STATE_1.equals(p.getExpire())){
					gCount++;
				}else if(MinaGlobalConstants.PD_STATE_0.equals(p.getExpire())){
					jCount++;
				}
				if(MinaGlobalConstants.IS_LONG_1.equals(p.getIsLong())){
					isLcount++;
				}
				if(StringUtils.isNotEmpty(p.getLimitUp())&&StringUtils.isEmpty(p.getLimitDown())){
					Integer up=Integer.valueOf(p.getLimitUp());
					if(p.getStockNum()>up)limtCount++;
				}
				if(StringUtils.isNotEmpty(p.getLimitDown())&&StringUtils.isEmpty(p.getLimitUp())){
					Integer down = Integer.valueOf(p.getLimitDown());
					if(p.getStockNum()<down)limtCount++;
				}
				if(StringUtils.isNotEmpty(p.getLimitDown())&&StringUtils.isNotEmpty(p.getLimitUp())){
					Integer up=Integer.valueOf(p.getLimitUp());
					Integer down = Integer.valueOf(p.getLimitDown());
					if(p.getStockNum()>up||p.getStockNum()<down){
						limtCount++;
					}
				}
				pCount+=p.getStockNum();
			}
			model.addAttribute("isNone", "1");
		}else{
			model.addAttribute("isNone", "0");
		}
		model.addAttribute("pCount", pCount);
		model.addAttribute("gCount", gCount);
		model.addAttribute("jCount", jCount);
		model.addAttribute("isLcount", isLcount);
		model.addAttribute("limtCount", limtCount);
		model.addAttribute("page", page);
		model.addAttribute("isSel", isSel);
		model.addAttribute("categoryStr", categoryStr);
		model.addAttribute("groupStr", groupStr);
		return "hys/pd/pdProductStockList";
	}
	@ResponseBody
	@RequestMapping(value = "topCount")
	public Object topCount(PdProductStockTotal pdProductStockTotal, Model model) {
		User user = UserUtils.getUser();
		String storeroomId = user.getStoreroomId();	
		if(StringUtils.isNotEmpty(storeroomId))pdProductStockTotal.setStoreroomId(storeroomId);
		Map<String, Object> map = pdProductStockTotalService.findPdCount(pdProductStockTotal);
		return map;
	}

	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = "form")
	public String form(PdProductStockTotal pdProductStockTotal, Model model) {
		model.addAttribute("pdProductStockTotal", pdProductStockTotal);
		return "hys/pd/pdProductStockForm";
	}

	@RequiresPermissions("pd:pdProductStock:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStockTotal pdProductStockTotal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductStockTotal)){
			return form(pdProductStockTotal, model);
		}
		pdProductStockTotalService.save(pdProductStockTotal);
		addMessage(redirectAttributes, "保存库存记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStock/?repage";
	}
	
	@RequiresPermissions("pd:pdProductStock:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductStockTotal pdProductStockTotal, RedirectAttributes redirectAttributes) {
		pdProductStockTotalService.delete(pdProductStockTotal);
		addMessage(redirectAttributes, "删除库存记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStock/?repage";
	}

	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PdProductStockTotal pdProductStockTotal, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			User user = UserUtils.getUser();			
			String storeroomId = user.getStoreroomId();	
			String selSid = pdProductStockTotal.getStoreroomId();
			if(StringUtils.isEmpty(selSid)){
				pdProductStockTotal.setStoreroomId(storeroomId);
				
			}else{
				if("0".equals(selSid))pdProductStockTotal.setStoreroomId(null);				
			}
            String fileName = "库存数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<PdProductStockTotal> list = pdProductStockTotalService.findListForQuery(pdProductStockTotal);
    		new ExportExcel("库存数据", PdProductStockTotal.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出库存失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pd/pdProductStockTotal/queryPdStock";
    }
	@ResponseBody
	@RequestMapping(value="updateProductStock")
	public CommonRspVo updateProductStock(HttpServletRequest request){
		CommonRspVo vo = new CommonRspVo();
		String ids = request.getParameter("ids");
		String upNum=request.getParameter("upNum");
		String downNum=request.getParameter("downNum");
		ids=ids.replace("&quot;","\"");
		JSONArray arr = JSONArray.parseArray(ids);
		for(int i=0;i<arr.size();i++){
			JSONObject obj = (JSONObject) arr.get(i);
			String sid = (String) obj.get("storeroomId");
			String pid = (String) obj.get("productId");
			PdProductStockTotal stockTotal = new PdProductStockTotal();
			stockTotal.setProductId(pid);
			if(StringUtils.isNotEmpty(downNum))
			stockTotal.setLimitDown(downNum);		
			if(StringUtils.isNotEmpty(upNum))
			stockTotal.setLimitUp(upNum);
			if(StringUtils.isNotEmpty(sid))
			stockTotal.setStoreroomId(sid);
			pdProductStockTotalService.updateProductStock(stockTotal);
		}
		return vo;
		//return "redirect:" + adminPath + "/pd/pdProductStockTotal/queryPdStock";		
	}
	
}