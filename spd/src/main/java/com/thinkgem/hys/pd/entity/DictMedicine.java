/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 中心药品目录Entity
 * @author zxh
 * @version 2019-09-20
 */
public class DictMedicine extends DataEntity<DictMedicine> {
	
	private static final long serialVersionUID = 1L;
	private String medid;		// ID
	private String numcode;		// 数字码
	private String number;		// 批准文号
	private String cname;		// 中文名称
	private String ename;		// 英文名称
	private String tname;		// 通用名
	private String lname;		// 拉丁名
	private String pycode;		// 拼音码
	private String wbcode;		// 五笔码
	private String pycodet;		// pycodet
	private String wbcodet;		// wbcodet
	private String cuscode;		// 自定义码
	private String invclass;		// 项目分类
	private String itemclass;		// 药品大类
	private String drugclass;		// 药品类别
	private String drugtype;		// 药品剂型
	private String drugform;		// drugform
	private String packunit;		// 大单位
	private String packamount;		// 大小单位系数
	private String miniunit;		// 小单位
	private String doseunit;		// doseunit
	private String dosenum;		// dosenum
	private String spec;		// 规格
	private String dosunit;		// 剂量单位
	private String dosage;		// 剂量单位转换系数
	private String basedos;		// 默认用量
	private String maxdos;		// 最大剂量
	private String inprice;		// 购进价
	private String sellprice;		// 零售价
	private String hstprice;		// 最高零售价
	private String isgmp;		// 是否GMP认证
	private String isaze;		// 是否麻醉药
	private String isposion;		// 是否毒性药
	private String isbasic;		// 是否基本用药
	private String iscostly;		// 是否贵重药
	private String islunacy;		// 是否精神药
	private String isbid;		// 是否中标
	private String isskin;		// 是否皮试药
	private String mretype;		// 是否医保类型
	private String isstop;		// 是否停用
	private String isantibiotic;		// 是否抗生素
	private String modstaff;		// 修改人
	private String modstaffname;		// 修改人名
	private Date moddate;		// 修改时间
	private String userange;		// 使用范围
	private String printclass;		// 医嘱打印分类
	private String sicode;		// 社保编码
	private String nhcode;		// 农合编码
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String zxauditstatus;		// 状态
	private String zxauditor;		// 审核人
	private String zxauditorname;		// 审核人名
	private String roundingmode;		// 取整方式
	private String hospitalid;		// 医院ID
	private String hospitalname;		// 医疗机构名称
	private String ishormone;		// 是否激素药
	
	public DictMedicine() {
		super();
	}

	public DictMedicine(String id){
		super(id);
	}

	@Length(min=1, max=11, message="ID长度必须介于 1 和 11 之间")
	public String getMedid() {
		return medid;
	}

	public void setMedid(String medid) {
		this.medid = medid;
	}
	
	@Length(min=1, max=30, message="数字码长度必须介于 1 和 30 之间")
	public String getNumcode() {
		return numcode;
	}

	public void setNumcode(String numcode) {
		this.numcode = numcode;
	}
	
