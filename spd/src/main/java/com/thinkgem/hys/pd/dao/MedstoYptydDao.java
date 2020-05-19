/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYptyd;

import java.util.List;

/**
 * 医院药品退药单表DAO接口
 * @author zxh
 * @version 2019-05-09
 */
@MyBatisDao
public interface MedstoYptydDao extends CrudDao<MedstoYptyd> {

    List<MedstoYptyd> findDrugReturnBillDetaile(MedstoYptyd medstoYptyd);
}