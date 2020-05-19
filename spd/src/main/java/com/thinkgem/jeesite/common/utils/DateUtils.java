/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	/**
	 * 得到现在的日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getNowDate(){
		return new Date();
	}
	
	public static String getMyDate(String time, String pattern){
		if(time != null && !"".equals(time)){
			Date newDate = parseDate(time);
			return formatDate(newDate, pattern);
		}
		return null;
	}
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取时间差
	 * 当前时间的差
	 * @param time
	 * @return
	 * @throws ParseException 
	 */
	public static long getDateDiff(String time) throws ParseException{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();  
		Date timeDate = df.parse(time);
		long timeDiff = nowDate.getTime() - timeDate.getTime();
		long days = timeDiff/(1000 * 60 * 60 * 24);  //天数
		return days;
	}
	
	/**
	 * 获得时间戳
	 * @param time
	 * @return
	 */
	public static long getDateLong(String time){
		long result=0;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeDate;
		try {
			timeDate = df.parse(time);
			result=timeDate.getTime();
		} catch( ParseException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得秒级时间戳
	 * @param time
	 * @return
	 */
	public static Long getDateInteger(String time){
		long result=0;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeDate;
		String timestamp = "0" ;
		try {
			if(StringUtils.isNoneBlank( time )){
				timeDate = df.parse(time);
				result=timeDate.getTime();
				timestamp= String.valueOf(result/1000);  
			}
		} catch( ParseException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  Long.valueOf(timestamp);
	}
	
	/**
	 * 日期加天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, long days){
		Date addDayDate =  new Date(date.getTime()+days*24*60*60*1000);     
		return addDayDate;
	}
	
	/**
	 * 日期减天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subDay(Date date, long days){
		Date subDayDate =  new Date(date.getTime()-days*24*60*60*1000);     
		return subDayDate;
	}
	/**
	 * 获取当前日期是周几
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date){
		if(date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		return "周"+"日一二三四五六".charAt(w-1);
	}
	/**
	 * 获取当前周一的时间(中国时间：从周一开始本周)
	 */
	public static Date getCurrentWeekDate(Date date){
		if(date == null)
			return null;
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.setFirstDayOfWeek(Calendar.MONDAY);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return ca.getTime();
	}
	
	/**
	 * 获得某个时间的Date时间
	 * @param hour
	 * @param minute
	 * @param second
	 * @param addDay
	 * @return
	 */
	public static  Date getNeedTime(int hour,int minute,int second,int addDay){
	    Calendar calendar = Calendar.getInstance();
	    if(addDay != 0){
	        calendar.add(Calendar.DATE,addDay);
	    }
	    calendar.set(Calendar.HOUR_OF_DAY,hour);
	    calendar.set(Calendar.MINUTE,minute);
	    calendar.set(Calendar.SECOND,second);
	    return calendar.getTime();
	}
	
	/**
	 * 取当前日期的前后某天的日期
	 * @param date
	 * @param days 可以为负整数
	 * @return
	 */
	public static Date getOneDayDate(Date date, int days){
		if (null == date) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	/**
	 * 判断两个日期是否相等
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualsFromTwoDate(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		String str1 = formatDate(date1, "yyyy-MM-dd");
		String str2 = formatDate(date2, "yyyy-MM-dd");
		return str1.equals(str2);
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(getDayOfWeek(getNowDate()));
//		System.out.println(getCurrentWeekDate(new Date()));
		System.out.println(getOneDayDate(new Date(), -1));
	}
}
