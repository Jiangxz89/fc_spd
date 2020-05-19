package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Auther: zxh
 * @Date: 2019/5/14 0014
 * @Description: com.thinkgem.hys.pd.entity.excel
 * @version: 1.0
 */
public class MedstoRkzjlExcel {
    @ExcelField(title="机构编码",sort=10,align=2)
    private String jgbm;		// 机构编码
    @ExcelField(title="药库编码",sort=20,align=2)
    private String storeId;		// 药库编码
    @ExcelField(title="药库名称",sort=30,align=2)
    private String storeName;		// 药库名称
    @ExcelField(title="入库单编号",sort=40,align=2)
    private String entryNo;		// 入库单编号
    @ExcelField(title="配送单编号",sort=50,align=2)
    private String deliveryNo;		// 配送单编号
    @ExcelField(title="经手人",sort=60,align=2)
    private String operName;		// 经手人
    @ExcelField(title="审核员",sort=70,align=2)
    private String checkName;		// 审核员
    @ExcelField(title="审核标记",sort=80,align=2)
    private String checkFlag;		// 审核标记:0未审核/1已审核
    @ExcelField(title="发票号",sort=90,align=2)
    private String fph;		// 发票号
    @ExcelField(title="入库日期",sort=100,align=2)
    private String rkrq;		// 入库日期
    //private String jjje;		// 进价金额
    @ExcelField(title="零售金额",sort=110,align=2)
    private String lsje;		// 零售金额
    @ExcelField(title="供货商名称",sort=120,align=2)
    private String producerName;		// 供货商名称
    @ExcelField(title="开票日期",sort=130,align=2)
    private String kprq;		// 开票日期
    @ExcelField(title="到票日期",sort=140,align=2)
    private String dprq;		// 到票日期
    @ExcelField(title="操作代码",sort=150,align=2)
    private String czdm;		// 操作代码
    @ExcelField(title="产地idm",sort=160,align=2)
    private String cdIdm;		// 产地idm
    @ExcelField(title="规格idm",sort=170,align=2)
    private String ggIdm;		// 规格idm
    @ExcelField(title="药品名称",sort=180,align=2)
    private String drugName;		// 药品名称
    @ExcelField(title="药品规格",sort=190,align=2)
    private String drugSpec;		// 药品规格
    @ExcelField(title="药品代码",sort=200,align=2)
    private String ypdm;		// 药品代码
    @ExcelField(title="批次序号",sort=210,align=2)
    private String pcxh;		// 批次序号
    @ExcelField(title="失效日期",sort=220,align=2)
    private String expiry;		// 失效日期
    @ExcelField(title="批号",sort=230,align=2)
    private String ph;		// 批号
    @ExcelField(title="药库系数",sort=240,align=2)
    private String ykxs;		// 药库系数
    @ExcelField(title="药品单位",sort=250,align=2)
    private String ypdw;		// 药品单位
    @ExcelField(title="单位系数",sort=260,align=2)
    private String dwxs;		// 单位系数
    @ExcelField(title="入库数量",sort=270,align=2)
    private String rksl;		// 入库数量
    @ExcelField(title="操作数量",sort=280,align=2)
    private String czsl;		// 操作数量
    @ExcelField(title="药品扣率",sort=290,align=2)
    private String ypkl;		// 药品扣率
    @ExcelField(title="药品进价",sort=300,align=2)
    private String ypjj;		// 药品进价
    @ExcelField(title="批发价",sort=310,align=2)
    private String ypfj;		// 批发价
    @ExcelField(title="零售价",sort=320,align=2)
    private String ylsj;		// 零售价
    @ExcelField(title="进价金额",sort=330,align=2)
    private String jjje;		// 进价金额
    @ExcelField(title="进销差额",sort=340,align=2)
    private String jxce;		// 进销差额
    @ExcelField(title="相关序号",sort=350,align=2)
    private String xgxh;		// 相关序号
    @ExcelField(title="到票标志",sort=360,align=2)
    private String dpbz;		// 到票标志
    @ExcelField(title="默认招标价",sort=370,align=2)
    private String mrzbj;		// 默认招标价
    @ExcelField(title="招标期号",sort=380,align=2)
    private String zbqh;		// 招标期号
    @ExcelField(title="中标单位",sort=390,align=2)
    private String zbdw;		// 中标单位
    @ExcelField(title="药品默认扣率",sort=400,align=2)
    private String ypmrkl;		// 药品默认扣率
    @ExcelField(title="上传日期",sort=410,align=2)
    private String scrq;		// 上传日期

