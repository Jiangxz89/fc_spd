/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYpckzd;

import java.util.List;

/**
 * 药品出库单DAO接口
 * @author zxh
 * @version 2019-05-07
 */
@MyBatisDao
public interface MedstoYpckzdDao extends CrudDao<MedstoYpckzd> {

    List<MedstoYpckzd> findDrugOutDetaile(MedstoYpckzd medstoYpckzd);
}