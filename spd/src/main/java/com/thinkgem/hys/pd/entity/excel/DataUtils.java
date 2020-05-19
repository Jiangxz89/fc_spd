package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.hys.pd.entity.*;
import com.thinkgem.hys.utils.SpdUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

public class DataUtils {

	/**
	 * 不良品登记---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<NotGoodProductExcel> getNotGoodData(List<PdRejectsRecord> list){
		List<NotGoodProductExcel> dataList = Lists.newArrayList();
		for(PdRejectsRecord pr : list){
			NotGoodProductExcel np = new NotGoodProductExcel();
			np.setProductBatchNo(pr.getBatchNo());
			if(null!=pr.getCreateDate()){
				np.setRecordTime(DateUtils.formatDate(pr.getCreateDate(), "yyyy-MM-dd"));
			}else{
				np.setRecordTime("");
			}
			np.setProductName(pr.getPdProduct().getName());
			np.setProductNo(pr.getPdProduct().getNumber());
			np.setProductSpec(pr.getPdProduct().getSpec());
			np.setProductVersion(pr.getPdProduct().getVersion());
			np.setInHospitalNo(pr.getInHospitalNo());
			np.setSeriousLevel(pr.getSeriousGrade());
			dataList.add(np);
		}
		return dataList;
	}
	/**
	 * 巡查记录---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PatrolRecordExcel> getPatrolRecordData(List<PdStoreroomPatrolRecord> list){
		List<PatrolRecordExcel> dataList = Lists.newArrayList();
		for(PdStoreroomPatrolRecord pr : list){
			PatrolRecordExcel np = new PatrolRecordExcel();
			np.setStoreroomName(pr.getStoreroomName());
			np.setProdName(pr.getPdProduct().getName());
			np.setProdNo(pr.getPdProduct().getNumber());
			np.setProdBarCode(pr.getProductBarCode());
			np.setProdSpec(pr.getPdProduct().getSpec());
			np.setProdVersion(pr.getPdProduct().getVersion());
			np.setProdUnit(pr.getPdProduct().getUnit());
			np.setBatchNo(pr.getBatchNo());
			if(null!=pr.getValidDate()){
				np.setValidDate(DateUtils.formatDate(pr.getValidDate(), "yyyy-MM-dd"));
			}else{
				np.setValidDate("");
			}
			np.setStockNum(pr.getStockNum()==null?"":pr.getStockNum().toString());
			np.setVenderName(pr.getPdProduct().getVenderName());
			np.setRegistration(pr.getPdProduct().getRegistration());
			np.setIsExpire(pr.getIsExpire());
			np.setResult(pr.getPatrolResult());
			np.setRemarks(pr.getRemarks());
			dataList.add(np);
		}
		return dataList;
	}
	
	/**
	 * 入库明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdIntoStockRecordExcel> getRecordInDtlQueryData(List<PdStockRecord> list) {
		List<PdIntoStockRecordExcel> dataList = Lists.newArrayList();
		for(PdStockRecord pdStockRecord :list){
			PdIntoStockRecordExcel pre = new PdIntoStockRecordExcel();
			pre.setRecordDate(DateUtils.formatDate(pdStockRecord.getRecordDate(), "yyyy-MM-dd"));
			pre.setInId(SpdUtils.getStoreroomName(pdStockRecord.getInId()));
			pre.setPdName(pdStockRecord.getPdName());
			pre.setNumber(pdStockRecord.getNumber());
			pre.setProductBarCode(pdStockRecord.getProductBarCode());
			pre.setSpec(pdStockRecord.getSpec());
			pre.setVersion(pdStockRecord.getVersion());
			pre.setBatchNo(pdStockRecord.getBatchNo());
			pre.setLimitDate(DateUtils.formatDate(pdStockRecord.getLimitDate(), "yyyy-MM-dd"));
			pre.setProductNum(pdStockRecord.getProductNum());
			pre.setUnit(pdStockRecord.getUnit());
			pre.setSellingPrice(pdStockRecord.getSellingPrice()==null?"":String.valueOf(pdStockRecord.getSellingPrice()));
			pre.setPdTotalPrice(pdStockRecord.getPdTotalPrice()==null?"":String.valueOf(pdStockRecord.getPdTotalPrice()));
			pre.setVenderName(pdStockRecord.getVenderName());
			pre.setRecordNo(pdStockRecord.getRecordNo());
			pre.setRemarks(pdStockRecord.getRemarks());
			pre.setInType(DictUtils.getDictLabel(pdStockRecord.getInType(),"pd_inType",""));
			pre.setRecordPeople(pdStockRecord.getRecordPeople());
			dataList.add(pre);
		}
		return dataList;
	}
	
	/**
	 * 出库明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdOutStockRecordExcel> getRecordOutDtlQueryData(List<PdStockRecord> list) {
		List<PdOutStockRecordExcel> dataList = Lists.newArrayList();
		for(PdStockRecord pdStockRecord :list){
			PdOutStockRecordExcel psr = new PdOutStockRecordExcel();
			psr.setRecordNo(pdStockRecord.getRecordNo());
			psr.setRecordDate(DateUtils.formatDate(pdStockRecord.getRecordDate(), "yyyy-MM-dd"));
			psr.setOutId(SpdUtils.getStoreroomName(pdStockRecord.getOutId()));
			psr.setInId(SpdUtils.getStoreroomName(pdStockRecord.getInId()));
			psr.setPdName(pdStockRecord.getPdName());
			psr.setNumber(pdStockRecord.getNumber());
			psr.setProductBarCode(pdStockRecord.getProductBarCode());
			psr.setSpec(pdStockRecord.getSpec());
			psr.setVersion(pdStockRecord.getVersion());
			psr.setBatchNo(pdStockRecord.getBatchNo());
			psr.setLimitDate(DateUtils.formatDate(pdStockRecord.getLimitDate(), "yyyy-MM-dd"));
			psr.setProductNum(pdStockRecord.getProductNum()==null?"":String.valueOf(pdStockRecord.getProductNum()));
			psr.setRecordPeople(pdStockRecord.getRecordPeople());
			psr.setRemarks(pdStockRecord.getRemarks());
			dataList.add(psr);
		}
		return dataList;
	}
	/**
	 * 药品库存明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<MedstoYpkcmxListExcel> getmedstoYpkcmxListData(List<MedstoYpkcmx> list) {
		List<MedstoYpkcmxListExcel> dataList = Lists.newArrayList();
		for(MedstoYpkcmx medstoYpkcmx :list){
			MedstoYpkcmxListExcel psr = new MedstoYpkcmxListExcel();
			psr.setJgbm(medstoYpkcmx.getJgbm());
			psr.setStoreId(medstoYpkcmx.getStoreId());
			psr.setStoreName(medstoYpkcmx.getStoreName());
			psr.setDrugId(medstoYpkcmx.getDrugId());
			psr.setYbtybm(medstoYpkcmx.getYbtybm());
            psr.setIsjbyw(medstoYpkcmx.getIsjbyw()=="1"?"是":"否");
            psr.setKss(medstoYpkcmx.getKss()=="1"?"是":"否");
            psr.setCmedCode(medstoYpkcmx.getCmedCode());
            psr.setDrugDetailType(medstoYpkcmx.getDrugDetailType());
            psr.setDoseCode(medstoYpkcmx.getDoseCode());
			psr.setDrugName(medstoYpkcmx.getDrugName());
			psr.setDrugSpec(medstoYpkcmx.getDrugSpec());
			psr.setPackageUnit(medstoYpkcmx.getPackageUnit());
			psr.setPackageQty(medstoYpkcmx.getPackageQty());
			psr.setSalesUnit(medstoYpkcmx.getSalesUnit());
			psr.setSalesQty(medstoYpkcmx.getSalesQty());
			psr.setSalesRelation(medstoYpkcmx.getSalesRelation());
			psr.setProducerName(medstoYpkcmx.getProducerName());
			psr.setCostPrice(medstoYpkcmx.getCostPrice());
			psr.setSalePrice(medstoYpkcmx.getSalePrice());
			psr.setCostAmt(medstoYpkcmx.getCostAmt());
			psr.setSaleAmt(medstoYpkcmx.getSaleAmt());
			psr.setBatchNo(medstoYpkcmx.getBatchNo());
			psr.setSdrugmanufacturers(medstoYpkcmx.getSdrugmanufacturers());
			psr.setSdrughabitat(medstoYpkcmx.getSdrughabitat());
			psr.setSratificationno(medstoYpkcmx.getSratificationno());
			psr.setDdateproduce(DateUtils.formatDate(medstoYpkcmx.getDdateproduce(), "yyyy-MM-dd"));
			psr.setExpiry(DateUtils.formatDate(medstoYpkcmx.getExpiry(),"yyyy-MM-dd"));
			psr.setScrq(DateUtils.formatDate(medstoYpkcmx.getScrq(),"yyyy-MM-dd"));
			dataList.add(psr);
		}
		return dataList;
	}
	/**
	 * 调入明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdCalledInStockRecordExcel> getRecordCalledInDtlQueryData(List<PdStockRecord> list) {
		List<PdCalledInStockRecordExcel> dataList = Lists.newArrayList();
		for(PdStockRecord pdStockRecord :list){
			PdCalledInStockRecordExcel pcsr = new PdCalledInStockRecordExcel();
			pcsr.setOutReCordNo(pdStockRecord.getOutReCordNo());
			pcsr.setRecordDate(DateUtils.formatDate(pdStockRecord.getRecordDate(), "yyyy-MM-dd"));
			pcsr.setOutId(SpdUtils.getStoreroomName(pdStockRecord.getOutId()));
			pcsr.setInId(SpdUtils.getStoreroomName(pdStockRecord.getInId()));
			pcsr.setPdName(pdStockRecord.getPdName());
			pcsr.setNumber(pdStockRecord.getNumber());
			pcsr.setProductBarCode(pdStockRecord.getProductBarCode());
			pcsr.setSpec(pdStockRecord.getSpec());
			pcsr.setVersion(pdStockRecord.getVersion());
			pcsr.setBatchNo(pdStockRecord.getBatchNo());
			pcsr.setLimitDate(DateUtils.formatDate(pdStockRecord.getLimitDate(), "yyyy-MM-dd"));
			pcsr.setProductNum(pdStockRecord.getProductNum()==null?"":String.valueOf(pdStockRecord.getProductNum()));
			pcsr.setRecordPeople(pdStockRecord.getRecordPeople());
			pcsr.setRemarks(pdStockRecord.getRemarks());
			dataList.add(pcsr);
		}
		return dataList;
	}
	
	/**
	 * 用量明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdDosageExcel> getPdDosageQueryData(List<PdDosage> list) {
		List<PdDosageExcel> dataList = Lists.newArrayList();
		for(PdDosage pdDosage :list){
			PdDosageExcel pde = new PdDosageExcel();
			pde.setDosageNo(pdDosage.getDosageNo());
			pde.setWarehouseName(pdDosage.getWarehouseName());
			pde.setDosageDate(DateUtils.formatDate(pdDosage.getDosageDate(), "yyyy-MM-dd"));
			pde.setPdName(pdDosage.getPdName());
			pde.setPower(pdDosage.getPower()=="0"?"公共产品":"自有产品");
			pde.setProductBarCode(pdDosage.getProductBarCode());
			pde.setSpec(pdDosage.getSpec());
			pde.setVersion(pdDosage.getVersion());
			pde.setBatchNo(pdDosage.getBatchNo());
			pde.setLimitDate(pdDosage.getLimitDate()==null?"":DateUtils.formatDate(pdDosage.getLimitDate(), "yyyy-MM-dd"));
			pde.setProductNum(pdDosage.getProductNum()==null?"":String.valueOf(pdDosage.getProductNum()));
			pde.setUnit(pdDosage.getUnit());
			pde.setSellingPrice(pdDosage.getSellingPrice()==null?"":String.valueOf(pdDosage.getSellingPrice()));
			pde.setAmountMoney(pdDosage.getAmountMoney()==null?"":String.valueOf(pdDosage.getAmountMoney()));
			pde.setVenderName(pdDosage.getVenderName());
			pde.setSalesman(pdDosage.getSalesman());
			pde.setDosageOperator(pdDosage.getDosageOperator());
			pde.setExeDeptName(pdDosage.getExeDeptName());
			pde.setOprDeptName(pdDosage.getOprDeptName());
			pde.setInHospitalNo(pdDosage.getInHospitalNo());
			pde.setPatientInfo(pdDosage.getPatientInfo());
			pde.setSurgeon(pdDosage.getSurgeon());
			pde.setRemarks(pdDosage.getRemarks());
			dataList.add(pde);
		}
		return dataList;
	}
	
	/**
	 * 院内出货明细---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<CourtWithinOutDtlExcel> getCourtWithinOutDtlQueryData(List<PdStockRecord> list) {
		List<CourtWithinOutDtlExcel> dataList = Lists.newArrayList();
		for(PdStockRecord pdStockRecord :list){
			CourtWithinOutDtlExcel cwod = new CourtWithinOutDtlExcel();
			cwod.setRecordNo(pdStockRecord.getRecordNo());
			cwod.setRecordDate(DateUtils.formatDate(pdStockRecord.getRecordDate(), "yyyy-MM-dd"));
			cwod.setOutId(SpdUtils.getStoreroomName(pdStockRecord.getOutId()));
			cwod.setInId(SpdUtils.getStoreroomName(pdStockRecord.getInId()));
			cwod.setPdName(pdStockRecord.getPdName());
			cwod.setNumber(pdStockRecord.getNumber());
			cwod.setProductBarCode(pdStockRecord.getProductBarCode());
			cwod.setSpec(pdStockRecord.getSpec());
			cwod.setVersion(pdStockRecord.getVersion());
			cwod.setBatchNo(pdStockRecord.getBatchNo());
			cwod.setLimitDate(DateUtils.formatDate(pdStockRecord.getLimitDate(), "yyyy-MM-dd"));
			cwod.setProductNum(pdStockRecord.getProductNum()==null?"":String.valueOf(pdStockRecord.getProductNum()));
			cwod.setRecordPeople(pdStockRecord.getRecordPeople());
			cwod.setRemarks(pdStockRecord.getRemarks());
			dataList.add(cwod);
		}
		return dataList;
	}
	
	/**
	 * 库存查询---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<StockTimeExcel> getStockTimeQueryData(List<PdProductStockTime> list) {
		List<StockTimeExcel> dataList = Lists.newArrayList();
		for(PdProductStockTime pdProductStockTime :list){
			StockTimeExcel ste = new StockTimeExcel();
			ste.setStoreroomId(SpdUtils.getStoreroomName(pdProductStockTime.getStoreroomId()));
			ste.setProductName(pdProductStockTime.getProductName());
			ste.setProdNo(pdProductStockTime.getProdNo());
			ste.setBarCode(pdProductStockTime.getBarCode());
			ste.setProductSpec(pdProductStockTime.getProductSpec());
			ste.setProductVersion(pdProductStockTime.getProductVersion());
			ste.setBatchNo(pdProductStockTime.getBatchNo());
			ste.setValidDate(pdProductStockTime.getValidDate()==null?"":DateUtils.formatDate(pdProductStockTime.getValidDate(), "yyyy-MM-dd"));
			ste.setStockNum(pdProductStockTime.getStockNum());
			ste.setVenderName(pdProductStockTime.getVenderName());
			ste.setSupplierName(pdProductStockTime.getSupplierName());
			dataList.add(ste);
		}
		return dataList;
	}
	
	/**
	 * 用户退回---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdDosagertExcel> getDosagertQueryData(List<PdDosagert> list) {
		List<PdDosagertExcel> dataList = Lists.newArrayList();
		for(PdDosagert pdDosagert :list){
			PdDosagertExcel pde = new PdDosagertExcel();
			pde.setDosagertNo(pdDosagert.getDosagertNo());
			pde.setDosagertDate(pdDosagert.getDosagertDate()==null?"":DateUtils.formatDate(pdDosagert.getDosagertDate(), "yyyy-MM-dd"));
			pde.setDosagertRoomId(SpdUtils.getStoreroomName(pdDosagert.getDosagertRoomId()));
			pde.setDosagertInroomId(SpdUtils.getStoreroomName(pdDosagert.getDosagertInroomId()));
			pde.setProdName(pdDosagert.getProdName());
			pde.setProdNo(pdDosagert.getProdNo());
			pde.setProdBarcode(pdDosagert.getProdBarcode());
			pde.setProdSpec(pdDosagert.getProdSpec());
			pde.setVersion(pdDosagert.getVersion());
			pde.setBatchNo(pdDosagert.getBatchNo());
			pde.setExpireDate(pdDosagert.getExpireDate()==null?"":DateUtils.formatDate(pdDosagert.getExpireDate(), "yyyy-MM-dd"));
			pde.setRtCount(pdDosagert.getRtCount()==null?"":String.valueOf(pdDosagert.getRtCount()));
			pde.setOperaterName(pdDosagert.getOperaterName());
			pde.setRemarks(pdDosagert.getRemarks());
			dataList.add(pde);
		}
		return dataList;
	}
	
	/**
	 * 院外明细查询---导出excel数据
	 * @param list
	 * @return
	 */
	public static List<PdRejectedListHeadExcel> getHospitaloutsideReturnQueryData(List<PdRejectedListHead> list) {
		List<PdRejectedListHeadExcel> dataList = Lists.newArrayList();
		for(PdRejectedListHead pdRejectedListHead :list){
			PdRejectedListHeadExcel plh = new PdRejectedListHeadExcel();
			plh.setNumber(pdRejectedListHead.getNumber());
			plh.setRejectedDate(pdRejectedListHead.getRejectedDate());
			plh.setRejectedRepoName(pdRejectedListHead.getRejectedRepoName());
			plh.setSupplierName(pdRejectedListHead.getGhdwMc());
			plh.setProductName(pdRejectedListHead.getProductName());
			plh.setSpec(pdRejectedListHead.getSpec());
			plh.setVersion(pdRejectedListHead.getVersion());
			plh.setBatchNo(pdRejectedListHead.getBatchNo());
			plh.setValidDate(pdRejectedListHead.getValidDate()==null?"":DateUtils.formatDate(pdRejectedListHead.getValidDate(), "yyyy-MM-dd"));
			plh.setRejectedCount(String.valueOf(pdRejectedListHead.getRejectedCount()));
			plh.setOperPerson(pdRejectedListHead.getOperPerson());
			plh.setRemarks(pdRejectedListHead.getRemarks());
			dataList.add(plh);
		}
		return dataList;
	}
	
	
	/**
	 * 进销存报表---导出excel数据
	 */
	public static List<PdRpInventoryExcel> getEntersSellsSavesDetailData(List<PdRpInventory> list) {
		List<PdRpInventoryExcel> dataList = Lists.newArrayList();
		for(PdRpInventory pdRpInventory :list){
			PdRpInventoryExcel pie = new PdRpInventoryExcel();
			pie.setSupplier(pdRpInventory.getSupplier());
			pie.setProductName(pdRpInventory.getProductName());
			pie.setVersion(pdRpInventory.getVersion());
			pie.setBeginStockNum(pdRpInventory.getBeginStockNum());
			pie.setRukNum(pdRpInventory.getRukNum());
			pie.setZhengcNum(pdRpInventory.getZhengcNum());
			pie.setDiaocNum(pdRpInventory.getDiaocNum());
			pie.setDiaorNum(pdRpInventory.getDiaorNum());
			pie.setTuihcNum(pdRpInventory.getTuihcNum());
			pie.setTuihrNum(pdRpInventory.getTuihrNum());
			pie.setShiyNum(pdRpInventory.getShiyNum());
			pie.setTuihNum(pdRpInventory.getTuihNum());
			pie.setYythNum(pdRpInventory.getYythNum());
			pie.setSellingPrice(pdRpInventory.getSellingPrice());
			pie.setDosageAmount(pdRpInventory.getDosageAmount());
			pie.setEndStockNum(pdRpInventory.getEndStockNum());
			dataList.add(pie);
		}
		return dataList;
	}

