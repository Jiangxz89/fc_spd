/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import java.util.Date;
import java.util.HashMap;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.PdAllocationProduct;
import com.thinkgem.hys.pd.entity.PdAllocationRecord;
import com.thinkgem.hys.pd.entity.PdPackage;
import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdStoreroom;
import com.thinkgem.hys.pd.entity.PdStoreroomAdmin;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.pd.service.PdAllocationProductService;
import com.thinkgem.hys.pd.service.PdAllocationRecordService;
import com.thinkgem.hys.pd.service.PdPackageService;
import com.thinkgem.hys.pd.service.PdProductService;
import com.thinkgem.hys.pd.service.PdStoreroomAdminService;
import com.thinkgem.hys.pd.service.PdStoreroomService;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.PlatDateUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 调拨记录Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdAllocationRecord")
public class PdAllocationRecordController extends BaseController {

	@Autowired
	private PdAllocationRecordService pdAllocationRecordService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdStoreroomAdminService pdStoreroomAdminService;
	@Autowired
	private PdAllocationProductService pdAllocationProductService;
	@Autowired
	private PdPackageService pdPackageService;
	@Autowired
	private PdProductService pdProductService;
	
	@ModelAttribute
	public PdAllocationRecord get(@RequestParam(required=false) String id) {
		PdAllocationRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdAllocationRecordService.get(id);
		}
		if (entity == null){
			entity = new PdAllocationRecord();
		}
		return entity;
	}
	
	/**
	 * 调拨管理列表
	 * @param pdAllocationRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdAllocationRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询库房
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		//设置调拨单入库id
		User user = UserUtils.getUser();
		pdAllocationRecord.setInId(user.getStoreroomId());
		Page<PdAllocationRecord> page = pdAllocationRecordService.findPage(new Page<PdAllocationRecord>(request, response), pdAllocationRecord); 
		//根据库房查询操作人员
		PdStoreroomAdmin pdStoreroomAdmin = new PdStoreroomAdmin();
		pdStoreroomAdmin.setStoreroomId(user.getStoreroomId());
		List<PdStoreroomAdmin> storeroomAdminList = pdStoreroomAdminService.findAdminList(pdStoreroomAdmin);
		
		model.addAttribute("page", page);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		model.addAttribute("storeroomList", storeroomList);
		model.addAttribute("storeroomAdminList", storeroomAdminList);
		return "hys/pd/pdAllocationRecordList";
	}
	
	/**
	 * 申请调拨单详情
	 * @param pdAllocationRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toDetail")
	public String toDetail(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdProduct> prodList = Lists.newArrayList();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					pdPro.setSelfStoreroomId(UserUtils.getUser().getStoreroomId());
					PdProduct product = pdProductService.getProduct(pdPro);
					product.setApplyNum(allocationProduct.getAllocationNum());
					prodList.add(product);
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),UserUtils.getUser().getStoreroomId());
					pdPack.setPdProductList(productList);
					pdPack.setApplyNum(allocationProduct.getAllocationNum());
					packageList.add(pdPack);
				}
			}
		}
		model.addAttribute("packageList", packageList);
		model.addAttribute("prodList", prodList);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		return "hys/pd/pdAllocationRecordDetail";
	}
	
	/**
	 * 调拨确认
	 * @param pdAllocationRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toConfirm")
	public String toConfirm(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdProduct> prodList = Lists.newArrayList();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					pdPro.setSelfStoreroomId(pdAllocationRecord.getInId());
					PdProduct product = pdProductService.getProduct(pdPro);
					product.setApplyNum(allocationProduct.getAllocationNum());
					prodList.add(product);
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),pdAllocationRecord.getInId());
					pdPack.setPdProductList(productList);
					pdPack.setApplyNum(allocationProduct.getAllocationNum());
					packageList.add(pdPack);
				}
			}
		}
		model.addAttribute("packageList", packageList);
		model.addAttribute("prodList", prodList);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		return "hys/pd/pdAllocationRecordConfirm";
	}
	
	//调拨审核列表
	@RequiresPermissions("pd:pdAllocationRecord:view")
	@RequestMapping("examineList")
	public String examineList(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询库房
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		//设置调拨单入库id
		User user = UserUtils.getUser();
		pdAllocationRecord.setOutId(user.getStoreroomId());
		Page<PdAllocationRecord> page = pdAllocationRecordService.findPage(new Page<PdAllocationRecord>(request, response), pdAllocationRecord); 
		//根据库房查询操作人员 
		PdAllocationRecord pdRecord = new PdAllocationRecord();
		pdRecord.setOutId(user.getStoreroomId());
		List<PdAllocationRecord> recordList = pdAllocationRecordService.findList(pdRecord);
		Set<String> storeroomAdminList = Sets.newHashSet();
		for (PdAllocationRecord record : recordList) {
			storeroomAdminList.add(record.getRecordPeople());
		}
		model.addAttribute("page", page);
		model.addAttribute("storeroomList", storeroomList);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		model.addAttribute("storeroomAdminList", storeroomAdminList);
		return "hys/pd/pdAllocationRecordListExamine";
	}

	@RequiresPermissions("pd:pdAllocationRecord:view")
	@RequestMapping(value = "form")
	public String form(PdAllocationRecord pdAllocationRecord, Model model) {
		User user = UserUtils.getUser();
		//查询库房
		PdStoreroom storeroom = new PdStoreroom();
		if(MinaGlobalConstants.STOREROOM_TYPE_0.equals(user.getStoreroomType())){
			storeroom.setStoreroomType(MinaGlobalConstants.STOREROOM_TYPE_1);
		}
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(storeroom);
		//生成调拨单编号
		//String allocationCode = PlatDateUtils.dateToStr(PlatDateUtils.getSysTimestamp(), "yyyyMMddHHmmss")+SnoGerUtil.getSerialNumber(3);
		String allocationCode = CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_DB);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		model.addAttribute("allocationCode", allocationCode);
		model.addAttribute("user", user);
		model.addAttribute("allocationDate", PlatDateUtils.dateToStr(new Date(), "yyyy-MM-dd"));
		model.addAttribute("storeroomList", storeroomList);
		return "hys/pd/pdAllocationRecordForm";
	}
	
	/**
	 * 修改被拒绝的调拨单
	 * @param pdAllocationRecord
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "allocationRecordModify")
	public String allocationRecordModify(PdAllocationRecord pdAllocationRecord, Model model) {
		User user = UserUtils.getUser();
		int rowsRecord = 0;
		//查询库房
		PdStoreroom storeroom = new PdStoreroom();
		if(MinaGlobalConstants.STOREROOM_TYPE_0.equals(user.getStoreroomType())){
			storeroom.setStoreroomType(MinaGlobalConstants.STOREROOM_TYPE_1);
		}
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(storeroom);
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdProduct> prodList = Lists.newArrayList();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					pdPro.setSelfStoreroomId(pdAllocationRecord.getInId());
					PdProduct product = pdProductService.getProduct(pdPro);
					product.setApplyNum(allocationProduct.getAllocationNum());
					prodList.add(product);
					rowsRecord += 1;
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),pdAllocationRecord.getInId());
					pdPack.setPdProductList(productList);
					pdPack.setApplyNum(allocationProduct.getAllocationNum());
					packageList.add(pdPack);
					rowsRecord += productList.size();
				}
			}
		}
		model.addAttribute("packageList", packageList);
		model.addAttribute("prodList", prodList);
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		model.addAttribute("user", user);
		model.addAttribute("allocationDate", PlatDateUtils.dateToStr(new Date(), "yyyy-MM-dd"));
		model.addAttribute("storeroomList", storeroomList);
		model.addAttribute("rowsRecord", rowsRecord);
		return "hys/pd/pdAllocationRecordModify";
	}

	/**
	 * 改变调拨单状态
	 * @param pdAllocationRecord
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateAllocation")
	@ResponseBody
	public CommonRspVo updateAllocation(PdAllocationRecord pdAllocationRecord, Model model, RedirectAttributes redirectAttributes) {
		CommonRspVo rsp = new CommonRspVo();
		//验证库存数量
		if(MinaGlobalConstants.APPLY_ORDER_STATUS_PASSED.equals(pdAllocationRecord.getRecordState())){
			PdProduct prod = checkStoreNum(pdAllocationRecord);
			if(null!=prod){
				rsp.setCode("-200");
				rsp.setMessage("库存已不足，无法通过！");
				rsp.setData(prod);
				return rsp;
			}
		}
		try {
			pdAllocationRecord.setCheckPeople(UserUtils.getUser().getName());
			pdAllocationRecord.setCheckDate(new Date());
			pdAllocationRecordService.save(pdAllocationRecord);
			if(MinaGlobalConstants.APPLY_ORDER_STATUS_REFUSED.equals(pdAllocationRecord.getRecordState())){
				rsp.setCode("0");
			}else{
				rsp.setCode("200");
			}
			rsp.setMessage("保存成功");
		} catch (Exception e) {
			rsp.setCode("500");
			rsp.setMessage("保存失败");
		}
		return rsp;
	}
	
	//验证库存数量
	private PdProduct checkStoreNum(PdAllocationRecord pdAllocationRecord) {
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		Map<String,PdProduct> numMap = Maps.newHashMap();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					pdPro.setSelfStoreroomId(pdAllocationRecord.getInId());
					PdProduct product = pdProductService.getProduct(pdPro);
					if(numMap.containsKey(product.getId())){
						PdProduct tempProd = numMap.get(product.getId());
						String applyNum = tempProd.getApplyNum();
						int totalApplyNum = Integer.valueOf(applyNum).intValue()+Integer.valueOf(allocationProduct.getAllocationNum()).intValue();
						tempProd.setApplyNum(String.valueOf(totalApplyNum));
						numMap.put(product.getId(), tempProd);
					}else{
						PdProduct newPro = new PdProduct();
						newPro.setStockNum(product.getStockNum());
						newPro.setApplyNum(allocationProduct.getAllocationNum());
						newPro.setName(product.getName());
						newPro.setId(product.getId());
						numMap.put(product.getId(), newPro);
					}
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),pdAllocationRecord.getInId());
					for (PdProduct product : productList) {
						if(numMap.containsKey(product.getId())){
							PdProduct tempProd = numMap.get(product.getId());
							String applyNum = tempProd.getApplyNum();
							int tempAllo = Integer.valueOf(allocationProduct.getAllocationNum()).intValue();
							int totalAllo = tempAllo * product.getProductNumber();
							int totalApplyNum = Integer.valueOf(applyNum).intValue()+totalAllo;
							tempProd.setApplyNum(String.valueOf(totalApplyNum));
							numMap.put(product.getId(), tempProd);
						}else{
							PdProduct newPro = new PdProduct();
							newPro.setStockNum(product.getStockNum());
							int tempAllo = Integer.valueOf(allocationProduct.getAllocationNum()).intValue();
							int totalAllo = tempAllo * product.getProductNumber();
							newPro.setApplyNum(String.valueOf(totalAllo));
							newPro.setName(product.getName());
							newPro.setId(product.getId());
							numMap.put(product.getId(), newPro);
						}
					}
				}
			}
		}
		if(null!=numMap){
			for(String prodId:numMap.keySet()){
				PdProduct pdProduct = numMap.get(prodId);
				String stockNum = pdProduct.getStockNum();
				String applyNum = pdProduct.getApplyNum();
				if(Integer.valueOf(stockNum).intValue() < Integer.valueOf(applyNum).intValue()){
					return pdProduct;
				}
			}
		}
		return null;
	}

	@RequiresPermissions("pd:pdAllocationRecord:edit")
	@RequestMapping(value = "save")
	public String save(String oldId,PdAllocationRecord pdAllocationRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdAllocationRecord)){
			return form(pdAllocationRecord, model);
		}
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationRecord.getPdAllocationProductList();
		pdAllocationRecord.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_SENDED);
		pdAllocationRecord.setAllocationDate(new Date());
		//1.保存调拨单
		//删除调拨单下的产品和定数包
		PdAllocationProduct allocationProduct = new PdAllocationProduct();
		allocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> alloProductList = pdAllocationProductService.findList(allocationProduct);
		if(null!=alloProductList && alloProductList.size()>0){
			for (PdAllocationProduct prod : alloProductList) {
				pdAllocationProductService.delete(prod);
			}
		}
		if(StringUtils.isNotBlank(oldId)){
			PdAllocationRecord oldAllocation = pdAllocationRecordService.get(oldId);
			oldAllocation.setRecordState(MinaGlobalConstants.APPLY_ORDER_STATUS_SENDED);
			oldAllocation.setAllocationNum(pdAllocationRecord.getAllocationNum());
			oldAllocation.setAllocationDate(pdAllocationRecord.getAllocationDate());
			oldAllocation.setRemarks(pdAllocationRecord.getRemarks());
			oldAllocation.setCheckPeople(null);
			oldAllocation.setCheckDate(null);
			oldAllocation.setRejectReason(null);
			pdAllocationRecordService.save(oldAllocation);
		}else{
			pdAllocationRecordService.save(pdAllocationRecord);
		}
		//2.保存调拨单与产品中间表
		for (PdAllocationProduct pdAllocationProduct : pdAllocationProductList) {
			if(StringUtils.isNotBlank(pdAllocationProduct.getAllocationCode())){
				pdAllocationProductService.save(pdAllocationProduct);
			}
		}
		addMessage(redirectAttributes, "保存调拨记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdAllocationRecord/?repage";
	}
	
	@RequiresPermissions("pd:pdAllocationRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PdAllocationRecord pdAllocationRecord, RedirectAttributes redirectAttributes) {
		pdAllocationRecordService.delete(pdAllocationRecord);
		addMessage(redirectAttributes, "删除调拨记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdAllocationRecord/?repage";
	}
	/**
	 * 新增出库 调拨单导入
	 * @param pdAllocationRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pdAddAllocationOutBox")
	public String pdAddAllocationOutBox(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		pdAllocationRecord.setOutId(user.getStoreroomId());				
		if(StringUtils.isNotEmpty(pdAllocationRecord.getRecordState())||StringUtils.isNotEmpty(pdAllocationRecord.getRecordStates())){
			Page<PdAllocationRecord> page = pdAllocationRecordService.findPage(new Page<PdAllocationRecord>(request, response), pdAllocationRecord); 
			model.addAttribute("page", page);			
		}
		model.addAttribute("pdAllocationRecord", pdAllocationRecord);
		return "hys/pd/pdAddAllocationOutBox";
	}
	
	/**
	 * 新增入库 调拨单导入
	 * @param pdAllocationRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="addAllocationInBox")
	public String addAllocationInBox(PdAllocationRecord pdAllocationRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		pdAllocationRecord.setInId(user.getStoreroomId());
		if(StringUtils.isNotEmpty(pdAllocationRecord.getRecordState()) ||
				StringUtils.isNotEmpty(pdAllocationRecord.getRecordStates())){
			Page<PdAllocationRecord> page = pdAllocationRecordService.findPage(new Page<PdAllocationRecord>(request, response), pdAllocationRecord); 
			model.addAttribute("page", page);
		}
		
		return "hys/pd/box/pdAddAllocationInBox";
	}
	
	@RequestMapping(value="queryProducts")
	@ResponseBody
	public Object queryProducts(PdAllocationRecord pdAllocationRecord){
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdProduct> prodList = Lists.newArrayList();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					PdProduct product = pdProductService.getProduct(pdPro);
					product.setApplyNum(allocationProduct.getAllocationNum());
					prodList.add(product);
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),null);
					pdPack.setPdProductList(productList);
					pdPack.setApplyNum(allocationProduct.getAllocationNum());
					packageList.add(pdPack);
				}
			}
		}
		if(prodList!=null&&!prodList.isEmpty()){
			return prodList;
		}
		if(packageList!=null&&!packageList.isEmpty()){
			return packageList;
		}
		return null;
	}
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(PdAllocationRecord pdAllocationRecord){
		//查询定数包和产品列表 
		PdAllocationProduct pdAllocationProduct = new PdAllocationProduct();
		pdAllocationProduct.setAllocationCode(pdAllocationRecord.getAllocationCode());
		List<PdAllocationProduct> pdAllocationProductList = pdAllocationProductService.findList(pdAllocationProduct);
		List<PdPackage> packageList = Lists.newArrayList();
		List<PdProduct> prodList = Lists.newArrayList();
		Map<String,Object> map = new HashMap<String,Object>();
		//设置定数包下面的产品集合
		if(null!=pdAllocationProductList && pdAllocationProductList.size()>0){
			for (PdAllocationProduct allocationProduct : pdAllocationProductList) {
				String productAttr = allocationProduct.getProductAttr();
				if("1".equals(productAttr)){
					PdProduct pdPro = new PdProduct();
					pdPro.setId(allocationProduct.getProductCode());
					pdPro.setStoreroomId(pdAllocationRecord.getOutId());
					PdProduct product = pdProductService.getProduct(pdPro);
					product.setApplyNum(allocationProduct.getAllocationNum());
					prodList.add(product);
				}else{
					PdPackage pdPack = pdPackageService.get(allocationProduct.getProductCode());
					List<PdProduct> productList = pdPackageService.getProductByPackageId(pdPack.getId(),pdAllocationRecord.getOutId(),null);
					pdPack.setPdProductList(productList);
					pdPack.setApplyNum(allocationProduct.getAllocationNum());
					packageList.add(pdPack);
				}
			}
		}
		map.put("productList", prodList);
		map.put("packageList", packageList);
		return map;
	}
	

}