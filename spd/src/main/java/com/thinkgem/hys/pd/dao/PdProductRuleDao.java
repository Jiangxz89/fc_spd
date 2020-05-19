/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.hys.pd.entity.PdProductRule;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 产品绑定编码规则DAO接口
 * @author zxh
 * @version 2019-11-03
 */
@MyBatisDao
public interface PdProductRuleDao extends CrudDao<PdProductRule> {

    void batchSave(List<PdProductRule> pdProductRules);

    void deleteByProductId(String id);
}