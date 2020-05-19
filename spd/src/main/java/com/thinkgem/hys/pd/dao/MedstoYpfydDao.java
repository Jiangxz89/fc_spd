/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.MedstoYpfyd;

import java.util.List;

/**
 * 药房发药单表DAO接口
 * @author zxh
 * @version 2019-05-10
 */
@MyBatisDao
public interface MedstoYpfydDao extends CrudDao<MedstoYpfyd> {

    List<MedstoYpfyd> findDispensingListDetail(MedstoYpfyd medstoYpfyd);
}