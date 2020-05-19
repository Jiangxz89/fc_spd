/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.hys.pd.entity.PdEncodingIdentifier;

/**
 * 应用标识符表DAO接口
 * @author zxh
 * @version 2019-04-19
 */
@MyBatisDao
public interface PdEncodingIdentifierDao extends CrudDao<PdEncodingIdentifier> {
	
}