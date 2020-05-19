/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdEncodingRule;

import java.util.List;

/**
 * 编码规则表DAO接口
 * @author zxh
 * @version 2019-04-22
 */
@MyBatisDao
public interface PdEncodingRuleDao extends CrudDao<PdEncodingRule> {

    List<PdEncodingRule> verify(PdEncodingRule pdEncodingRule);
}