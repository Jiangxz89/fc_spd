package com.thinkgem.hys.pd.entity.excel;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * @Auther: zxh
 * @Date: 2019/5/7 0007
 * @Description: com.thinkgem.hys.pd.entity.excel
 * @version: 1.0
 */
public class MedstoYpckzdExcel {
    @ExcelField(title="出库代码",sort=10,align=2)
    private String rkdm;//出库代码
    @ExcelField(title="出库单号",sort=20,align=2)
    private String rkdh;//出库单号
    @ExcelField(title="科室代码",sort=30,align=2)
    private String ksdm;//科室代码
    @ExcelField(title="供货单位",sort=40,align=2)
    private String ghdwdm;//供货单位
    @ExcelField(title="发票号",sort=50,align=2)
    private String fph;//发票号
    @ExcelField(title="开票日期",sort=60,align=2)
    private String kprq;//开票日期
    @ExcelField(title="开票日期",sort=70,align=2)
    private String dprq;//到票日期
    @ExcelField(title="出库日期",sort=80,align=2)
    private String rkrq;//出库日期
    @ExcelField(title="出库操作员",sort=90,align=2)
    private String rkczyh;//出库操作员
    @ExcelField(title="零售金额",sort=100,align=2)
    private String lsje;//零售金额
    @ExcelField(title="批发金额",sort=110,align=2)
    private String pfje;//批发金额
    @ExcelField(title="进价金额",sort=120,align=2)
    private String jjje;//进价金额
    @ExcelField(title="优惠金额",sort=130,align=2)
    private String yhje;//优惠金额
    @ExcelField(title="配送单号",sort=140,align=2)
    private String psdh;//配送单号
    @ExcelField(title="药品名称",sort=150,align=2)
    private String drugName;//药品名称
    @ExcelField(title="药品规格",sort=160,align=2)
    private String drugSpec;//药品规格
    @ExcelField(title="失效日期",sort=170,align=2)
    private String sxrq;//失效日期
    @ExcelField(title="批号",sort=180,align=2)
    private String ph;//批号
    @ExcelField(title="操作数量",sort=190,align=2)
    private String czsl; //操作数量
    @ExcelField(title="药品进价",sort=200,align=2)
    private String ypjj;//药品进价
    @ExcelField(title="批发价",sort=210,align=2)
    private String ypfj;//批发价
    @ExcelField(title="零售价",sort=220,align=2)
    private String ylsj;//零售价

    public String getRkdm() {
        return rkdm;
    }

    public void setRkdm(String rkdm) {
        this.rkdm = rkdm;
    }

    public String getRkdh() {
        return rkdh;
    }

    public void setRkdh(String rkdh) {
        this.rkdh = rkdh;
    }

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getGhdwdm() {
        return ghdwdm;
    }

    public void setGhdwdm(String ghdwdm) {
        this.ghdwdm = ghdwdm;
    }

    public String getFph() {
        return fph;
    }

    public void setFph(String fph) {
        this.fph = fph;
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

    public String getRkrq() {
        return rkrq;
    }

    public void setRkrq(String rkrq) {
        this.rkrq = rkrq;
    }

    public String getRkczyh() {
        return rkczyh;
    }

    public void setRkczyh(String rkczyh) {
        this.rkczyh = rkczyh;
    }

    public String getLsje() {
        return lsje;
    }

    public void setLsje(String lsje) {
        this.lsje = lsje;
    }

    public String getPfje() {
        return pfje;
    }

    public void setPfje(String pfje) {
        this.pfje = pfje;
    }

    public String getJjje() {
        return jjje;
    }

    public void setJjje(String jjje) {
        this.jjje = jjje;
    }

    public String getYhje() {
        return yhje;
    }

    public void setYhje(String yhje) {
        this.yhje = yhje;
    }

    public String getPsdh() {
        return psdh;
    }

    public void setPsdh(String psdh) {
        this.psdh = psdh;
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

    public String getSxrq() {
        return sxrq;
    }

    public void setSxrq(String sxrq) {
        this.sxrq = sxrq;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getCzsl() {
        return czsl;
    }

    public void setCzsl(String czsl) {
        this.czsl = czsl;
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
}