	@Length(min=1, max=20, message="批准文号长度必须介于 1 和 20 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=1, max=60, message="中文名称长度必须介于 1 和 60 之间")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Length(min=1, max=60, message="英文名称长度必须介于 1 和 60 之间")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Length(min=1, max=60, message="通用名长度必须介于 1 和 60 之间")
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	@Length(min=1, max=60, message="拉丁名长度必须介于 1 和 60 之间")
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	@Length(min=1, max=50, message="拼音码长度必须介于 1 和 50 之间")
	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	
	@Length(min=1, max=50, message="五笔码长度必须介于 1 和 50 之间")
	public String getWbcode() {
		return wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}
	
	@Length(min=1, max=50, message="pycodet长度必须介于 1 和 50 之间")
	public String getPycodet() {
		return pycodet;
	}

	public void setPycodet(String pycodet) {
		this.pycodet = pycodet;
	}
	
	@Length(min=1, max=50, message="wbcodet长度必须介于 1 和 50 之间")
	public String getWbcodet() {
		return wbcodet;
	}

	public void setWbcodet(String wbcodet) {
		this.wbcodet = wbcodet;
	}
	
	@Length(min=1, max=10, message="自定义码长度必须介于 1 和 10 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=1, max=11, message="项目分类长度必须介于 1 和 11 之间")
	public String getInvclass() {
		return invclass;
	}

	public void setInvclass(String invclass) {
		this.invclass = invclass;
	}
	
	@Length(min=1, max=11, message="药品大类长度必须介于 1 和 11 之间")
	public String getItemclass() {
		return itemclass;
	}

	public void setItemclass(String itemclass) {
		this.itemclass = itemclass;
	}
	
	@Length(min=1, max=11, message="药品类别长度必须介于 1 和 11 之间")
	public String getDrugclass() {
		return drugclass;
	}

	public void setDrugclass(String drugclass) {
		this.drugclass = drugclass;
	}
	
	@Length(min=1, max=11, message="药品剂型长度必须介于 1 和 11 之间")
	public String getDrugtype() {
		return drugtype;
	}

	public void setDrugtype(String drugtype) {
		this.drugtype = drugtype;
	}
	
	@Length(min=1, max=20, message="drugform长度必须介于 1 和 20 之间")
	public String getDrugform() {
		return drugform;
	}

	public void setDrugform(String drugform) {
		this.drugform = drugform;
	}
	
	@Length(min=1, max=10, message="大单位长度必须介于 1 和 10 之间")
	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}
	
	public String getPackamount() {
		return packamount;
	}

	public void setPackamount(String packamount) {
		this.packamount = packamount;
	}
	
	@Length(min=1, max=10, message="小单位长度必须介于 1 和 10 之间")
	public String getMiniunit() {
		return miniunit;
	}

	public void setMiniunit(String miniunit) {
		this.miniunit = miniunit;
	}
	
	@Length(min=1, max=10, message="doseunit长度必须介于 1 和 10 之间")
	public String getDoseunit() {
		return doseunit;
	}

	public void setDoseunit(String doseunit) {
		this.doseunit = doseunit;
	}
	
	public String getDosenum() {
		return dosenum;
	}

	public void setDosenum(String dosenum) {
		this.dosenum = dosenum;
	}
	
	@Length(min=1, max=100, message="规格长度必须介于 1 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=1, max=10, message="剂量单位长度必须介于 1 和 10 之间")
	public String getDosunit() {
		return dosunit;
	}

	public void setDosunit(String dosunit) {
		this.dosunit = dosunit;
	}
	
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
	public String getBasedos() {
		return basedos;
	}

	public void setBasedos(String basedos) {
		this.basedos = basedos;
	}
	
	public String getMaxdos() {
		return maxdos;
	}

	public void setMaxdos(String maxdos) {
		this.maxdos = maxdos;
	}
	
	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}
	
	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	
	public String getHstprice() {
		return hstprice;
	}

	public void setHstprice(String hstprice) {
		this.hstprice = hstprice;
	}
	
	@Length(min=1, max=4, message="是否GMP认证长度必须介于 1 和 4 之间")
	public String getIsgmp() {
		return isgmp;
	}

	public void setIsgmp(String isgmp) {
		this.isgmp = isgmp;
	}
	
	@Length(min=1, max=4, message="是否麻醉药长度必须介于 1 和 4 之间")
	public String getIsaze() {
		return isaze;
	}

	public void setIsaze(String isaze) {
		this.isaze = isaze;
	}
	
	@Length(min=1, max=4, message="是否毒性药长度必须介于 1 和 4 之间")
	public String getIsposion() {
		return isposion;
	}

	public void setIsposion(String isposion) {
		this.isposion = isposion;
	}
	
	@Length(min=1, max=4, message="是否基本用药长度必须介于 1 和 4 之间")
	public String getIsbasic() {
		return isbasic;
	}

	public void setIsbasic(String isbasic) {
		this.isbasic = isbasic;
	}
	
	@Length(min=1, max=4, message="是否贵重药长度必须介于 1 和 4 之间")
	public String getIscostly() {
		return iscostly;
	}

	public void setIscostly(String iscostly) {
		this.iscostly = iscostly;
	}
	
	@Length(min=1, max=4, message="是否精神药长度必须介于 1 和 4 之间")
	public String getIslunacy() {
		return islunacy;
	}

	public void setIslunacy(String islunacy) {
		this.islunacy = islunacy;
	}
	
	@Length(min=1, max=4, message="是否中标长度必须介于 1 和 4 之间")
	public String getIsbid() {
		return isbid;
	}

	public void setIsbid(String isbid) {
		this.isbid = isbid;
	}
	
	@Length(min=1, max=4, message="是否皮试药长度必须介于 1 和 4 之间")
	public String getIsskin() {
		return isskin;
	}

	public void setIsskin(String isskin) {
		this.isskin = isskin;
	}
	
	@Length(min=1, max=4, message="是否医保类型长度必须介于 1 和 4 之间")
	public String getMretype() {
		return mretype;
	}

	public void setMretype(String mretype) {
		this.mretype = mretype;
	}
	
	@Length(min=1, max=4, message="是否停用长度必须介于 1 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=1, max=4, message="是否抗生素长度必须介于 1 和 4 之间")
	public String getIsantibiotic() {
		return isantibiotic;
	}

	public void setIsantibiotic(String isantibiotic) {
		this.isantibiotic = isantibiotic;
	}
	
	@Length(min=1, max=11, message="修改人长度必须介于 1 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=1, max=20, message="修改人名长度必须介于 1 和 20 之间")
	public String getModstaffname() {
		return modstaffname;
	}

	public void setModstaffname(String modstaffname) {
		this.modstaffname = modstaffname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="修改时间不能为空")
	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Length(min=1, max=4, message="使用范围长度必须介于 1 和 4 之间")
	public String getUserange() {
		return userange;
	}

	public void setUserange(String userange) {
		this.userange = userange;
	}
	
	@Length(min=1, max=4, message="医嘱打印分类长度必须介于 1 和 4 之间")
	public String getPrintclass() {
		return printclass;
	}

	public void setPrintclass(String printclass) {
		this.printclass = printclass;
	}
	
	@Length(min=1, max=20, message="社保编码长度必须介于 1 和 20 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=1, max=20, message="农合编码长度必须介于 1 和 20 之间")
	public String getNhcode() {
		return nhcode;
	}

	public void setNhcode(String nhcode) {
		this.nhcode = nhcode;
	}
	
	@Length(min=1, max=11, message="厂家ID长度必须介于 1 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=1, max=150, message="厂家名称长度必须介于 1 和 150 之间")
	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}
	
	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getZxauditstatus() {
		return zxauditstatus;
	}

	public void setZxauditstatus(String zxauditstatus) {
		this.zxauditstatus = zxauditstatus;
	}
	
	@Length(min=0, max=11, message="审核人长度必须介于 0 和 11 之间")
	public String getZxauditor() {
		return zxauditor;
	}

	public void setZxauditor(String zxauditor) {
		this.zxauditor = zxauditor;
	}
	
	@Length(min=0, max=20, message="审核人名长度必须介于 0 和 20 之间")
	public String getZxauditorname() {
		return zxauditorname;
	}

	public void setZxauditorname(String zxauditorname) {
		this.zxauditorname = zxauditorname;
	}
	
	@Length(min=1, max=6, message="取整方式长度必须介于 1 和 6 之间")
	public String getRoundingmode() {
		return roundingmode;
	}

	public void setRoundingmode(String roundingmode) {
		this.roundingmode = roundingmode;
	}
	
	@Length(min=1, max=6, message="医院ID长度必须介于 1 和 6 之间")
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	@Length(min=1, max=50, message="医疗机构名称长度必须介于 1 和 50 之间")
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	@Length(min=0, max=4, message="是否激素药长度必须介于 0 和 4 之间")
	public String getIshormone() {
		return ishormone;
	}

	public void setIshormone(String ishormone) {
		this.ishormone = ishormone;
	}
	
}