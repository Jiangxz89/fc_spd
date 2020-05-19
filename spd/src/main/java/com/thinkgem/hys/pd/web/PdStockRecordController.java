/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.response.CommonRspVo;
import com.thinkgem.hys.pd.service.*;
import com.thinkgem.hys.utils.CommonUtils;
import com.thinkgem.hys.utils.SpdUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import jersey.repackaged.com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 出入库记录Controller
 * @author changjundong
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdStockRecord")
public class PdStockRecordController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(PdStockRecordController.class);
	@Autowired
	private PdStockRecordService pdStockRecordService;
	@Autowired
	private PdStockRecordInvoiceService pdStockRecordInvoiceService;
	@Autowired
	private PdStockRecordProductService pdStockRecordProductService;
	@Autowired
	private PdStoreroomService pdStoreroomService;
	@Autowired
	private PdProductService pdProductService;	
	@Autowired
	private PdApplyOrderService pdApplyOrderService;
	@Autowired
	private PdAllocationRecordService pdAllocationRecordService;

	@ModelAttribute
	public PdStockRecord get(@RequestParam(required=false) String id) {
		PdStockRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pdStockRecordService.get(id);
		}
		if (entity == null){
			entity = new PdStockRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		return "hys/pd/pdStockRecordList";
	}
	
	//入库列表
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"inList", ""})
	public String inList(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {

		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		User user = UserUtils.getUser();
		pdStockRecord.setInId(user.getStoreroomId());  //所属库房
		
		//供应商查询条件 处理
		//供应商查询条件 处理
		String outId = pdStockRecord.getOutId();
		if(StringUtils.isNotBlank(outId) && outId.startsWith("|")){
			pdStockRecord.setSupplierId(outId.substring(1));
			pdStockRecord.setOutId("");
		}
		
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		
		pdStockRecord.setOutId(outId);
		pdStockRecord.setSupplierId("");
		
		model.addAttribute("page", page);
		return "hys/pd/pdStockRecordInList";
	}
	
	//出库列表
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"outList", ""})
	public String outList(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
		
		User user = UserUtils.getUser();
		pdStockRecord.setOutId(user.getStoreroomId());  //所属库房
		
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		model.addAttribute("storeroomList", storeroomList);
		return "hys/pd/pdStockRecordOutList";
	}
	 
	//出库列表参照
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"outListBox"})
	public String outListBox(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
		
		User user = UserUtils.getUser();
		pdStockRecord.setInId(user.getStoreroomId());  //入库库房为所在库房
		
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		return "hys/pd/box/pdStockRecordOutBox";
	}
	
	//入库审核列表
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"inExamineList", ""})
	public String inExamineList(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		
		User user = UserUtils.getUser();
		pdStockRecord.setInId(user.getStoreroomId());  //所属库房
		
		//供应商查询条件 处理
		String outId = pdStockRecord.getOutId();
		if(StringUtils.isNotBlank(outId) && outId.startsWith("|")){
			pdStockRecord.setSupplierId(outId.substring(1));
			pdStockRecord.setOutId("");
		}
		
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		
		pdStockRecord.setOutId(outId);
		pdStockRecord.setSupplierId("");
		
		model.addAttribute("page", page);
		return "hys/pd/pdStockRecordInListExamine";
	}
	
	//出库审核列表
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = {"outExamineList"})
	public String outExamineList(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
		
		User user = UserUtils.getUser();
		pdStockRecord.setOutId(user.getStoreroomId());  //所属库房
		
		Page<PdStockRecord> page = pdStockRecordService.findPage(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		String isOut = request.getParameter("isOut");
		if(StringUtils.isNotEmpty(isOut)){
			model.addAttribute("isOut", isOut);
		}
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		model.addAttribute("storeroomList", storeroomList);
		return "hys/pd/pdStockRecordOutListExamine";
	}
	
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = "form")
	public String form(PdStockRecord pdStockRecord, Model model) {
		model.addAttribute("pdStockRecord", pdStockRecord);
		return "hys/pd/pdStockRecordForm";
	}

	//入库表单(formType 1:查看；2：审核)
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = "formIn")
	public String formIn(PdStockRecord pdStockRecord,String formType, Model model, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotBlank(formType) && "1".equals(formType)){
			if(StringUtils.isBlank(pdStockRecord.getRecordNo())){
				String recordNo = CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_RK);
				//pdStockRecord.setRecordNo(OrderNoUtils.getStockInNo());
				pdStockRecord.setRecordNo(recordNo);
			}
			if(StringUtils.isNotBlank(pdStockRecord.getId())){
				//获取入库记录关联产品
				PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
				pdStockRecordProduct.setRecordId(pdStockRecord.getId());
				List<PdStockRecordProduct> productList = pdStockRecordProductService.findPdList(pdStockRecordProduct);
				int productTotNum = 0;
				BigDecimal sumPdTotalPrice = new BigDecimal(0);
				for(PdStockRecordProduct p:productList){
					productTotNum = p.getProductNum() + productTotNum;
					if(p.getPdTotalPrice() != null){
						sumPdTotalPrice = sumPdTotalPrice.add(p.getPdTotalPrice());
					}
				}
				pdStockRecord.setProductTotNum(productTotNum);
				pdStockRecord.setSumPdTotalPrice(sumPdTotalPrice);
				pdStockRecord.setProductList(productList);
				//获取入库记录关联发票
				if(StringUtils.isNotBlank(pdStockRecord.getOrderNo())){
					PdStockRecordInvoice pdStockRecordInvoice = new PdStockRecordInvoice();
					pdStockRecordInvoice.setRecordId(pdStockRecord.getId());
					List<PdStockRecordInvoice> invoiceList = pdStockRecordInvoiceService.findList(pdStockRecordInvoice);
					pdStockRecord.setInvoiceList(invoiceList);
				}
			}else{
				User user = UserUtils.getUser();
				Date nowDate  = DateUtils.getNowDate();
				pdStockRecord.setRecordDate(nowDate);
				pdStockRecord.setRecordPeople(user.getName());
				pdStockRecord.setInName(user.getStoreroomName());
				pdStockRecord.setInId(user.getStoreroomId());
			}
			model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			model.addAttribute("pdStockRecord", pdStockRecord);
			return "hys/pd/pdStockRecordInForm";
			
		}else if(StringUtils.isNotBlank(formType) && "2".equals(formType)){
			if(StringUtils.isNotBlank(pdStockRecord.getId())){
				//获取入库记录关联产品
				PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
				pdStockRecordProduct.setRecordId(pdStockRecord.getId());
				List<PdStockRecordProduct> productList = pdStockRecordProductService.findPdList(pdStockRecordProduct);
				int productTotNum = 0;
				BigDecimal sumPdTotalPrice = new BigDecimal(0);
				for(PdStockRecordProduct p:productList){
					productTotNum = p.getProductNum() + productTotNum;
					if(p.getPdTotalPrice() != null){
						sumPdTotalPrice = sumPdTotalPrice.add(p.getPdTotalPrice());
					}
				}
				pdStockRecord.setProductTotNum(productTotNum);
				pdStockRecord.setSumPdTotalPrice(sumPdTotalPrice);
				
				pdStockRecord.setProductList(productList);
				//获取入库记录关联发票
				if(StringUtils.isNotBlank(pdStockRecord.getOrderNo())){
					PdStockRecordInvoice pdStockRecordInvoice = new PdStockRecordInvoice();
					pdStockRecordInvoice.setRecordId(pdStockRecord.getId());
					List<PdStockRecordInvoice> invoiceList = pdStockRecordInvoiceService.findList(pdStockRecordInvoice);
					pdStockRecord.setInvoiceList(invoiceList);
				}
			}
			model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			model.addAttribute("pdStockRecord", pdStockRecord);
			return "hys/pd/pdStockRecordInFormExamine";
		}
		addMessage(redirectAttributes, "传入参数不完整");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/inList?repagpdStockRecorde";
	}
	
	//入库表单(formType 1:查看)
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = "viewIn")
	public String viewIn(PdStockRecord pdStockRecord,String formType, Model model, RedirectAttributes redirectAttributes) {
		
		formIn(pdStockRecord, formType, model, redirectAttributes);
		return "hys/pd/pdStockRecordInView";
	}
	
	//新增出库单页面
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value = "formOut")
	public String formOut(PdStockRecord pdStockRecord, Model model) {
		User user = UserUtils.getUser();
		if(StringUtils.isNotBlank(pdStockRecord.getId())){
			//获取出库记录关联产品
			PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
			pdStockRecordProduct.setRecordId(pdStockRecord.getId());
			List<PdStockRecordProduct> productList = pdStockRecordProductService.findList(pdStockRecordProduct);
			pdStockRecord.setProductList(productList);
		}else{			
			Date nowDate = DateUtils.getNowDate();
			pdStockRecord.setRecordNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_CK));
			pdStockRecord.setRecordDate(nowDate);
			pdStockRecord.setRecordPeople(user.getName());
			pdStockRecord.setOutName(user.getStoreroomName());
			pdStockRecord.setOutId(user.getStoreroomId());
		}
		List<PdStoreroom> storeroomList = pdStoreroomService.findList(new PdStoreroom());
		model.addAttribute("storeroomList", storeroomList);
		model.addAttribute("user", user);
		model.addAttribute("pdStockRecord", pdStockRecord);
		return "hys/pd/pdStockRecordOutForm";
	}

	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "save")
	public String save(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStockRecord)){
			return form(pdStockRecord, model);
		}
		pdStockRecordService.save(pdStockRecord);
		addMessage(redirectAttributes, "保存出入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/?repage";
	}
	
	//入库保存
	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "saveIn")
	public String saveIn(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStockRecord)){
			return form(pdStockRecord, model);
		}
		
		User user = UserUtils.getUser();
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_0);
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		String inId = user.getStoreroomId();
		String inName = SpdUtils.getStoreroomName(inId);
		pdStockRecord.setInId(inId);     //入库库房
		pdStockRecord.setInName(inName);  //入库库房名称
		String storeroomType = user.getStoreroomType();
		
		pdStockRecordService.saveRecordIn(pdStockRecord, storeroomType);
		
		addMessage(redirectAttributes, "保存入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/inList?repage";
	}

	//出库保存
	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "saveOut")
	public String saveOut(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pdStockRecord)){
			return form(pdStockRecord, model);
		}
		if(StringUtils.isNotEmpty(pdStockRecord.getInStoreId())){
			pdStockRecord.setInId(pdStockRecord.getInStoreId());
		}		
		List<PdStockRecordProduct> list = pdStockRecord.getProductList();
		//校验数据的合法性
		Iterator<PdStockRecordProduct> it = list.iterator();
		while(it.hasNext()){
			PdStockRecordProduct child = it.next();
			if(child.getProductNum()==null || child.getProductId()==null){
				it.remove();
			}
		}
		List<PdStockRecordProduct> pdList = dealRepeatData(list);
		pdStockRecord.setProductList(pdList);
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_0);
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
		pdStockRecordService.saveRecord(pdStockRecord);		
		addMessage(redirectAttributes, "保存出库记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/outList?repage";
	}	
	
	//合并相同的用量
	private List<PdStockRecordProduct> dealRepeatData(final List<PdStockRecordProduct> list){
		List<PdStockRecordProduct> tempArray = new ArrayList<PdStockRecordProduct>();
		Set<String> pids = new HashSet<String>();
		if(list != null && list.size() > 0){
			for(PdStockRecordProduct temp : list){
				if(StringUtils.isNotEmpty(temp.getNumber())){
					StringBuilder sb = new StringBuilder();
					sb.append(temp.getProductId()).append(temp.getNumber()).append(temp.getProductBarCode()).append(temp.getBatchNo());
					if(pids.contains(sb.toString())){
						continue;
					}
					int dosageTotal = 0;
					for(PdStockRecordProduct tp : list){
						if(StringUtils.isNotEmpty(tp.getNumber())){
							if(temp.getBatchNo().equals(tp.getBatchNo()) 
									&& temp.getNumber().equals(tp.getNumber()) 
									&& temp.getProductBarCode().equals(tp.getProductBarCode())
									&& temp.getProductId().equals(tp.getProductId())){
								pids.add(sb.toString());
								dosageTotal += tp.getProductNum();
							}
						}						
					}
					temp.setProductNum(dosageTotal);
					tempArray.add(temp);
				}
				
			}
		}
		return tempArray;
	}
	
	//入库审核通过
	@ResponseBody
	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "examineIn")
	public Object examineIn(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		String id = pdStockRecord.getId();
		CommonRspVo crv = new CommonRspVo();
		
		if(pdStockRecord == null || StringUtils.isBlank(pdStockRecord.getId())){
			crv.setCode("201");
			crv.setMessage("入库审核，传入参数不存在！");
			return crv;
		}
		String recordState = pdStockRecord.getRecordState();
		if(!MinaGlobalConstants.STOCK_RECORD_STATE_0.equals(recordState)){
			crv.setCode("202");
			crv.setMessage("入库审核，入库单非‘待审核’状态，不允许审核！");
			return crv;
		}
		User user = UserUtils.getUser();
		String storeroomType = user.getStoreroomType();
		
		crv = pdStockRecordService.examineIn(pdStockRecord, storeroomType);
		//crv.setCode("200");
		
		return crv;
	}

	//出库审核
	@ResponseBody
	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "examineOut")
	public Object examineOut(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		CommonRspVo vo = new CommonRspVo();
		if(pdStockRecord != null && StringUtils.isNotBlank(pdStockRecord.getId())&& StringUtils.isNotBlank(pdStockRecord.getRecordState())){
			User user = UserUtils.getUser();
			PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
			pdStockRecordProduct.setRecordId(pdStockRecord.getId());
			PdStockRecord record = pdStockRecordService.get(pdStockRecord.getId());
			pdStockRecordProduct.setStoreroomId(user.getStoreroomId());
			List<PdStockRecordProduct> productList = pdStockRecordProductService.findList(pdStockRecordProduct);
			record.setProductList(productList);
			record.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);
			record.setCheckPeople(user.getName());
			try {
				logger.info("--------------------出库审核开始--------------------");
				vo=pdStockRecordService.examineOut(record);
				PdStockRecord pdSr = pdStockRecordService.get(record.getId());
				vo.setData(pdSr);
				logger.info("--------------------出库审核结束--------------------");					
			} catch (Exception e) {
				vo.setCode("500");
				vo.setMessage(e.getMessage());
				logger.info("--------------------出库审核失败信息--------------------");	
				logger.error(e.getMessage());
				e.printStackTrace();
			}			
		}else{
			vo.setCode("500");
			vo.setMessage("参数错误");
		}
		return vo;
	}	

	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PdStockRecord pdStockRecord, RedirectAttributes redirectAttributes) {
		pdStockRecordService.delete(pdStockRecord);
		addMessage(redirectAttributes, "删除出入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/?repage";
	}

	//整单驳回rejectAll
	@RequiresPermissions("pd:pdStockRecord:edit")
	@RequestMapping(value = "rejectAll")
	public String rejectAll(PdStockRecord pdStockRecord, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		if(pdStockRecord != null && StringUtils.isNotBlank(pdStockRecord.getId()) && StringUtils.isNotBlank(pdStockRecord.getRejectReason())){
			PdStockRecord record = pdStockRecordService.get(pdStockRecord.getId());
			String recordState = record.getRecordState();
			//非待审核状态
			if(!MinaGlobalConstants.STOCK_RECORD_STATE_0.equals(recordState)){
				addMessage(redirectAttributes, "非待审核状态，不允许驳回");
			}else{
				record.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_2);
				record.setRejectReason(pdStockRecord.getRejectReason());
				record.setCheckPeople(user.getName());
				pdStockRecordService.rejectAll(record);			
				addMessage(redirectAttributes, "整单驳回成功");
			}
		}else{
			addMessage(redirectAttributes, "整单驳回失败");
		}
		String recodeType = pdStockRecord.getRecodeType();
		if(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT.equals(recodeType)){
			return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/outExamineList?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/pd/pdStockRecord/inExamineList?repage";
		}
		
	}
	/**
	 * 出库确认，查看
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pdStockRecordDetail")
	public String pdStockRecordDetail(HttpServletRequest request,PdStockRecord pdStockRecord,Model model){
		User user = UserUtils.getUser();
		pdStockRecord = pdStockRecordService.get(pdStockRecord.getId());
		PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
		pdStockRecordProduct.setRecordId(pdStockRecord.getId());
		if(StringUtils.isNotEmpty(user.getStoreroomId())){
			pdStockRecordProduct.setStoreroomId(user.getStoreroomId());
		}else{
			pdStockRecordProduct.setStoreroomId(pdStockRecord.getOutId());
		}
		List<PdStockRecordProduct> productList = pdStockRecordProductService.findList(pdStockRecordProduct);
		int productTotNum = 0;
		BigDecimal sumPdTotalPrice = new BigDecimal(0);
		BigDecimal sumPdOutTotalPrice = new BigDecimal(0);

		for(PdStockRecordProduct p:productList){
			productTotNum = p.getProductNum() + productTotNum;
			if(p.getPdOutTotalPrice() != null){
				sumPdTotalPrice = sumPdTotalPrice.add(p.getPdTotalPrice());
				sumPdOutTotalPrice = sumPdOutTotalPrice.add(p.getPdOutTotalPrice());
			}
		}
		pdStockRecord.setSumPdTotalPrice(sumPdTotalPrice);
		pdStockRecord.setSumPdOutTotalPrice(sumPdOutTotalPrice);
		pdStockRecord.setProductTotNum(productTotNum);		
		pdStockRecord.setProductList(productList);
		model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("pdStockRecord", pdStockRecord);
		String isOut=request.getParameter("isOut");
		if(StringUtils.isNotEmpty(isOut)){
			model.addAttribute("isOut", isOut);
		}
		return "hys/pd/pdStockRecordOutFormDetail";
	}
	
	
	/**
	 * 入库明细查询
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="pdStockRecordInDtlQuery")
	public String stockRecordInDtlQuery(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
		User user = UserUtils.getUser();		
		if(!StringUtils.isNotEmpty(pdStockRecord.getStoreroomId())){
			pdStockRecord.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStockRecordService.findCensusQuery(pdStockRecord)));
		Page<PdStockRecord> page = pdStockRecordService.findCensusQuery(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);	
		model.addAttribute("user", user);
		return "hys/pd/query/stockRecordInDtlQuery";
	}
	
	/**
	 * 出库明细查询
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="pdStockRecordOutDtlQuery")
	public String stockRecordOutDtlQuery(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdStockRecord.getStoreroomId())){
			pdStockRecord.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStockRecordService.findOutQuery(pdStockRecord)));
		Page<PdStockRecord> page = pdStockRecordService.findOutQuery(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		model.addAttribute("user", user);
		return "hys/pd/query/stockRecordOutDtlQuery";
		
	}
	
	/**
	 * 调入明细查询
	 * */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="stockRecordCallInQuery")
	public String stockRecordCallInQuery(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_IN);
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
		pdStockRecord.setInType(MinaGlobalConstants.STOCK_IN_TYPE_2);//只查调拨入库的明细
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdStockRecord.getStoreroomId())){
			pdStockRecord.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStockRecordService.findCallInQuery(pdStockRecord)));
		Page<PdStockRecord> page = pdStockRecordService.findCallInQuery(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "hys/pd/query/stockRecordcalledInQuery";
	}
	
	/**
	 * 院内退货明细
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pd:pdStockRecord:view")
	@RequestMapping(value="pdStockRecordCourtWithinOutDtlQuery")
	public String stockRecordCourtWithinOutDtlQuery(PdStockRecord pdStockRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		pdStockRecord.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);//出库
		pdStockRecord.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);//只查已通过的明细
		pdStockRecord.setOutType(MinaGlobalConstants.STOCK_IN_TYPE_1);//只查退货入库
		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdStockRecord.getStoreroomId())){
			pdStockRecord.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStockRecordService.findOutQuery(pdStockRecord)));
		Page<PdStockRecord> page = pdStockRecordService.findOutQuery(new Page<PdStockRecord>(request, response), pdStockRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		model.addAttribute("user", user);
		return "hys/pd/query/stockRecordCourtWithinOutDtlQuery";
		
	}

	/**
	 * 药品入库明细查询
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	/**@RequiresPermissions("pd:medstoyprkmxQuery:view")
	@RequestMapping(value="medstoyprkmxQuery")
	public String medstoyprkmxQuery(MedstoYprkmx medstoYprkmx, HttpServletRequest request, HttpServletResponse response, Model model){

		User user = UserUtils.getUser();
		if(!StringUtils.isNotEmpty(pdStockRecord.getStoreroomId())){
			pdStockRecord.setStoreroomId(user.getStoreroomId());  //所属库房
		}
		//导出excel需要的数据
		model.addAttribute("exportDataList", JsonMapper.toJsonString(pdStockRecordService.findOutQuery(pdStockRecord)));
		Page<PdStockRecord> page = pdStockRecordService.findOutQuery(new Page<PdStockRecord>(request, response), pdStockRecord);
		model.addAttribute("page", page);
		model.addAttribute("pdStockRecord", pdStockRecord);
		model.addAttribute("user", user);
		return "hys/pd/query/medsto/medstoyprkmxQuery";

	}*/

	@ResponseBody
	@RequestMapping(value="getPrintData")
	public Object getPrintData(PdStockRecord pdStockRecord){
		pdStockRecord = pdStockRecordService.get(pdStockRecord.getId());
		PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
		pdStockRecordProduct.setRecordId(pdStockRecord.getId());
		List<PdStockRecordProduct> productList = pdStockRecordProductService.findList(pdStockRecordProduct);
		//pdStockRecord.setProductList(productList);
		return productList;
	}
	
	/**
	 * 入库扫码
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="stockRecordInScan")
	public String stockRecordInScan(String number, String barCode, HttpServletRequest request, HttpServletResponse response, Model model){
		JSONObject json = new JSONObject();
		
		if(StringUtils.isEmpty(number)){
			json.put("code", "202");
			json.put("msg", "产品编码不能为空！");
			return json.toString();
		}
		PdProduct pdProduct = new PdProduct();
		pdProduct.setNumber(number);
		//pdProduct.setBarCode(barCode);
		List<PdProduct> pdProducts = pdProductService.basicFindList(pdProduct);
		
		if(pdProducts != null && pdProducts.size() > 0){
			PdProduct product = pdProducts.get(0); 
			json.put("productId", product.getId());
			json.put("productName", product.getName());
			json.put("productCode", product.getNumber());
			json.put("barCode", barCode);
			json.put("productUnit", product.getUnit());
			json.put("productSpec", product.getSpec());
			json.put("productVersion", product.getVersion());
			json.put("productPrice", product.getPruPrice());
			json.put("registration", product.getRegistration());
			json.put("venderName", product.getVenderName());
			json.put("isUrgent", product.getIsUrgent());   //是否紧急产品
			json.put("surplusPurCount", product.getSurplusPurCount());   //紧急产品剩余采购数量
			json.put("supplierId", product.getSupplierId());
			json.put("code", "200");
		}else{
			json.put("code", "201");
			json.put("msg", "无产品信息");
		}
		
		return json.toString();
	}
	
	/**
	 * 入库扫码时根据产品名称型号规格查询产品
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getStockRecordInScanByOther")
	public String getStockRecordInScanByOther(PdProduct pdProduct,HttpServletRequest request, HttpServletResponse response, Model model){
		List<PdProduct> pdProducts = pdProductService.basicFindList(pdProduct);
		JSONObject json = new JSONObject();
		if(pdProducts != null && pdProducts.size() > 0){
			List<Map<String,Object>> list = Lists.newArrayList();
			for(PdProduct p:pdProducts){
				Map<String,Object> map =Maps.newHashMap();
				map.put("productId", p.getId());
				map.put("productName", p.getName());
				map.put("productCode", p.getNumber());
				//map.put("barCode", pdProduct.getBarCode());扫码时无需输入产品条码，自动使用产品编码当做条码2019年7月2日16:46:08
				map.put("barCode", p.getNumber());
				map.put("productUnit", p.getUnit());
				map.put("productSpec", p.getSpec());
				map.put("productVersion", p.getVersion());
				map.put("productPrice", p.getPruPrice());
				map.put("registration", p.getRegistration());
				map.put("venderName", p.getVenderName());
				map.put("isUrgent", p.getIsUrgent());   //是否紧急产品
				map.put("surplusPurCount", p.getSurplusPurCount());   //紧急产品剩余采购数量
				map.put("supplierId", p.getSupplierId());
				list.add(map);
			}
			json.put("code", "200");
			json.put("data", JSONArray.toJSONString(list));
		}else{
			json.put("code", "201");
			json.put("msg", "无产品信息");
		}
		System.out.println(json.toString());
		return json.toString();
	}
	
	/**
	 * 申领单，调拨单审核出库
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pdPassAddStockRecord")	
	public String pdPassAddStockRecord(PdStockRecord pdStockRecord,Model model,HttpServletRequest request){
		User user = UserUtils.getUser();		
		String applyNo = pdStockRecord.getApplyNo();
		String allocationNo=pdStockRecord.getAllocationNo();
		String flag=request.getParameter("flag");
		if(StringUtils.isNotEmpty(flag)){
			model.addAttribute("flag",flag);
		}
		if(StringUtils.isNotEmpty(applyNo)){
			PdApplyOrder pdApplyOrder = pdApplyOrderService.get(applyNo);
			pdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_0);	
			pdStockRecord.setInId(pdApplyOrder.getDeptId());
			pdStockRecord.setOutId(user.getStoreroomId());
			pdStockRecord.setInName(pdApplyOrder.getDeptName());
		}else if(StringUtils.isNotEmpty(allocationNo)){
			pdStockRecord.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_2);	
			PdAllocationRecord allocationRecord = pdAllocationRecordService.get(allocationNo);
			pdStockRecord.setInId(allocationRecord.getInId());
			pdStockRecord.setOutId(allocationRecord.getOutId());
			pdStockRecord.setInName(allocationRecord.getInName());
		}		
		Date nowDate = DateUtils.getNowDate();
		pdStockRecord.setRecordNo(CommonUtils.generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_CK));
		pdStockRecord.setRecordDate(nowDate);
		pdStockRecord.setRecordPeople(user.getName());		
		model.addAttribute("pdStockRecord", pdStockRecord);		
		return "hys/pd/pdPassAddStockRecord";
	}	
	
	/**
	 * 申领单、调拨单、退货单查询出库信息
	 * @param pdStockRecord
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getStockRecordOutProds")	
	public Object getStockRecordOutProds(PdStockRecord pdStockRecord, HttpServletRequest request, Model model){
		
		String stockImpType = request.getParameter("stockImpType");
		String inFromNo = request.getParameter("inFromNo");
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(stockImpType) || StringUtils.isEmpty(inFromNo)){
			map.put("code", "201");
			map.put("message", "传入参数有误！");
			return map;
		}
		
		User user = UserUtils.getUser();
		List<PdStockRecordProduct> stockRecordProducts = new ArrayList<PdStockRecordProduct>();
		PdStockRecord quSr = new PdStockRecord();
		//申领单导入
		if(MinaGlobalConstants.STOCK_IMP_TYPE_1.equals(stockImpType)){
			quSr.setApplyNo(inFromNo);
			quSr.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_0);
		}
		//退货单导入
		else if(MinaGlobalConstants.STOCK_IMP_TYPE_3.equals(stockImpType)){
			quSr.setRecordNo(inFromNo);
			quSr.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_1);
		}
		//调拨单导入
		else if(MinaGlobalConstants.STOCK_IMP_TYPE_2.equals(stockImpType)){
			quSr.setAllocationNo(inFromNo);
			quSr.setOutType(MinaGlobalConstants.STOCK_OUT_TYPE_2);
		}else{
			map.put("code", "202");
			map.put("message", "传入导入类型有误！");
			return map;
		}
		
		quSr.setRecodeType(MinaGlobalConstants.STOCK_RECORD_TYPE_OUT);  //出库单
		quSr.setRecordState(MinaGlobalConstants.STOCK_RECORD_STATE_1);  //出库单已通过审核 
		List<PdStockRecord> pdStockRecords = pdStockRecordService.findList(quSr);
		if(pdStockRecords != null && pdStockRecords.size() > 0){
			pdStockRecord = pdStockRecords.get(0);
			PdStockRecordProduct pdStockRecordProduct = new PdStockRecordProduct();
			pdStockRecordProduct.setRecordId(pdStockRecord.getId());
			stockRecordProducts = pdStockRecordProductService.findPdList(pdStockRecordProduct);
		}else{
			map.put("code", "203");
			map.put("message", "申领单："+inFromNo +"未出库！");
		}

		String outName = SpdUtils.getStoreroomName(pdStockRecord.getOutId());
		pdStockRecord.setOutName(outName);
		
		map.put("code", "200");
		map.put("outStockRecord", pdStockRecord);
		map.put("productList", stockRecordProducts);
		
		return map;
	}
}