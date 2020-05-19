package com.thinkgem.hys.pd.entity.bo;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * @author jiangxz
 * @description
 * @date 2019-5-6
 */
public class HisPurchaseOrderBO {

    private static final long serialVersionUID = 1L;

    private String id;
    private String jgbm;		// 机构编码
    private String purNo;		// 采购单号
    private String purDate;		// 采购日期
    private String operName;		// 经手人
    private String checkName;		// 审核人
    private int synFlag ;   //同步标志，0-未同步；1-已同步

    protected String remarks;	// 备注
    protected User createBy;	// 创建者
    protected Date createDate;	// 创建日期
    protected User updateBy;	// 更新者
    protected Date updateDate;	// 更新日期
    protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

    List<HisPurchaseOrderItemBO> itemList;//明细列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Length(min=0, max=64, message="机构编码长度必须介于 0 和 64 之间")
    public String getJgbm() {
        return jgbm;
    }

    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    @Length(min=0, max=64, message="采购单号长度必须介于 0 和 64 之间")
    public String getPurNo() {
        return purNo;
    }

    public void setPurNo(String purNo) {
        this.purNo = purNo;
    }

    @Length(min=0, max=50, message="采购日期长度必须介于 0 和 50 之间")
    public String getPurDate() {
        return purDate;
    }

    public void setPurDate(String purDate) {
        this.purDate = purDate;
    }

    @Length(min=0, max=50, message="经手人长度必须介于 0 和 50 之间")
    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    @Length(min=0, max=50, message="审核人长度必须介于 0 和 50 之间")
    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public List<HisPurchaseOrderItemBO> getItemList() {
        return itemList;
    }

    public void setItemList(List<HisPurchaseOrderItemBO> itemList) {
        this.itemList = itemList;
    }

    public int getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(int synFlag) {
        this.synFlag = synFlag;
    }
}
