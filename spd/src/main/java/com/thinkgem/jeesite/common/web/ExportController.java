package com.thinkgem.jeesite.common.web;


import com.alibaba.fastjson.JSONArray;
import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.pd.entity.excel.*;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Excel导出controller
 * @author Mr.Wang
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/excelExport/")
public class ExportController extends BaseController {
	
	/**
	 * 不良品登记管理----Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "rejectsProductRecord", method=RequestMethod.POST)
	public void rejectsProductRecord(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "不良品记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdRejectsRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdRejectsRecord.class);
			new ExportExcel("不良品记录", NotGoodProductExcel.class).setDataList(DataUtils.getNotGoodData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 巡查管理详情----Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "patrolRecord", method=RequestMethod.POST)
	public void patrolRecord(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "巡查记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdStoreroomPatrolRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdStoreroomPatrolRecord.class);
			new ExportExcel("巡查记录", PatrolRecordExcel.class).setDataList(DataUtils.getPatrolRecordData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 入库明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "stockRecordInDtlQueryExport", method=RequestMethod.POST)
	public void stockRecordInDtlQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "入库明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdStockRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdStockRecord.class);
			new ExportExcel("入库明细", PdIntoStockRecordExcel.class).setDataList(DataUtils.getRecordInDtlQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 出库明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "stockRecordOutDtlQueryExport", method=RequestMethod.POST)
	public void stockRecordOutDtlQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "出库明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdStockRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdStockRecord.class);
			new ExportExcel("出库明细", PdOutStockRecordExcel.class).setDataList(DataUtils.getRecordOutDtlQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 药品库存明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "medstoYpkcmxList", method=RequestMethod.POST)
	public void medstoYpkcmxListExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品库存明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpkcmx> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpkcmx.class);
			new ExportExcel("药品库存明细", MedstoYpkcmxListExcel.class).setDataList(DataUtils.getmedstoYpkcmxListData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 调入明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "stockRecordcalledInQueryExport", method=RequestMethod.POST)
	public void stockRecordcalledInQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "调入明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdStockRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdStockRecord.class);
			new ExportExcel("调入明细", PdCalledInStockRecordExcel.class).setDataList(DataUtils.getRecordCalledInDtlQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用量明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "dosageQueryExport", method=RequestMethod.POST)
	public void dosageQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "用量明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdDosage> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdDosage.class);
			new ExportExcel("用量明细", PdDosageExcel.class).setDataList(DataUtils.getPdDosageQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 院内退货明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "courtWithinOutDtlQueryExport", method=RequestMethod.POST)
	public void courtWithinOutDtlQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "院内退货明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdStockRecord> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdStockRecord.class);
			new ExportExcel("院内退货明细", CourtWithinOutDtlExcel.class).setDataList(DataUtils.getCourtWithinOutDtlQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 库存明细导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "stockTimeQueryExport", method=RequestMethod.POST)
	public void stockTimeQueryExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "库存明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdProductStockTime> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdProductStockTime.class);
			new ExportExcel("库存明细", StockTimeExcel.class).setDataList(DataUtils.getStockTimeQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户退回导出---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "detailListExport", method=RequestMethod.POST)
	public void detailListExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "用户退回"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdDosagert> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdDosagert.class);
			new ExportExcel("用户退回", PdDosagertExcel.class).setDataList(DataUtils.getDosagertQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 院外退货明细---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "hospitaloutsideReturnDetailExport", method=RequestMethod.POST)
	public void hospitaloutsideReturnDetailExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "院外退货明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdRejectedListHead> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdRejectedListHead.class);
			new ExportExcel("院外退货明细", PdRejectedListHeadExcel.class).setDataList(DataUtils.getHospitaloutsideReturnQueryData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进销存报表---Excel导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "pdEntersSellsSavesDetailExport", method=RequestMethod.POST)
	public void pdEntersSellsSavesDetailExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "进销存报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdRpInventory> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdRpInventory.class);
			new ExportExcel("进销存报表", PdRpInventoryExcel.class).setDataList(DataUtils.getEntersSellsSavesDetailData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品出库明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugOutDetaileExport", method=RequestMethod.POST)
	public void drugOutDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品出库明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpckzd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpckzd.class);
			new ExportExcel("药品出库明细", MedstoYpckzdExcel.class).setDataList(DataUtils.getDrugOutDetaileData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品调价单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugDrugPriceAdjustmentExport", method=RequestMethod.POST)
	public void drugDrugPriceAdjustmentExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品调价单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpdjd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpdjd.class);
			new ExportExcel("药品调价单明细", MedstoYpdjdExcel.class).setDataList(DataUtils.getdrugDrugPriceAdjustmentData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品盘点单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugInventoryDetailedExport", method=RequestMethod.POST)
	public void drugInventoryDetailedExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品盘点单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYppdd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYppdd.class);
			new ExportExcel("药品盘点单明细", MedstoYppddExcel.class).setDataList(DataUtils.getdrugInventoryDetailedData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 药品丢失单明细导出
     * @param data
     * @param request
     * @param response
     */
    @RequestMapping(value = "drugLostOrderDetaileExport", method=RequestMethod.POST)
    public void drugLostOrderDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
        String fileName = "药品丢失单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            List<MedstoYpbsd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpbsd.class);
            new ExportExcel("药品丢失单明细", MedstoYpbsdExcel.class).setDataList(DataUtils.getDrugLostOrderDetaileData(list)).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 药品退药单明细导出
     * @param data
     * @param request
     * @param response
     */
    @RequestMapping(value = "drugReturnBillDetaileExport", method=RequestMethod.POST)
    public void drugReturnBillDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
        String fileName = "药品退药单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            List<MedstoYptyd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYptyd.class);
            new ExportExcel("药品退药单明细", MedstoYptydExcel.class).setDataList(DataUtils.getDrugReturnBillDetaileData(list)).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 药品发药单明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "dispensingListDetailExport", method=RequestMethod.POST)
	public void dispensingListDetailExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品发药单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoYpfyd> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoYpfyd.class);
			new ExportExcel("药品发药单明细", MedstoYpfydExcel.class).setDataList(DataUtils.getdispensingListDetailData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 药品入库明细导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "drugInDetaileExport", method=RequestMethod.POST)
	public void drugInDetaileExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "药品入库明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<MedstoRkzjl> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), MedstoRkzjl.class);
			new ExportExcel("药品入库明细", MedstoRkzjlExcel.class).setDataList(DataUtils.getDrugInDetaileData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产品列表导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "productListExport", method=RequestMethod.POST)
	public void productListExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "产品列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdProduct> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdProduct.class);
			new ExportExcel("产品列表",  ProductExcel.class).setDataList(DataUtils.getProductListData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 采购明细列表导出
	 * @param data
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "pdPurchaseDetailExport", method=RequestMethod.POST)
	public void pdPurchaseDetailExport(@RequestParam(value="exportData")String data, HttpServletRequest request, HttpServletResponse response){
		String fileName = "采购明细列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try {
			List<PdPurchaseDetail> list = JSONArray.parseArray(data.replaceAll("&quot;", "\""), PdPurchaseDetail.class);
			new ExportExcel("采购明细列表",  PurchaseDetailExcel.class).setDataList(DataUtils.getPdPurchaseDetailListData(list)).write(response, fileName).dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}