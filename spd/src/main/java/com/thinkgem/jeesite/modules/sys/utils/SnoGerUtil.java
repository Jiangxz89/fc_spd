package com.thinkgem.jeesite.modules.sys.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.thinkgem.jeesite.common.utils.DateUtils;

public class SnoGerUtil {
	
	private static Random random = new Random();
	
	/**
	 * 生成25位编码，格式为：yyMMddHHmmssSSS + 8 随机数
	 * @return  String
	 */
	public static String getNo() {

		StringBuffer result = new StringBuffer();
		int len = 8;

		// 取时间
		String dateTime =getCurrentTimeNum();
		//result = result.append(dateTime.substring(0,8));
		result = result.append(dateTime);
		
		// 取10位随机数
		for (int i = 0; i < len; i++) {
			result = result.append(random.nextInt(10));
		}

		return result.toString();
	}	
	
	/**
	 * 取传入长度的随机数
	 * @param len
	 * @return  String
	 */
	public static String getSerialNumber(int len) {
		
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < len; i++) {
			result = result.append(random.nextInt(10));
		}
		
		return result.toString();
	}
	
	/**
	 * 获取验证码，6位随机数
	 * @return String
	 */
	public static String getVerifcode() {
		int len = 6;
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < len; i++) {
			result = result.append(random.nextInt(10));
		}
		
		return result.toString();
	}
	
	/**
	 * 取32位UUID
	 * @return String
	 */
	public static String GetUUID(){
		
		String id = UUID.randomUUID().toString();
		return id.replace("-", "");
	}
	
	public static String getCurrentTimeNum(){
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS");
	}
	
}
