/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdUnit;

import java.util.List;

/**
 * 产品单位表DAO接口
 * @author jiangxz
 * @version 2019-05-10
 */
@MyBatisDao
public interface PdUnitDao extends CrudDao<PdUnit> {

    List<PdUnit> verify(PdUnit pdUnit);
}