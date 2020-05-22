package com.thinkgem.hys.pd.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author jiangxz
 * @description HIS项目收费代码
 * @date 2020-5-20
 */
public class HisChargeCode {

    private static final long serialVersionUID = 1L;

    private String SFCODE; //收费项目代码
    private String SFNO;   //收费项目编码
    private String SFNAME; //收费项目名称

    public String getSFCODE() {
        return SFCODE;
    }

    public void setSFCODE(String SFCODE) {
        this.SFCODE = SFCODE;
    }

    public String getSFNO() {
        return SFNO;
    }

    public void setSFNO(String SFNO) {
        this.SFNO = SFNO;
    }

    public String getSFNAME() {
        return SFNAME;
    }

    public void setSFNAME(String SFNAME) {
        this.SFNAME = SFNAME;
    }
}
