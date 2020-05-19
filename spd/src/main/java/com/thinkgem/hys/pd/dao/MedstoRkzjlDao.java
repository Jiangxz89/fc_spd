/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoRkzjl;

import java.util.List;

/**
 * 入库主记录DAO接口
 * @author zxh
 * @version 2019-05-14
 */
@MyBatisDao
public interface MedstoRkzjlDao extends CrudDao<MedstoRkzjl> {

    List<MedstoRkzjl> findDrugInDetaile(MedstoRkzjl medstoRkzjl);
}