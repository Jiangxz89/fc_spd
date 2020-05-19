package com.thinkgem.hys.pd.entity.bo;

import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * @author jiangxz
 * @description 产品组别BO
 * @date 2019-4-28
 */
public class PdGroupBO {
    private static final long serialVersionUID = 1L;
    private String id;		// _id
    private String name;		// 组别名称
    private String remarks;	// 备注
    private String createBy;	// 创建者
    private Date createDate;	// 创建日期
    private String updateBy;	// 更新者
    private Date updateDate;	// 更新日期
    private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
    private int synFlag ;   //同步标志，0-未同步；1-已同步

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
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

    public int getSynFlag() {
        return synFlag;
    }

    public void setSynFlag(int synFlag) {
        this.synFlag = synFlag;
    }
}
