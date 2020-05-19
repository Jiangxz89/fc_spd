package com.thinkgem.jeesite.modules.sys.service.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.auth.Oauth2ClientDao;
import com.thinkgem.jeesite.modules.sys.dao.auth.Oauth2UserRelateDao;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2Client;
import com.thinkgem.jeesite.modules.sys.entity.auth.Oauth2UserRelate;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class OAuthService {
	
	@Autowired
	private Oauth2ClientDao oauth2ClientDao;
	
	
	@Autowired
	private Oauth2UserRelateDao  oauth2UserRelateDao;

    //添加 auth code
	@Transactional(readOnly = false)
    public void addAuthCode(String authCode, String username){
    	System.out.println("保存Code和username");
    	Oauth2UserRelate o = new Oauth2UserRelate();
    	o.setUserName(username);
    	o.setCode(authCode);
    	o.preInsert();
    	oauth2UserRelateDao.insertEntity(o);
    }
    
    //添加 access token
	@Transactional(readOnly = false)
    public void addAccessToken(String accessToken,String refressToken, String username){
    	System.out.println("保存用户名和accessToken的关联关系");
    	Oauth2UserRelate o = new Oauth2UserRelate();
    	o.setUserName(username);
    	o.setToken(accessToken);
    	o.setRefressToken(refressToken);
    	o.setBuildTime(new Date());
    	o.preUpdate();
    	oauth2UserRelateDao.addAccessToken(o);
    }

    //验证auth code是否有效
    public boolean checkAuthCode(String authCode){
    	return true;
    }
    
    //验证access token是否有效
   public boolean checkAccessToken(String accessToken){
    	return true;
    }

    //通过code获取用户名
    public String getUsernameByAuthCode(String authCode){
    	Oauth2UserRelate o = new Oauth2UserRelate();
    	o.setCode(authCode);
    	o = oauth2UserRelateDao.getUsernameByAuthCode(o);
    	if(o!=null){
    		return o.getUserName();
    	}else{
    		return "";	
    	}
    	
    }
    
    
    public String getUsernameByAccessToken(String accessToken){
      return "";	
    }


    //auth code / access token 过期时间
    public long getExpireIn(){
    	return 1l;
    }


    public boolean checkClientId(String clientId){
    	boolean flag = true;
    	Oauth2Client o = new Oauth2Client();
    	o.setClientId(clientId);
    	o = oauth2ClientDao.findByClientId(o);
    	if(o==null||StringUtils.isEmpty(o.getClientId())){
    		return false;
    	}
    	return flag;
    }

    public boolean checkClientSecret(String clientSecret){
    	
    	return true;
    }


}
