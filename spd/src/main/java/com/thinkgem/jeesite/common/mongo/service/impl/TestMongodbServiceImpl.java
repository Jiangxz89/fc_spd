package com.thinkgem.jeesite.common.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.mongo.model.TestMongodbVo;
import com.thinkgem.jeesite.common.mongo.service.TestMongodbService;

@Service("testMongodbService")
public class TestMongodbServiceImpl implements TestMongodbService{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void save(TestMongodbVo testMongodbVo){
		mongoTemplate.insert(testMongodbVo);
	}
 
}