    public String getJgbm() {
        return jgbm;
    }

    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getFph() {
        return fph;
    }

    public void setFph(String fph) {
        this.fph = fph;
    }

    public String getRkrq() {
        return rkrq;
    }

    public void setRkrq(String rkrq) {
        this.rkrq = rkrq;
    }

    public String getLsje() {
        return lsje;
    }

    public void setLsje(String lsje) {
        this.lsje = lsje;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getDprq() {
        return dprq;
    }

    public void setDprq(String dprq) {
        this.dprq = dprq;
    }

    public String getCzdm() {
        return czdm;
    }

    public void setCzdm(String czdm) {
        this.czdm = czdm;
    }

    public String getCdIdm() {
        return cdIdm;
    }

    public void setCdIdm(String cdIdm) {
        this.cdIdm = cdIdm;
    }

    public String getGgIdm() {
        return ggIdm;
    }

    public void setGgIdm(String ggIdm) {
        this.ggIdm = ggIdm;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public String getYpdm() {
        return ypdm;
    }

    public void setYpdm(String ypdm) {
        this.ypdm = ypdm;
    }

    public String getPcxh() {
        return pcxh;
    }

    public void setPcxh(String pcxh) {
        this.pcxh = pcxh;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getYkxs() {
        return ykxs;
    }

    public void setYkxs(String ykxs) {
        this.ykxs = ykxs;
    }

    public String getYpdw() {
        return ypdw;
    }

    public void setYpdw(String ypdw) {
        this.ypdw = ypdw;
    }

    public String getDwxs() {
        return dwxs;
    }

    public void setDwxs(String dwxs) {
        this.dwxs = dwxs;
    }

    public String getRksl() {
        return rksl;
    }

    public void setRksl(String rksl) {
        this.rksl = rksl;
    }

    public String getCzsl() {
        return czsl;
    }

    public void setCzsl(String czsl) {
        this.czsl = czsl;
    }

    public String getYpkl() {
        return ypkl;
    }

    public void setYpkl(String ypkl) {
        this.ypkl = ypkl;
    }

    public String getYpjj() {
        return ypjj;
    }

    public void setYpjj(String ypjj) {
        this.ypjj = ypjj;
    }

    public String getYpfj() {
        return ypfj;
    }

    public void setYpfj(String ypfj) {
        this.ypfj = ypfj;
    }

    public String getYlsj() {
        return ylsj;
    }

    public void setYlsj(String ylsj) {
        this.ylsj = ylsj;
    }

    public String getJjje() {
        return jjje;
    }

    public void setJjje(String jjje) {
        this.jjje = jjje;
    }

    public String getJxce() {
        return jxce;
    }

    public void setJxce(String jxce) {
        this.jxce = jxce;
    }

    public String getXgxh() {
        return xgxh;
    }

    public void setXgxh(String xgxh) {
        this.xgxh = xgxh;
    }

    public String getDpbz() {
        return dpbz;
    }

    public void setDpbz(String dpbz) {
        this.dpbz = dpbz;
    }

    public String getMrzbj() {
        return mrzbj;
    }

    public void setMrzbj(String mrzbj) {
        this.mrzbj = mrzbj;
    }

    public String getZbqh() {
        return zbqh;
    }

    public void setZbqh(String zbqh) {
        this.zbqh = zbqh;
    }

    public String getZbdw() {
        return zbdw;
    }

    public void setZbdw(String zbdw) {
        this.zbdw = zbdw;
    }

    public String getYpmrkl() {
        return ypmrkl;
    }

    public void setYpmrkl(String ypmrkl) {
        this.ypmrkl = ypmrkl;
    }

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }
}
