/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYppdd;

import java.util.List;

/**
 * 药品盘点表DAO接口
 * @author zxh
 * @version 2019-05-08
 */
@MyBatisDao
public interface MedstoYppddDao extends CrudDao<MedstoYppdd> {

    List<MedstoYppdd> findDrugInventoryDetailed(MedstoYppdd medstoYppdd);
}