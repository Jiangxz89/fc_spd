/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdStockRecordProduct;
import com.thinkgem.hys.pd.service.PdStockRecordProductService;
import jersey.repackaged.com.google.common.collect.Sets;
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
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.hys.pd.entity.PdProductStock;
import com.thinkgem.hys.pd.entity.PdRpInventory;
import com.thinkgem.hys.pd.service.PdProductStockService;
import com.thinkgem.hys.pd.service.PdRpInventoryService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 库存记录Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdProductStock")
public class PdProductStockController extends BaseController {

	@Autowired
	private PdProductStockService pdProductStockService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdRpInventoryService pdRpInventoryService;
	@Autowired
	private PdStockRecordProductService pdStockRecordProductService;


	@ModelAttribute
	public PdProductStock get(@RequestParam(required=false) String id) {
		PdProductStock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdProductStockService.get(id);
		}
		if (entity == null){
			entity = new PdProductStock();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdProductStock pdProductStock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdProductStock> page = pdProductStockService.findPage(new Page<PdProductStock>(request, response), pdProductStock); 
		model.addAttribute("page", page);
		return "hys/pd/pdProductStockList";
	}
	
	

	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = "form")
	public String form(PdProductStock pdProductStock, Model model) {
		model.addAttribute("pdProductStock", pdProductStock);
		return "hys/pd/pdProductStockForm";
	}

	@RequiresPermissions("pd:pdProductStock:edit")
	@RequestMapping(value = "save")
	public String save(PdProductStock pdProductStock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdProductStock)){
			return form(pdProductStock, model);
		}
		pdProductStockService.save(pdProductStock);
		addMessage(redirectAttributes, "保存库存记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStock/?repage";
	}
	
	@RequiresPermissions("pd:pdProductStock:edit")
	@RequestMapping(value = "delete")
	public String delete(PdProductStock pdProductStock, RedirectAttributes redirectAttributes) {
		pdProductStockService.delete(pdProductStock);
		addMessage(redirectAttributes, "删除库存记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdProductStock/?repage";
	}
	
	/**
	 * 获取库存中产品的信息
	 */
	@RequestMapping(value = "getProductInfo")
	@ResponseBody
	public Object getProductInfo(PdProductStock pdProductStock){
		pdProductStock.setStoreroomId(UserUtils.getUser().getStoreroomId());
		return pdProductStockService.getByProductBarCode(pdProductStock);
	}

	/**
	 * 在器械使用时获取库存中产品的信息
	 */
	@RequestMapping(value = "getProductInfoFromDosage")
	@ResponseBody
	public Object getProductInfoFromDosage(PdProductStock pdProductStock){
		pdProductStock.setStoreroomId(UserUtils.getUser().getStoreroomId());
		List<PdProductStock> list = pdProductStockService.getByProductBarCode(pdProductStock); 
		Set<String> batchNoList = Sets.newHashSet();
		JSONObject stockMap = new JSONObject();//批号对应的库存
		JSONObject validDateMap = new JSONObject();//批号对应的有效期
		if(list != null && list.size() > 0){
			for(PdProductStock ps : list){
				batchNoList.add(ps.getBatchNo());
				stockMap.put(ps.getBatchNo(), ps.getStockNum());
				validDateMap.put(ps.getBatchNo(), DateUtils.formatDate(ps.getValidDate(), "yyyy-MM-dd"));
			}
			PdProductStock temp = list.get(0);
			String chargeCodes = temp.getPdProduct().getChargeCode();
			if(StringUtils.isNotEmpty(chargeCodes)){
				temp.setChargeCodeList(Arrays.asList(chargeCodes.split(",")));
			}
			temp.setStockMap(JSONObject.toJSONString(stockMap));
			temp.setValidDateMap(JSONObject.toJSONString(validDateMap));
			temp.setBatchNoList(batchNoList);
			return temp;
		}
		
		return null;
	}

	//出库退货器械使用扫码2019年7月4日10:31:11
	@ResponseBody
	@RequestMapping(value="getStockRecordOutScan")
	public List<PdProductStock> getStockRecordOutScan(PdProductStock pdProductStock,HttpServletRequest request, HttpServletResponse response, Model model){
		String findFlag = request.getParameter("findFlag");
		//findFlag=1时只根据编号查询
		if("1".equals(findFlag)){
			String number = pdProductStock.getProductNo();
			pdProductStock = new PdProductStock();
			pdProductStock.setProductNo(number);
		}
		pdProductStock.setStoreroomId(UserUtils.getUser().getStoreroomId());
		List<PdProductStock> list = pdProductStockService.getByProductBarCodeNew(pdProductStock);
		return list;
	}

	
	/**
	 * 耗材出库根据产品信息查询产品
	 */
	@RequestMapping(value = "getProductInfoByOther")
	@ResponseBody
	public Object getProductInfoByOther(PdProductStock pdProductStock){
		pdProductStock.setStoreroomId(UserUtils.getUser().getStoreroomId());
		List<PdProductStock> list = pdProductStockService.getProductInfoByOther(pdProductStock); 
		//封装好的产品容器
		List<PdProductStock> productStocks = new ArrayList<PdProductStock>();
		if(list != null && list.size() > 0){
			//按照产品编号分组
			Map<String,List<PdProductStock>> productMap = new HashMap<String,List<PdProductStock>>();
			for(PdProductStock ps : list){
				String key = ps.getProductNo()+"_"+ps.getProductBarCode();
				if(productMap.containsKey(key)){
					List<PdProductStock> pds = productMap.get(key);
					pds.add(ps);
					productMap.put(key, pds);
				}else{
					List<PdProductStock> pds = new ArrayList<PdProductStock>();
					pds.add(ps);
					productMap.put(key, pds);
				}
			}
			for(String str :productMap.keySet()){
				List<PdProductStock> pds = productMap.get(str);
				Set<String> batchNoList = Sets.newHashSet();
				JSONObject stockMap = new JSONObject();//批号对应的库存
				JSONObject validDateMap = new JSONObject();//批号对应的有效期
				for(PdProductStock ps : pds){
					batchNoList.add(ps.getBatchNo());
					stockMap.put(ps.getBatchNo(), ps.getStockNum());
					validDateMap.put(ps.getBatchNo(), DateUtils.formatDate(ps.getValidDate(), "yyyy-MM-dd"));
				}
				PdProductStock temp = pds.get(0);
				temp.setStockMap(JSONObject.toJSONString(stockMap));
				temp.setValidDateMap(JSONObject.toJSONString(validDateMap));
				temp.setBatchNoList(batchNoList);
				productStocks.add(temp);
			}
			return productStocks;
		}
		return null;
	}
	
	/**
	 * 导出
	 * @param
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PdProductStock pdProductStock, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "库存数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PdProductStock> page = pdProductStockService.findListForQuery(new Page<PdProductStock>(request, response, -1), pdProductStock);
            new ExportExcel("库存数据", PdProductStock.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出库存失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pd/pdProductStock/queryPdStockDetail";
    }
	@RequiresPermissions("pd:pdProductStock:view")
	@RequestMapping(value = {"queryPdStockDetail"})
	public String queryPdStockDetail(PdProductStock pdProductStock, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String storeroomId = pdProductStock.getStoreroomId();
		String productId = pdProductStock.getProductId();
				
		Page<PdProductStock> page = pdProductStockService.findListForQuery(new Page<PdProductStock>(request, response), pdProductStock); 
		model.addAttribute("page", page);
		PdProductStock productStock = new PdProductStock();
		productStock.setStoreroomId(storeroomId);
		productStock.setProductId(productId);
		Map<String, Object> map = pdProductStockService.findPdCount(productStock);
		for (String key : map.keySet()) { 
			  Object value = map.get(key); 
			  model.addAttribute(key, value);			  
		}
		model.addAttribute("pdProductStock", pdProductStock);		
		return "hys/pd/pdProductStockDetail";
	}

	@RequestMapping(value="autoPrompt")
	@ResponseBody
	public Object autoPrompt(){
		String message="";
		Object gCount="0";
		Object jCount="0";
		User user = UserUtils.getUser();
		PdProductStock productStock = new PdProductStock();
		if(!user.isAdmin()){
			String storeroomId = user.getStoreroomId();				
			productStock.setStoreroomId(storeroomId);
		}		
		Map<String, Object> map = pdProductStockService.findPdCount(productStock);
		for (String key : map.keySet()) { 
			  if(key.equals("gCount")){
				  gCount = map.get(key).toString(); 				  
			  }else if(key.equals("jCount")){
				  jCount = map.get(key).toString(); 
			  }			  	  
		}
		if(!"0".equals(jCount)&&!"0".equals(gCount)){
			message="库存中有近效期和过期产品!";
		}else if(!"0".equals(jCount)&&"0".equals(gCount)){
			message="库存中有近效期产品!";
		}else if("0".equals(jCount)&&!"0".equals(gCount)){
			message="库存中有过期产品!";
		}		
		return message;
	}

	
	
	/*
	 * 物流追溯产品列表
	 */
	@RequestMapping(value = "queryOriginalProduct")
	@ResponseBody
	public Object queryOriginalProduct(PdProductStock pdProductStock) {
		//全流程追溯-从一级库房开始
		String firstStoreroomId = pdStoreroomService.findFirstStoreroom();
		pdProductStock.setStoreroomId(firstStoreroomId);
		return pdProductStockService.getByOriginalProduct(pdProductStock);
	}
	/*
	 * 库房巡查产品列表
	 */
	@RequestMapping(value = "queryPatrolProduct")
	@ResponseBody
	public Map<String, Object> queryPatrolProduct(PdProductStock pdProductStock, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", pdProductStockService.findListForQuery(pdProductStock));
		map.put("pageList", pdProductStockService.findListForQuery(new Page<PdProductStock>(request, response), pdProductStock));
		return map;
	}
	@RequestMapping(value = "addPatrolForm")
	public String addStoreroomPatrol(PdProductStock pdProductStock, Model model){
		model.addAttribute("PdProductStock", pdProductStock);
		model.addAttribute("patrolDate", DateUtils.getNowDate());
		return "hys/pd/pdStoreroomPatrolForm";
	}
	/**
	 * 库存明细
	 * @param pdProductStock
	 * @return
	 */
	@RequestMapping(value="pdStockDetail")
	@ResponseBody
	public List<PdProductStock> pdStockDetail(PdProductStock pdProductStock){
		User user = UserUtils.getUser();
		pdProductStock.setStoreroomId(user.getStoreroomId());
		List<PdProductStock> findList = pdProductStockService.findList(pdProductStock);
		return findList;
	}
	
	/**
	 * 进销存报表
	 * @param pdRpInventory
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="pdEntersSellsSavesDetail")
	public String entersSellsSavesDetail(PdRpInventory pdRpInventory, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdRpInventory.getStoreroomId())){
			pdRpInventory.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//如果时间默认数据为空
		Page<PdRpInventory> page = null;
		if(pdRpInventory.getStartDate()==null || pdRpInventory.getEndDate()==null){
			page= new Page<PdRpInventory>(request, response);
			page.setList(new ArrayList<PdRpInventory>());
			//导出excel需要的数据
			model.addAttribute("exportDataList", JsonMapper.toJsonString(new ArrayList<PdRpInventory>()));
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			Date date = calendar.getTime();
			Long dateTime = date.getTime()-24*60*60*1000;
			if(pdRpInventory.getEndDate().getTime()>dateTime){
				Date date1= new Date(dateTime);
                pdRpInventory.setQueryEndDate(date1);
			}else{
                pdRpInventory.setQueryEndDate(pdRpInventory.getEndDate());
            }
            pdRpInventory.setQueryStartDate(pdRpInventory.getStartDate());
			//导出excel需要的数据
			model.addAttribute("exportDataList", JsonMapper.toJsonString(pdRpInventoryService.findEntersSellsQuery(pdRpInventory)));
			page = pdRpInventoryService.findEntersSellsQuery(new Page<PdRpInventory>(request, response), pdRpInventory);
		}	 
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "hys/pd/query/entersSellsSavesDetail";
	}

	/**
	 * 查询出入库明细 add by jiangxz 20190918 用于库存管理查询出入库明细
	 * @param pdStockRecordProduct
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="stockInAndOutRecordDetailQuery")
	public String stockInAndOutRecordDetailQuery(PdStockRecordProduct pdStockRecordProduct, Model model, HttpServletRequest request, HttpServletResponse response){
		User user = UserUtils.getUser();
		Page<PdStockRecordProduct> page = pdStockRecordProductService.stockInAndOutRecordDetailQueryForPage(new Page<PdStockRecordProduct>(request, response),pdStockRecordProduct);

		int productTotNum = 0;
		int productOutTotNum = 0;
		BigDecimal sumPdTotalPrice = new BigDecimal(0);
		BigDecimal sumPdOutTotalPrice = new BigDecimal(0);

		List<PdStockRecordProduct> list = pdStockRecordProductService.stockInAndOutRecordDetailQuery(pdStockRecordProduct);
		if(list != null && list.size() > 0){
			for (PdStockRecordProduct item : list) {
				if(MinaGlobalConstants.STOCK_RECORD_TYPE_IN_NAME.equals(item.getRecodeType())){
					productTotNum += item.getProductNum();
					if(item.getPdTotalPrice() != null){
						sumPdTotalPrice = sumPdTotalPrice.add(item.getPdTotalPrice());
					}
				}else if(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT_NAME.equals(item.getRecodeType())){
					productOutTotNum += item.getProductNum();
					if(item.getPdOutTotalPrice() != null){
						sumPdOutTotalPrice = sumPdOutTotalPrice.add(item.getPdOutTotalPrice());
					}
				}
			}
		}

		model.addAttribute("page", page);
		model.addAttribute("productTotNum", productTotNum);
		model.addAttribute("productOutTotNum", productOutTotNum);
		model.addAttribute("sumPdTotalPrice", sumPdTotalPrice);
		model.addAttribute("sumPdOutTotalPrice", sumPdOutTotalPrice);
		model.addAttribute("pdStockRecordProduct", pdStockRecordProduct);
		return "hys/pd/pdStockInAndOutRecordDetail";
	}
}