	/**
	 * 药品出库明细导出
	 * @param list
	 * @return
	 */
	public static List<MedstoYpckzdExcel> getDrugOutDetaileData(List<MedstoYpckzd> list) {
		List<MedstoYpckzdExcel> dataList = Lists.newArrayList();
		for(MedstoYpckzd medstoYpckzd :list){
			MedstoYpckzdExcel mye = new MedstoYpckzdExcel();
			mye.setRkdm(medstoYpckzd.getRkdm());
			mye.setRkdh(medstoYpckzd.getRkdh());
			mye.setKsdm(medstoYpckzd.getKsdm());
			mye.setGhdwdm(medstoYpckzd.getGhdwdm());
			mye.setFph(medstoYpckzd.getFph());
			mye.setKprq(medstoYpckzd.getKprq()==null?"":DateUtils.formatDate(medstoYpckzd.getKprq(), "yyyy-MM-dd"));
			mye.setDprq(medstoYpckzd.getDprq()==null?"":DateUtils.formatDate(medstoYpckzd.getDprq(), "yyyy-MM-dd"));
			mye.setRkrq(medstoYpckzd.getRkrq()==null?"":DateUtils.formatDate(medstoYpckzd.getRkrq(), "yyyy-MM-dd"));
			mye.setRkczyh(medstoYpckzd.getRkczyh());
			mye.setLsje(medstoYpckzd.getLsje());
			mye.setPfje(medstoYpckzd.getPfje());
			mye.setJjje(medstoYpckzd.getJjje());
			mye.setYhje(medstoYpckzd.getYhje());
			mye.setPsdh(medstoYpckzd.getPsdh());
			mye.setDrugName(medstoYpckzd.getMedstoYpckmx().getDrugName());
			mye.setDrugSpec(medstoYpckzd.getMedstoYpckmx().getDrugSpec());
			mye.setSxrq(medstoYpckzd.getMedstoYpckmx().getSxrq()==null?"":DateUtils.formatDate(medstoYpckzd.getMedstoYpckmx().getSxrq(), "yyyy-MM-dd"));
			mye.setPh(medstoYpckzd.getMedstoYpckmx().getPh());
			mye.setCzsl(medstoYpckzd.getMedstoYpckmx().getCzsl());
			mye.setYpjj(medstoYpckzd.getMedstoYpckmx().getYpjj());
			mye.setYpfj(medstoYpckzd.getMedstoYpckmx().getYpfj());
			mye.setYlsj(medstoYpckzd.getMedstoYpckmx().getYlsj());
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品调价单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpdjdExcel> getdrugDrugPriceAdjustmentData(List<MedstoYpdjd> list) {
		List<MedstoYpdjdExcel> dataList = Lists.newArrayList();
		for(MedstoYpdjd medstoYpdjd :list){
			MedstoYpdjdExcel mye = new MedstoYpdjdExcel();
			mye.setDrugId(medstoYpdjd.getDrugId());
			mye.setDrugName(medstoYpdjd.getDrugName());
			mye.setDrugSpec(medstoYpdjd.getDrugSpec());
			mye.setYpmrkl(medstoYpdjd.getYpmrkl());
			mye.setStoreName(medstoYpdjd.getStoreName());
			mye.setYbtybm(medstoYpdjd.getYbtybm());
			mye.setIsjbyw(medstoYpdjd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpdjd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpdjd.getCmedCode());
			mye.setDrugDetailType(medstoYpdjd.getDrugDetailType());
			mye.setDoseCode(medstoYpdjd.getDoseCode());
			mye.setPackageUnit(medstoYpdjd.getPackageUnit());
			mye.setPackageQty(medstoYpdjd.getPackageQty());
			mye.setSalesUnit(medstoYpdjd.getSalesUnit());
			mye.setSalesQty(medstoYpdjd.getSalesQty());
			mye.setSalesRelation(medstoYpdjd.getSalesRelation());
			mye.setProducerName(medstoYpdjd.getProducerName());
			mye.setCostPrice(medstoYpdjd.getCostPrice());
			mye.setSalePrice(medstoYpdjd.getSalePrice());
			mye.setCostAmt(medstoYpdjd.getCostAmt());
			mye.setSaleAmt(medstoYpdjd.getSaleAmt());
			mye.setBatchNo(medstoYpdjd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpdjd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpdjd.getSdrughabitat());
			mye.setSratificationno(medstoYpdjd.getSratificationno());
			mye.setDdateproduce(medstoYpdjd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYpdjd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpdjd.getExpiry()==null?"":DateUtils.formatDate(medstoYpdjd.getExpiry(), "yyyy-MM-dd"));
			mye.setBeforeprice(medstoYpdjd.getBeforeprice());
			mye.setAfterprice(medstoYpdjd.getAfterprice());
			mye.setScrq(medstoYpdjd.getScrq()==null?"":DateUtils.formatDate(medstoYpdjd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品盘点单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYppddExcel> getdrugInventoryDetailedData(List<MedstoYppdd> list) {

		List<MedstoYppddExcel> dataList = Lists.newArrayList();
		for(MedstoYppdd medstoYppdd :list){
			MedstoYppddExcel mye = new MedstoYppddExcel();
			mye.setDrugId(medstoYppdd.getDrugId());
			mye.setDrugName(medstoYppdd.getDrugName());
			mye.setDrugSpec(medstoYppdd.getDrugSpec());
			mye.setYbtybm(medstoYppdd.getYbtybm());
			mye.setIsjbyw(medstoYppdd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYppdd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYppdd.getCmedCode());
			mye.setDrugDetailType(medstoYppdd.getDrugDetailType());
			mye.setDoseCode(medstoYppdd.getDoseCode());
			mye.setPackageUnit(medstoYppdd.getPackageUnit());
			mye.setPackageQty(medstoYppdd.getPackageQty());
			mye.setSalesUnit(medstoYppdd.getSalesUnit());
			mye.setSalesQty(medstoYppdd.getSalesQty());
			mye.setSalesRelation(medstoYppdd.getSalesRelation());
			mye.setProducerName(medstoYppdd.getProducerName());
			mye.setCostPrice(medstoYppdd.getCostPrice());
			mye.setSalePrice(medstoYppdd.getSalePrice());
			mye.setCostAmt(medstoYppdd.getCostAmt());
			mye.setSaleAmt(medstoYppdd.getSaleAmt());
			mye.setBatchNo(medstoYppdd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYppdd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYppdd.getSdrughabitat());
			mye.setSratificationno(medstoYppdd.getSratificationno());
			mye.setDdateproduce(medstoYppdd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYppdd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYppdd.getExpiry()==null?"":DateUtils.formatDate(medstoYppdd.getExpiry(), "yyyy-MM-dd"));
			mye.setInventorystorage(medstoYppdd.getInventorystorage());
			mye.setStoragenumber(medstoYppdd.getStoragenumber());
			mye.setInventorynumber(medstoYppdd.getInventorynumber());
			mye.setYpmrkl(medstoYppdd.getYpmrkl());
			mye.setScrq(medstoYppdd.getScrq()==null?"":DateUtils.formatDate(medstoYppdd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;

	}

	/**
	 * 药品丢失单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpbsdExcel> getDrugLostOrderDetaileData(List<MedstoYpbsd> list) {
		List<MedstoYpbsdExcel> dataList = Lists.newArrayList();
		for(MedstoYpbsd medstoYpbsd :list){
			MedstoYpbsdExcel mye = new MedstoYpbsdExcel();
			mye.setStoreName(medstoYpbsd.getStoreName());
			mye.setDrugId(medstoYpbsd.getDrugId());
			mye.setDrugName(medstoYpbsd.getDrugName());
			mye.setDrugSpec(medstoYpbsd.getDrugSpec());
			mye.setYbtybm(medstoYpbsd.getYbtybm());
			mye.setIsjbyw(medstoYpbsd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpbsd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpbsd.getCmedCode());
			mye.setDrugDetailType(medstoYpbsd.getDrugDetailType());
			mye.setDoseCode(medstoYpbsd.getDoseCode());
			mye.setPackageUnit(medstoYpbsd.getPackageUnit());
			mye.setPackageQty(medstoYpbsd.getPackageQty());
			mye.setSalesUnit(medstoYpbsd.getSalesUnit());
			mye.setSalesQty(medstoYpbsd.getSalesQty());
			mye.setSalesRelation(medstoYpbsd.getSalesRelation());
			mye.setProducerName(medstoYpbsd.getProducerName());
			mye.setCostPrice(medstoYpbsd.getCostPrice());
			mye.setSalePrice(medstoYpbsd.getSalePrice());
			mye.setCostAmt(medstoYpbsd.getCostAmt());
			mye.setSaleAmt(medstoYpbsd.getSaleAmt());
			mye.setBatchNo(medstoYpbsd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpbsd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpbsd.getSdrughabitat());
			mye.setSratificationno(medstoYpbsd.getSratificationno());
			mye.setDamagenumber(medstoYpbsd.getDamagenumber());
			mye.setDamagedatetime(medstoYpbsd.getDamagedatetime()==null?"":DateUtils.formatDate(medstoYpbsd.getDamagedatetime(), "yyyy-MM-dd"));
			mye.setDamageremark(medstoYpbsd.getDamageremark());
			mye.setDdateproduce(medstoYpbsd.getDdateproduce()==null?"":DateUtils.formatDate(medstoYpbsd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpbsd.getExpiry()==null?"":DateUtils.formatDate(medstoYpbsd.getExpiry(), "yyyy-MM-dd"));
			mye.setScrq(medstoYpbsd.getScrq()==null?"":DateUtils.formatDate(medstoYpbsd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 *	药品退药单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYptydExcel> getDrugReturnBillDetaileData(List<MedstoYptyd> list) {
		List<MedstoYptydExcel> dataList = Lists.newArrayList();
		for (MedstoYptyd medstoYptyd : list) {
			MedstoYptydExcel mye = new MedstoYptydExcel();
			mye.setStoreName(medstoYptyd.getStoreName());
			mye.setDrugId(medstoYptyd.getDrugId());
			mye.setDrugName(medstoYptyd.getDrugName());
			mye.setDrugSpec(medstoYptyd.getDrugSpec());
			mye.setYbtybm(medstoYptyd.getYbtybm());
			mye.setIsjbyw(medstoYptyd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYptyd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYptyd.getCmedCode());
			mye.setDrugDetailType(medstoYptyd.getDrugDetailType());
			mye.setDoseCode(medstoYptyd.getDoseCode());
			mye.setPackageUnit(medstoYptyd.getPackageUnit());
			mye.setPackageQty(medstoYptyd.getPackageQty());
			mye.setSalesUnit(medstoYptyd.getSalesUnit());
			mye.setSalesQty(medstoYptyd.getSalesQty());
			mye.setSalesRelation(medstoYptyd.getSalesRelation());
			mye.setProducerName(medstoYptyd.getProducerName());
			mye.setCostPrice(medstoYptyd.getCostPrice());
			mye.setSalePrice(medstoYptyd.getSalePrice());
			mye.setCostAmt(medstoYptyd.getCostAmt());
			mye.setSaleAmt(medstoYptyd.getSaleAmt());
			mye.setBatchNo(medstoYptyd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYptyd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYptyd.getSdrughabitat());
			mye.setSratificationno(medstoYptyd.getSratificationno());
			mye.setDdateproduce(medstoYptyd.getDdateproduce() == null ? "" : DateUtils.formatDate(medstoYptyd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYptyd.getExpiry() == null ? "" : DateUtils.formatDate(medstoYptyd.getExpiry(), "yyyy-MM-dd"));
			mye.setInventorystorage(medstoYptyd.getInventorystorage());
			mye.setStoragenumber(medstoYptyd.getStoragenumber());
			mye.setInventorynumber(medstoYptyd.getInventorynumber());
			mye.setThrq(medstoYptyd.getThrq() == null ? "" : DateUtils.formatDate(medstoYptyd.getThrq(), "yyyy-MM-dd"));
			mye.setThyy(medstoYptyd.getThyy());
			mye.setScrq(medstoYptyd.getScrq() == null ? "" : DateUtils.formatDate(medstoYptyd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}

	/**
	 * 药品发药单明细导出
	 * @param list
	 * @return
	 */
    public static List<MedstoYpfydExcel> getdispensingListDetailData(List<MedstoYpfyd> list) {
		List<MedstoYpfydExcel> dataList = Lists.newArrayList();
		for (MedstoYpfyd medstoYpfyd : list) {
			MedstoYpfydExcel mye = new MedstoYpfydExcel();
			mye.setStoreName(medstoYpfyd.getStoreName());
			mye.setDrugId(medstoYpfyd.getDrugId());
			mye.setDrugName(medstoYpfyd.getDrugName());
			mye.setDrugSpec(medstoYpfyd.getDrugSpec());
			mye.setYpmrkl(medstoYpfyd.getYpmrkl());
			mye.setYbtybm(medstoYpfyd.getYbtybm());
			mye.setIsjbyw(medstoYpfyd.getIsjbyw()=="1"?"是":"否");
			mye.setKss(medstoYpfyd.getKss()=="1"?"是":"否");
			mye.setCmedCode(medstoYpfyd.getCmedCode());
			mye.setDrugDetailType(medstoYpfyd.getDrugDetailType());
			mye.setDoseCode(medstoYpfyd.getDoseCode());
			mye.setPackageUnit(medstoYpfyd.getPackageUnit());
			mye.setPackageQty(medstoYpfyd.getPackageQty());
			mye.setSalesUnit(medstoYpfyd.getSalesUnit());
			mye.setSalesQty(medstoYpfyd.getSalesQty());
			mye.setSalesRelation(medstoYpfyd.getSalesRelation());
			mye.setProducerName(medstoYpfyd.getProducerName());
			mye.setCostPrice(medstoYpfyd.getCostPrice());
			mye.setSalePrice(medstoYpfyd.getSalePrice());
			mye.setCostAmt(medstoYpfyd.getCostAmt());
			mye.setSaleAmt(medstoYpfyd.getSaleAmt());
			mye.setBatchNo(medstoYpfyd.getBatchNo());
			mye.setSdrugmanufacturers(medstoYpfyd.getSdrugmanufacturers());
			mye.setSdrughabitat(medstoYpfyd.getSdrughabitat());
			mye.setSratificationno(medstoYpfyd.getSratificationno());
			mye.setDdateproduce(medstoYpfyd.getDdateproduce() == null ? "" : DateUtils.formatDate(medstoYpfyd.getDdateproduce(), "yyyy-MM-dd"));
			mye.setExpiry(medstoYpfyd.getExpiry() == null ? "" : DateUtils.formatDate(medstoYpfyd.getExpiry(), "yyyy-MM-dd"));
			mye.setPatientname(medstoYpfyd.getPatientname());
			mye.setPatientage(medstoYpfyd.getPatientage());
			mye.setPatientsex(medstoYpfyd.getPatientsex());
			mye.setNumber(medstoYpfyd.getNumber());
			mye.setPatientid(medstoYpfyd.getPatientid());
			mye.setOutpatientid(medstoYpfyd.getOutpatientid());
			mye.setOutpatientdoctor(medstoYpfyd.getOutpatientdoctor());
			mye.setPharmacist(medstoYpfyd.getPharmacist());
			mye.setFydatetime(medstoYpfyd.getFydatetime() == null ? "" : DateUtils.formatDate(medstoYpfyd.getFydatetime(), "yyyy-MM-dd"));
			mye.setScrq(medstoYpfyd.getScrq() == null ? "" : DateUtils.formatDate(medstoYpfyd.getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}


	/**
	 * 产品明细导出
	 * @param list
	 * @return
	 */
    public static List<ProductExcel> getProductListData(List<PdProduct> list) {
		List<ProductExcel> dataList = Lists.newArrayList();
		for (PdProduct prodbct : list) {
			ProductExcel prod = new ProductExcel();
			prod.setNumber(prodbct.getNumber());
			prod.setName(prodbct.getName());
			prod.setCategory(prodbct.getCategoryName());
			prod.setGroup(prodbct.getGroupName());
			prod.setUnit(prodbct.getUnit());
			prod.setSpec(prodbct.getSpec());
			prod.setVersion(prodbct.getVersion());
			prod.setVender(prodbct.getVenderName());
			prod.setChargeCode(prodbct.getChargeCode());
			prod.setRegistration(prodbct.getRegistration());
			prod.setSellingPrice(prodbct.getSellingPrice()+"");
			prod.setDescription(prodbct.getDescription());
			prod.setSupplierNameShow(prodbct.getSupplierNameShow());
			prod.setIsCharge(prodbct.getIsCharge().equals("1") ? "是" : "否");
			dataList.add(prod);
		}
		return dataList;
	}


	/**
	 * 采购产品明细导出
	 * @param list
	 * @return
	 */
	public static List<PurchaseDetailExcel> getPdPurchaseDetailListData(List<PdPurchaseDetail> list) {
		List<PurchaseDetailExcel> dataList = Lists.newArrayList();
		for (PdPurchaseDetail purchaseDetail : list) {
			PurchaseDetailExcel prod = new PurchaseDetailExcel();
			prod.setProdNo(purchaseDetail.getProdNo());
			prod.setProdName(purchaseDetail.getProdName());
			prod.setTpProdId(purchaseDetail.getTpProdId());
			prod.setSupplierName(purchaseDetail.getSupplierName());
			prod.setVenderName(purchaseDetail.getVenderName());
			prod.setApplyCount(purchaseDetail.getApplyCount()+"");
			prod.setStockNum(purchaseDetail.getStockNum()+"");
			prod.setProdUnit(purchaseDetail.getProdUnit());
			prod.setProdVersion(purchaseDetail.getProdVersion());
			prod.setProdSpec(purchaseDetail.getProdSpec());
			prod.setProdPrice(purchaseDetail.getProdPrice());
			dataList.add(prod);
		}
		return dataList;
	}



	/**
	 * 药品出库明细导出
	 * @param list
	 * @return
	 */
	public static List<MedstoRkzjlExcel> getDrugInDetaileData(List<MedstoRkzjl> list) {
		List<MedstoRkzjlExcel> dataList = Lists.newArrayList();
		for (MedstoRkzjl medstoRkzjl : list) {
			MedstoRkzjlExcel mye = new MedstoRkzjlExcel();
			mye.setJgbm(medstoRkzjl.getJgbm());
			mye.setStoreId(medstoRkzjl.getStoreId());
			mye.setStoreName(medstoRkzjl.getStoreName());
			mye.setEntryNo(medstoRkzjl.getEntryNo());
			mye.setDeliveryNo(medstoRkzjl.getDeliveryNo());
			mye.setOperName(medstoRkzjl.getOperName());
			mye.setCheckName(medstoRkzjl.getCheckName());
			mye.setCheckFlag(medstoRkzjl.getCheckFlag()=="0"?"未审核":"已审核");
			mye.setFph(medstoRkzjl.getFph());
			mye.setRkrq(medstoRkzjl.getRkrq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getRkrq(), "yyyy-MM-dd"));
			mye.setLsje(medstoRkzjl.getLsje());
			mye.setProducerName(medstoRkzjl.getProducerName());
			mye.setKprq(medstoRkzjl.getMedstoYprkmxNew().getKprq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getKprq(), "yyyy-MM-dd"));
			mye.setDprq(medstoRkzjl.getMedstoYprkmxNew().getDprq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getDprq(), "yyyy-MM-dd"));
			mye.setCzdm(medstoRkzjl.getMedstoYprkmxNew().getCzdm());
			mye.setCdIdm(medstoRkzjl.getMedstoYprkmxNew().getCdIdm());
			mye.setGgIdm(medstoRkzjl.getMedstoYprkmxNew().getGgIdm());
			mye.setDrugName(medstoRkzjl.getMedstoYprkmxNew().getDrugName());
			mye.setDrugSpec(medstoRkzjl.getMedstoYprkmxNew().getDrugSpec());
			mye.setYpdm(medstoRkzjl.getMedstoYprkmxNew().getYpdm());
			mye.setPcxh(medstoRkzjl.getMedstoYprkmxNew().getPcxh());
			mye.setExpiry(medstoRkzjl.getMedstoYprkmxNew().getExpiry() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getExpiry(), "yyyy-MM-dd"));
			mye.setPh(medstoRkzjl.getMedstoYprkmxNew().getPh());
			mye.setYkxs(medstoRkzjl.getMedstoYprkmxNew().getYkxs());
			mye.setYpdw(medstoRkzjl.getMedstoYprkmxNew().getYpdw());
			mye.setDwxs(medstoRkzjl.getMedstoYprkmxNew().getDwxs());
			mye.setRksl(medstoRkzjl.getMedstoYprkmxNew().getRksl());
			mye.setCzsl(medstoRkzjl.getMedstoYprkmxNew().getCzsl());
			mye.setYpkl(medstoRkzjl.getMedstoYprkmxNew().getYpkl());
			mye.setYpjj(medstoRkzjl.getMedstoYprkmxNew().getYpjj());
			mye.setYpfj(medstoRkzjl.getMedstoYprkmxNew().getYpfj());
			mye.setYlsj(medstoRkzjl.getMedstoYprkmxNew().getYlsj());
			mye.setJjje(medstoRkzjl.getMedstoYprkmxNew().getJjje());
			mye.setJxce(medstoRkzjl.getMedstoYprkmxNew().getJxce());
			mye.setXgxh(medstoRkzjl.getMedstoYprkmxNew().getXgxh());
			mye.setDpbz(medstoRkzjl.getMedstoYprkmxNew().getDpbz());
			mye.setMrzbj(medstoRkzjl.getMedstoYprkmxNew().getMrzbj());
			mye.setZbqh(medstoRkzjl.getMedstoYprkmxNew().getZbqh());
			mye.setZbdw(medstoRkzjl.getMedstoYprkmxNew().getZbdw());
			mye.setYpmrkl(medstoRkzjl.getMedstoYprkmxNew().getYpmrkl());
			mye.setScrq(medstoRkzjl.getMedstoYprkmxNew().getScrq() == null ? "" : DateUtils.formatDate(medstoRkzjl.getMedstoYprkmxNew().getScrq(), "yyyy-MM-dd"));
			dataList.add(mye);
		}
		return dataList;
	}
}
