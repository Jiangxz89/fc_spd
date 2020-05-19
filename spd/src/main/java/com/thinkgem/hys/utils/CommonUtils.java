package com.thinkgem.hys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import com.thinkgem.hys.pd.constants.MinaGlobalConstants;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 
 * @author Mr.Wang
 * @version 2018-03-09
 */
public class CommonUtils {
	
	private static final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789"; //字符池
	
	/**
	 * 随机生成你设定长度为n的数字字符串
	 * @return
	 */
	public static String randomInt(int n){
		if(n == 0 || n < 0){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i = 0; i < n; i++){
			sb.append(String.valueOf(random.nextInt(10)));
		}
		return sb.toString();
	}

	/**
	 * 随机生成采购单号
	 * @return
	 */
	public static String generatePurchaseOrderNo(){
		StringBuffer sb = new StringBuffer();
		//地区
		sb.append("CG");
		//时间戳
		String dateStr = DateUtils.getDate("yyyyMMddHHmmssSSS");
		sb.append(dateStr);
		//随机数
		sb.append(randomInt(3));
		return sb.toString();
	}
	
	/**
	 * 随机生成申领单号
	 * @return
	 */
	public static String generateApplyOrderNo(){
		StringBuffer sb = new StringBuffer();
		sb.append("SL");
		//时间戳
		String dateStr = DateUtils.getDate("yyyyMMddHHmmssSSS");
		sb.append(dateStr);
		//随机数
		sb.append(randomInt(3));
		return sb.toString();
	}
	
	/**
	 * 随机字符串（n位字母数字）
	 * 随机生成申领单号
	 * @return
	 */
	public static String generateDosageOrderNo(){
		StringBuffer sb = new StringBuffer();
		sb.append("YL");
		//时间戳
		String dateStr = DateUtils.getDate("yyyyMMddHHmmssSSS");
		sb.append(dateStr);
		//随机数
		sb.append(randomInt(3));
		return sb.toString();
	}
	/**
	 * 随机字符串（n位字母数字）
	 * 随机生成库房巡查单号
	 * @return
	 */
	public static String generatePatrolOrderNo(){
		StringBuffer sb = new StringBuffer();
		sb.append("XC");
		//时间戳
		String dateStr = DateUtils.getDate("yyyyMMddHHmmssSSS");
		sb.append(dateStr);
		//随机数
		sb.append(randomInt(3));
		return sb.toString();
	}
	
	/**
	 * 随机生成定数包编号（n位字母数字）
	 * */
	public static String getRandomString(int n){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		
		for(int i = 0 ; i < n ; i ++){
			char c = CHAR_POOL.charAt(random.nextInt(36));
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 生成指定格式日期
	 * @param {短日期：SHORT , 长日期：LONG}
	 * @return {短日期：yyyy-MM-dd ,长日期：yyyy-MM-dd HH:mm:ss }
	 * */
	public static String formatDate(String s){
		
		if(s.equals("SHORT")){
			s = "yyyy-MM-dd";
		}else if(s.equals("LONG")){
			s = "yyyy-MM-dd HH:mm:ss";
		}else{
			return null;
		}
		
		Date date = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
		
		String format = simpleDateFormat.format(date);
		
		return format;
	}
	
	/**
	 * 生成指定格式日期
	 * @param {短日期：SHORT , 长日期：LONG}
	 * @return {短日期：yyyy-MM-dd ,长日期：yyyy-MM-dd HH:mm:ss }
	 * */
	public static String formatDate(String s , Date date){
		
		if(date == null){
			date = new Date();
		}
		
		if(s.equals("SHORT")){
			s = "yyyy-MM-dd";
		}else if(s.equals("LONG")){
			s = "yyyy-MM-dd HH:mm:ss";
		}else{
			return null;
		}
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
		
		String format = simpleDateFormat.format(date);
		
		return format;
	}
	
	/**
	 * 生成编号：前缀+当前时间（精确到毫秒）
	 * */
	public static String generateNumber(String prefix){
		String format = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
		//String format = new SimpleDateFormat("HHmmssSSS") .format(new Date() );
		String number = prefix+format;
		return number;
	}
	
	/**
	 * 返回日期
	 * @param {[param today:今天;yesterday:昨天;thisWeek:本周一]}
	 * */
	public static Date getAppointedDate(String param){
		Date dNow = new Date();
		Calendar cal =Calendar.getInstance();  //日历
		cal.setTime(dNow);//把当前时间赋给日历
		if(param.equals("today")){//当天日期
			return dNow;
		}else if(param.equals("yesterday")){//前一天日期
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return cal.getTime();
		}else if(param.equals("thisWeek")){//本周一日期
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			//cal.add(Calendar.WEEK_OF_YEAR, 1);
			return cal.getTime();
		}else{
			return null;
		}
	}
	
	/**
	 * 拼接多天日期格式字符串，用于模糊查询
	 * @param {[startDate:起始日期],[endDate:结束日期,null为当前日期]}
	 * @return [date,date,date...date]
	 * */
	public static String getFormatDates(Date startDate , Date endDate){
		
		if(endDate == null){
			endDate = new Date();
		}
		String result = "";
		Calendar cal =Calendar.getInstance();  //日历
		cal.setTime(startDate);//把当前时间赋给日历
		int j = 0;
		while(j == 0){
			if(cal.getTime().before(endDate)){
				result += formatDate("SHORT",cal.getTime()) + ",";
				cal.add(cal.DATE, 1);
			}else{
				result += formatDate("SHORT",endDate);
				j = 1;
			}
		}
		
		return result;
	}
	
	/**
	 * 从字符串里获取时间
	 * @param {String : 0000-00-00 - 0000-00-00}
	 * @return {0000-00-00 0000-00-00}
	 * */
	public static String getDateString(String s){
		if(s == null || s.equals("")){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int flag = 0 ;
		for(int i = 0 ; i < s.length(); i ++){
			if(s.charAt(i) == '-'){
				flag += 1;
				if(flag == 3){
					sb.append(" ");
				}else{
					sb.append(s.charAt(i));
				}
			}
			
			if(s.charAt(i) != ' '){
				sb.append(s.charAt(i));
			}
		}
		
		return sb.toString();
	}
	
	public static Date stringToDate(String s){
		if(s == null || s.equals("")){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		Date parse;
		try {
			parse = sdf.parse(s);
			return parse;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据单号首字母类型生成单号
	 * @return
	 */
	public static String generateOrderNoByType(String type){
		StringBuffer sb = new StringBuffer();
		//首字母
		sb.append(type);
		//时间戳
		sb.append(getCurrentTimeNum());
		//随机数
		sb.append(randomInt(3));
		return sb.toString();
	}
	
	//获取时间精确到毫秒
	public static String getCurrentTimeNum(){
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS");
	}
	
	//测试
	public static void main(String[] args) {
		
		System.out.println(generateOrderNoByType(MinaGlobalConstants.ORDER_NO_FIRST_LETTER_RK));
//		System.out.println(generatePurchaseOrderNo());
//		System.out.println(generateApplyOrderNo());
//		System.out.println(new Random().nextInt(10));
//		System.out.println(generateNumber("PD"));
//		System.out.println(getDate("SHORT"));
//		System.out.println(getFormatDates(getAppointedDate("thisWeek"),null));
//		System.out.println(generateNumber("96"));
	}
}
