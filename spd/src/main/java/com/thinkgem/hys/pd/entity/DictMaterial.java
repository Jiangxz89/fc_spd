/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 中心物资目录Entity
 * @author zxh
 * @version 2019-11-13
 */
public class DictMaterial extends DataEntity<DictMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String matid;		// 中心ID
	private String numcode;		// 批准文号
	private String name;		// 中文名称
	private String hisCode;  //his收费代码
	private String pycode;		// 拼音码
	private String wbcode;		// 五笔码
	private String cuscode;		// 自定义码
	private String packunit;		// 大单位
	private String miniunit;		// 大单位
	private String packamount;		// 大小单位转换系数
	private String spec;		// 规格
	private String inprice;		// 购进价
	private String sellprice;		// 零售价
	private String matclass;		// 物品大类
	private String matclassname;		// 物品大类名称
	private String mretype;		// 是否医保类型
	private String isfee;		// 是否收费项目
	private String iscost;		// 是否科室成本
	private String isstop;		// 是否停用
	private String modstaff;		// 修改人
	private String modstaffname;		// 修改人名
	private Date moddate;		// 修改时间
	private String sicode;		// 社保代码
	private String nhcode;		// 农合编码
	private String facid;		// 厂家ID
	private String facname;		// 厂家名称
	private String zxauditstatus;		// 状态
	private String zxauditor;		// 审核人
	private String zxauditorname;		// 审核人名
	private Date zxaudittime;		// 审核时间
	
	public DictMaterial() {
		super();
	}

	public DictMaterial(String id){
		super(id);
	}

	@Length(min=1, max=11, message="中心ID长度必须介于 1 和 11 之间")
	public String getMatid() {
		return matid;
	}

	public void setMatid(String matid) {
		this.matid = matid;
	}
	
	@Length(min=0, max=60, message="批准文号长度必须介于 0 和 60 之间")
	public String getNumcode() {
		return numcode;
	}

	public void setNumcode(String numcode) {
		this.numcode = numcode;
	}
	
	@Length(min=0, max=60, message="中文名称长度必须介于 0 和 60 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="拼音码长度必须介于 0 和 50 之间")
	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	
	@Length(min=0, max=50, message="五笔码长度必须介于 0 和 50 之间")
	public String getWbcode() {
		return wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}
	
	@Length(min=0, max=20, message="自定义码长度必须介于 0 和 20 之间")
	public String getCuscode() {
		return cuscode;
	}

	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	
	@Length(min=0, max=10, message="大单位长度必须介于 0 和 10 之间")
	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}
	
	@Length(min=0, max=10, message="大单位长度必须介于 0 和 10 之间")
	public String getMiniunit() {
		return miniunit;
	}

	public void setMiniunit(String miniunit) {
		this.miniunit = miniunit;
	}
	
	public String getPackamount() {
		return packamount;
	}

	public void setPackamount(String packamount) {
		this.packamount = packamount;
	}
	
	@Length(min=0, max=100, message="规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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
	
	@Length(min=0, max=9, message="物品大类长度必须介于 0 和 9 之间")
	public String getMatclass() {
		return matclass;
	}

	public void setMatclass(String matclass) {
		this.matclass = matclass;
	}
	
	@Length(min=0, max=20, message="物品大类名称长度必须介于 0 和 20 之间")
	public String getMatclassname() {
		return matclassname;
	}

	public void setMatclassname(String matclassname) {
		this.matclassname = matclassname;
	}
	
	@Length(min=0, max=4, message="是否医保类型长度必须介于 0 和 4 之间")
	public String getMretype() {
		return mretype;
	}

	public void setMretype(String mretype) {
		this.mretype = mretype;
	}
	
	@Length(min=0, max=4, message="是否收费项目长度必须介于 0 和 4 之间")
	public String getIsfee() {
		return isfee;
	}

	public void setIsfee(String isfee) {
		this.isfee = isfee;
	}
	
	@Length(min=0, max=4, message="是否科室成本长度必须介于 0 和 4 之间")
	public String getIscost() {
		return iscost;
	}

	public void setIscost(String iscost) {
		this.iscost = iscost;
	}
	
	@Length(min=0, max=4, message="是否停用长度必须介于 0 和 4 之间")
	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}
	
	@Length(min=0, max=11, message="修改人长度必须介于 0 和 11 之间")
	public String getModstaff() {
		return modstaff;
	}

	public void setModstaff(String modstaff) {
		this.modstaff = modstaff;
	}
	
	@Length(min=0, max=20, message="修改人名长度必须介于 0 和 20 之间")
	public String getModstaffname() {
		return modstaffname;
	}

	public void setModstaffname(String modstaffname) {
		this.modstaffname = modstaffname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Length(min=0, max=20, message="社保代码长度必须介于 0 和 20 之间")
	public String getSicode() {
		return sicode;
	}

	public void setSicode(String sicode) {
		this.sicode = sicode;
	}
	
	@Length(min=0, max=20, message="农合编码长度必须介于 0 和 20 之间")
	public String getNhcode() {
		return nhcode;
	}

	public void setNhcode(String nhcode) {
		this.nhcode = nhcode;
	}
	
	@Length(min=0, max=11, message="厂家ID长度必须介于 0 和 11 之间")
	public String getFacid() {
		return facid;
	}

	public void setFacid(String facid) {
		this.facid = facid;
	}
	
	@Length(min=0, max=150, message="厂家名称长度必须介于 0 和 150 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZxaudittime() {
		return zxaudittime;
	}

	public void setZxaudittime(Date zxaudittime) {
		this.zxaudittime = zxaudittime;
	}

	public String getHisCode() {
		return hisCode;
	}

	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}
}