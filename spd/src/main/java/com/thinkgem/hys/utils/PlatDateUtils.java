package com.thinkgem.hys.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

public class PlatDateUtils {

	public PlatDateUtils() {

	}

	public final static String UR = " with ur ";
	/** 一天的毫秒数 */
	public final static long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000;
	/** 本地化为简体中文 */
	public final static Locale DEFAULT_CHINA_LOCALE = Locale.SIMPLIFIED_CHINESE;
	/** 时区设置为北京时间 */
	public final static TimeZone DEFAULT_CHINA_TIMEZONE = TimeZone
			.getTimeZone("GMT+8:00");

	public final static String[] DEFAULT_WEEK_ARRAY_DESC = new String[] { "周一",
			"周二", "周三", "周四", "周五", "周六", "周日" };

	/**
	 * 功能: 把format格式的字符串日期转化成 日期
	 * 
	 *
	 * @param src
	 *            字符串
	 * @param format
	 *            字符串日期格式，默认yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromString(String src, String format)
			throws ParseException {
		if (src != null && !"".equals(src)) {
			SimpleDateFormat dateFormat;
			if (format == null || "".equals(format)) {
				dateFormat = new SimpleDateFormat(PATTERN_DATETIME);
			} else {
				dateFormat = new SimpleDateFormat(format);
			}
			return dateFormat.parse(src);
		}
		return null;
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前时间戳
	 * 
	 * @return Timestamp类型的时间
	 */
	public static Timestamp getSysTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前日期
	 * 
	 * @return 只返回String格式的日期，YYYY-MM-DD， 长10位
	 */
	public static String getSysDate() {
		return new Timestamp(System.currentTimeMillis()).toString().substring(
				0, 10);
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前日期和时间
	 * 
	 * @return 返回String格式的日期和时间, YYYY-MM-DD HH24:MI:SS， 长19位
	 */
	public static String getSysDateTime() {
		return new Timestamp(System.currentTimeMillis()).toString().substring(
				0, 19);
	}

	/**
	 * 获得时间戳
	 * 
	 * @param strDate
	 *            YYYY-MM-DD HH24:MI:SS格式的字符串
	 * @return 时间戳
	 */
	public static Timestamp getTimestamp(String strDate) {
		Timestamp rtValue = null;
		if (strDate == null || strDate.length() == 0) {
			return null;
		}
		try {
			rtValue = Timestamp.valueOf(strDate);
		} catch (Exception e) {
			strDate = strDate.trim() + " 00:00:00";
			try {
				rtValue = Timestamp.valueOf(strDate);
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
		}

		return rtValue;
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前日历
	 * 
	 * @return 返回String格式的日期和时间, YYYY-MM-DD HH24:MI:SS， 长19位
	 */
	private static String getSysDateTimeByCalendar() {
		StringBuffer str = new StringBuffer();
		Calendar rightNow = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		int iYear = rightNow.get(Calendar.YEAR);
		int iMonth = rightNow.get(Calendar.MONTH) + 1;
		int iDate = rightNow.get(Calendar.DATE);
		int iHour = rightNow.get(Calendar.HOUR_OF_DAY);
		int iMinute = rightNow.get(Calendar.MINUTE);
		int iSecond = rightNow.get(Calendar.SECOND);
		str.append(iYear);
		str.append("-");
		if (iMonth < 10)
			str.append("0");
		str.append(iMonth);
		str.append("-");
		if (iDate < 10)
			str.append("0");
		str.append(iDate);
		str.append(" ");
		str.append(iHour);
		if (iHour < 10)
			str.append("0");
		str.append(":");
		str.append(iMinute);
		if (iMinute < 0)
			str.append("0");
		str.append(":");
		if (iSecond < 0)
			str.append("0");
		str.append(iSecond);
		return str.toString();
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前日历
	 * 
	 * @return 只返回String格式的日期，YYYY-MM-DD， 长10位
	 */
	private static String getSysDateByCalendar() {
		StringBuffer str = new StringBuffer();
		Calendar rightNow = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		int iYear = rightNow.get(Calendar.YEAR);
		int iMonth = rightNow.get(Calendar.MONTH) + 1;
		int iDate = rightNow.get(Calendar.DATE);
		str.append(iYear);
		str.append("-");
		if (iMonth < 10) {
			str.append("0");
		}
		str.append(iMonth);
		str.append("-");
		if (iDate < 10) {
			str.append("0");
		}
		str.append(iDate);
		return str.toString();
	}

	/**
	 * 功能: 获得本地化的时间
	 *
	 * @param dateDesc
	 *            YYYY-MM-DD HH24:MI:SS 格式的字符串
	 * @return
	 */
	public static Calendar getCalendar(String dateDesc) {
		Calendar c = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		c.setTime(getTimestamp(dateDesc));
		return c;
	}

	/**
	 * 功能: 获得本地化的时间
	 *
	 * @param longDate
	 *            时间的长整数
	 * @return
	 */
	public static Calendar getCalendar(long longDate) {
		Calendar c = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		c.setTimeInMillis(longDate);
		return c;
	}

	/**
	 * 功能: 获得格式化的日期和时间描述
	 *
	 * @param longDate
	 *            时间的长整数
	 * @return YYYY-MM-DD HH24:MI:SS 格式的字符串
	 */
	public static String getFormatDateTimeDesc(long longDate) {
		return new Timestamp(longDate).toString().substring(0, 19);
	}

	/**
	 * 功能: 由毫秒数得到小时数
	 *
	 * @param longDate
	 * @return
	 */
	public static double getHourNumberByLong(long longDate) {
		return longDate / 60 * 60 * 1000;
	}

	/**
	 * 功能: 判断thisDate是否是今天
	 *
	 * @param thisDate
	 * @return
	 */
	public static boolean isToday(Date thisDate) {
		String today = getFormatDateTimeDesc(System.currentTimeMillis());
		String thisDateCal = getFormatDateTimeDesc(thisDate.getTime());
		if (today.substring(0, 10).endsWith(thisDateCal.substring(0, 10))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能: 判断long1-long2和long3-long4区域是否重叠
	 *
	 * @param long1
	 * @param long2
	 * @param long3
	 * @param long4
	 * @return
	 */
	public static boolean isOverlap(long long1, long long2, long long3,
			long long4) {
		boolean returnValue = false;
		if (long3 >= long1 && long3 < long2) {
			returnValue = true;
		} else if (long4 <= long2 && long4 > long1) {
			returnValue = true;
		} else if (long3 < long1 && long4 > long2) {
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * 功能: 转换时间为字符串，精确到分钟
	 *
	 * @param time1
	 *            Timestamp
	 * @return String
	 */
	public static String getTimePrecMinute(Timestamp time1) {
		if (time1 == null)
			return "";
		Calendar cal = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		cal.setTimeInMillis(time1.getTime());
		// 年
		String strYear = String.valueOf(cal.get(Calendar.YEAR));
		// 月
		String strMon;
		if (cal.get(Calendar.MONTH) + 1 < 10) {
			strMon = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			strMon = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		// 日
		String strDay;
		if (cal.get(Calendar.DATE) < 10) {
			strDay = "0" + String.valueOf(cal.get(Calendar.DATE));
		} else {
			strDay = String.valueOf(cal.get(Calendar.DATE));
		}
		// 时
		String strHour;
		if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
			strHour = "0" + String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		} else {
			strHour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		}

		// 分
		String strMin;
		if (cal.get(Calendar.MINUTE) < 10) {
			strMin = "0" + String.valueOf(cal.get(Calendar.MINUTE));
		} else {
			strMin = String.valueOf(cal.get(Calendar.MINUTE));
		}
		return strYear + "-" + strMon + "-" + strDay + " " + strHour + ":"
				+ strMin;
	}

	/**
	 * 功能: 转换时间为字符串，精确到天
	 *
	 * @param time1
	 *            Timestamp
	 * @return String
	 */
	public static String getStrDatePrecDay(Object otime) {
		if (otime == null
				|| otime.getClass().getName().equals("java.lang.String"))
			return "";
		Timestamp time1 = (Timestamp) otime;

		Calendar cal = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		cal.setTimeInMillis(time1.getTime());
		// 年
		String strYear = String.valueOf(cal.get(Calendar.YEAR));
		// 月
		String strMon;
		if (cal.get(Calendar.MONTH) + 1 < 10) {
			strMon = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			strMon = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		// 日
		String strDay;
		if (cal.get(Calendar.DATE) < 10) {
			strDay = "0" + String.valueOf(cal.get(Calendar.DATE));
		} else {
			strDay = String.valueOf(cal.get(Calendar.DATE));
		}
		return strYear + "-" + strMon + "-" + strDay;
	}

	/**
	 * 功能: 转换时间为字符串，精确到天，用中文表示
	 *
	 * @param time1
	 *            Timestamp
	 * @return String
	 */
	public static String getStrDatePrecDayWithChinese(Object otime) {
		if (otime == null
				|| otime.getClass().getName().equals("java.lang.String"))
			return "";
		Timestamp time1 = (Timestamp) otime;

		Calendar cal = Calendar.getInstance(DEFAULT_CHINA_TIMEZONE,
				DEFAULT_CHINA_LOCALE);
		cal.setTimeInMillis(time1.getTime());
		// 年
		String strYear = String.valueOf(cal.get(Calendar.YEAR));
		// 月
		String strMon;
		if (cal.get(Calendar.MONTH) + 1 < 10) {
			strMon = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			strMon = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		// 日
		String strDay;
		if (cal.get(Calendar.DATE) < 10) {
			strDay = "0" + String.valueOf(cal.get(Calendar.DATE));
		} else {
			strDay = String.valueOf(cal.get(Calendar.DATE));
		}
		return strYear + "年" + strMon + "月" + strDay + "日";
	}

	/**
	 * 功能: 比较compareDate是否比当前时间早minTime，默认三天内
	 *
	 * @param compareDate
	 *            要比较的时间
	 * @return
	 */
	public static boolean isNew(long compareDate) {
		return isNew(compareDate, 3 * ONE_DAY_MILLIS);
	}

	/**
	 * 功能: 比较compareDate是否比当前时间早minTime
	 *
	 * @param compareDate
	 *            要比较的时间
	 * @param minTime
	 *            最小差距
	 * @return
	 */
	public static boolean isNew(long compareDate, long minTime) {
		if (System.currentTimeMillis() - compareDate > minTime) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 功能: 得到系统时间如20060101010101
	 *
	 * @return
	 */
	public static String getJoinedSysDateTime() {
		String str = getSysDateTime();
		str = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10)
				+ str.substring(11, 13) + str.substring(14, 16)
				+ str.substring(17, 19);
		return str;
	}

	/**
	 * 功能: 得到系统时间如200601010，精确到天
	 * 
	 * @author wushqd
	 * @version 创建时间: 2014年11月24日 上午10:16:21
	 *
	 * @return
	 */
	public static String getJoinedSysDayTime() {
		String str = getSysDateTime();
		str = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
		return str;
	}

	/**
	 * 功能: 得到系统时间如20060101010101
	 * 
	 * @author wushqd
	 * @version 创建时间: 2014年11月22日 下午8:35:16
	 *
	 * @param date
	 * @return
	 */
	public static String getJoinedSysDateTime(Date date) {
		if (date != null) {
			String str = new Timestamp(date.getTime()).toString().substring(0,
					19);
			return str.substring(0, 4) + str.substring(5, 7)
					+ str.substring(8, 10) + str.substring(11, 13)
					+ str.substring(14, 16) + str.substring(17, 19);
		}
		return "";
	}

	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 日期+时间
	public static final String PATTERN_DATE = "yyyy-MM-dd"; // 日期
	public static final String PATTERN_TIME = "HH:mm:ss";
	public static final String PATTERN_DATE_STRING = "yyyyMMdd";
	public static final String PATTERN_DATE_SHORT_STRING = "yyMM"; // 时间
	public static final String PATTERN_DATETIME_STRING = "yyyyMMddHHmmss";

	private static SimpleDateFormat tool = null;

	/**
	 * 将一个日期格式的字符串转换成Timestamp,基础方法
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @param pattern
	 *            格式模板
	 * @return
	 */
	public static Timestamp strToTimestamp(String date, String pattern) {
		tool = new SimpleDateFormat(pattern);
		Timestamp ts = null;
		try {
			ts = new Timestamp(tool.parse(date).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 将一个字符串转换成Timestamp,例如：20131107064642 to 2013-11-07 06:46:42.0
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return
	 */
	public static Timestamp strToTimestamp(String date) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dDate;
		String reTime = "";
		try {
			dDate = format.parse(date);
			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			reTime = format2.format(dDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strToTimestamp(reTime, PATTERN_DATETIME);
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException {
		/*Timestamp t = PlatDateUtils.strToTimestamp("20131107064642");
		System.out.println(t);*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		String dstr="2018-05-16 00:00:00";  
		java.util.Date d2=sdf.parse(dstr);
		System.out.println(d2);
		Date date = new Date();
		if(date.after(d2)){
			System.out.println("guo");
		}
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		String dstr="2018-05-16 00:00:00";  
		java.util.Date d2=sdf.parse(dstr);
		boolean b = sameDate(new Date(), d2);
		System.out.println(b);*/
	}

	/**
	 * 将日期格式的字符串截取日期部分转换成Timestamp,使用模板yyyy-MM-dd
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return
	 */
	public static Timestamp dateStrToTs(String date) {
		return strToTimestamp(date, PATTERN_DATE);
	}

	/**
	 * 将字符串截取日期+时间格式转换成Timestamp,使用模板yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return
	 */
	public static Timestamp dateTimeStrToTs(String date) {
		return strToTimestamp(date, PATTERN_DATETIME);
	}

	/**
	 * 将Timestamp类型转换为字符串类型
	 * 
	 * @param num
	 * @return
	 */
	public static String timestampToStr(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}

		String str = timestamp.toString();
		return str.substring(0, str.length() - 2);
	}

	/**
	 * 讲字符串截取出只有时间的格式转化成Timestamp,使用模板HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static Timestamp timeStrToTs(String time) {
		return strToTimestamp(time, PATTERN_TIME);
	}

	// ---------------------------

	/**
	 * 将一个日期格式的字符串转换成一个Date对象
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Date timeStrToDate(String date, String pattern) {
		tool = new SimpleDateFormat(pattern);
		Date dt = null;
		try {
			dt = new Date(tool.parse(date).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	/**
	 * 将一个日期格式的字符串转化成一个Date对象
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date dateStrToDate(String date) {
		return strToTimestamp(date, PATTERN_DATE);
	}

	/**
	 * 将一个日期+时间格式的字符串转化成一个Date对象
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date dateTimeStrToDate(String date) {
		return strToTimestamp(date, PATTERN_DATETIME);
	}

	/**
	 * 将一个只有时间格式的字符串转化成Date对象
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date timeStrToDate(String date) {
		return strToTimestamp(date, PATTERN_TIME);
	}

	// -------------------------

	/**
	 * 将一个Date按照格式模板进行格式化,返回字串,基础方法
	 * 
	 * @param ts
	 *            Timestamp
	 * @param pattern
	 *            格式模板
	 * @return
	 * @throws Exception
	 */
	public static String dateToStr(Date dt, String pattern) {
		tool = new SimpleDateFormat(pattern);

		if (dt == null)
			return "";
		else
			return tool.format(dt);
	}

	/**
	 * 将一个Date按照格式模板进行格式化,返回字串,使用格式模板yyyy-MM-dd
	 * 
	 * @param ts
	 *            Timestamp
	 * @return
	 * @throws Exception
	 */
	public static String dateToDateStr(Date dt) {
		return dateToStr(dt, PATTERN_DATE);
	}

	/**
	 * 将一个Date按照格式模板进行格式化,返回字串,使用格式模板yyyy-MM-dd
	 * 
	 * @param ts
	 *            Timestamp
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Date dateToDate(Date dt) {
		String str = dateToStr(dt, PATTERN_DATE);
		return java.sql.Date.valueOf(str);
	}

	/**
	 * 将一个Date按照格式模板进行格式化,返回字串,使用格式模板yyyy-MM-dd HH:mm:ss
	 * 
	 * @param ts
	 * @return
	 * @throws Exception
	 */
	public static String dateToDateTimeStr(Date dt) {
		return dateToStr(dt, PATTERN_DATETIME);
	}

	/**
	 * 转化一个Date型数据为一个时间格式的字符串,使用模板HH:mm:ss
	 * 
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public static String dateToTimeStr(Date dt) {
		return dateToStr(dt, PATTERN_TIME);
	}

	/**
	 * 得到下一天
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public java.sql.Timestamp getNextDate(java.sql.Timestamp tsDate) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.DATE, 1);

		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 得到前一天
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public java.sql.Timestamp getPreviousDate(java.sql.Timestamp tsDate) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.DATE, -1);

		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 得到下一天
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public java.sql.Date getNextDate2(java.sql.Date tsDate) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.DATE, 1);

		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * 得到下一月日期
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public String getLastMonthDate(java.sql.Date tsDate) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.MONTH, -1);

		return dateToDateStr(calendar.getTime());
	}

	/**
	 * 得到下一月日期
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public String getMonthDate(java.sql.Date tsDate, int i) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.MONTH, i);

		return dateToDateStr(calendar.getTime());
	}

	/**
	 * 
	 * getOneMonthCondition(这里用一句话描述这个方法的作用)
	 * 
	 * @param queryCondition
	 *            之前的条件
	 * @param create_date
	 *            字段名称
	 * @return 参数中文名 总体查询条件、当前时间、一个月前时间
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	static public String[] getOneMonthCondition(String queryCondition,
			String create_date) {
		String nowDate = null;
		String lastMonDate = null;
		String[] returnValue = new String[3];
		nowDate = getSysDate();
		lastMonDate = getLastMonthDate(java.sql.Date.valueOf(nowDate));
		if (queryCondition == null || "".equals(queryCondition)) {
			queryCondition = create_date + " between timestamp('" + lastMonDate
					+ " 00:00:00')";
			queryCondition += " and timestamp('" + nowDate + " 23:59:59') ";
		} else {
			queryCondition += " and " + create_date + " between timestamp('"
					+ lastMonDate + " 00:00:00')";
			queryCondition += " and timestamp('" + nowDate + " 23:59:59') ";
		}
		returnValue[0] = queryCondition;
		returnValue[1] = nowDate;
		returnValue[2] = lastMonDate;
		return returnValue;
	}

	/**
	 * 
	 * getOneMonthCondition(这里用一句话描述这个方法的作用)
	 * 
	 * @param queryCondition
	 *            之前的条件
	 * @param create_date
	 *            字段名称
	 * @return 参数中文名 总体查询条件、当前时间、一个月前时间
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	static public String[] getMonthCondition(String queryCondition,
			String create_date, int i) {
		String nowDate = null;
		String lastMonDate = null;
		String[] returnValue = new String[3];
		nowDate = getSysDate();
		lastMonDate = getMonthDate(java.sql.Date.valueOf(nowDate), i);
		if (queryCondition == null || "".equals(queryCondition)) {
			queryCondition = create_date + " between timestamp('" + lastMonDate
					+ " 00:00:00')";
			queryCondition += " and timestamp('" + nowDate + " 23:59:59') ";
		} else {
			queryCondition += " and " + create_date + " between timestamp('"
					+ lastMonDate + " 00:00:00')";
			queryCondition += " and timestamp('" + nowDate + " 23:59:59') ";
		}
		returnValue[0] = queryCondition;
		returnValue[1] = nowDate;
		returnValue[2] = lastMonDate;
		return returnValue;
	}

	/**
	 * 得到前一天
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public java.sql.Date getPreviousDate2(java.sql.Date tsDate) {
		if (null == tsDate)
			return null;
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(tsDate);

		calendar.add(Calendar.DATE, -1);

		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * 获得指定日期之后nDay日（nDay>0）的或者指定日期之前nDay日（nDay<0）的日期
	 * 
	 * @param tsDate
	 *            日期
	 * @param nDay
	 *            间隔日数
	 * @author yangwentao
	 * @时间：2005-3-15
	 */
	static public java.sql.Date getDateByAddDate(java.sql.Date tsDate, int nDay) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by yangwentao
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.DATE, nDay);
		java.util.Date resDate = calendar.getTime();
		return new java.sql.Date(resDate.getTime());
	}
	static public Timestamp getTimestampByAddDate(Date tsDate, int nDay) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by yangwentao
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.DATE, nDay);
		java.util.Date resDate = calendar.getTime();
		return new Timestamp(resDate.getTime());
	}

	/**
	 * 获得指定日期之后nMonth月（nMonth>0）的或者指定日期之前nMonth月（nMonth<0）的日期
	 * 
	 * @param tsDate
	 *            日期
	 * @param nMonth
	 *            间隔月数
	 * @author yangwentao
	 * @时间：2005-3-15
	 */
	static public java.sql.Date getDateByAddMonth(java.sql.Date tsDate,
			int nMonth) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by yangwentao
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.MONTH, nMonth);
		java.util.Date resDate = calendar.getTime();
		return new java.sql.Date(resDate.getTime());
	}

	/**
	 * 获得指定日期之后nYear年（nYear>0）的或者指定日期之前nYear年（nYear<0）的日期
	 * 
	 * @param tsDate
	 *            日期
	 * @param nYear
	 *            间隔年数
	 * @author yangwentao
	 * @时间：2005-3-15
	 */
	static public java.sql.Date getDateByAddYear(java.sql.Date tsDate, int nYear) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by yangwentao
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.YEAR, nYear);
		java.util.Date resDate = calendar.getTime();
		return new java.sql.Date(resDate.getTime());
	}

	/**
	 * 得指定的日期
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public java.sql.Timestamp getGivenDate(java.sql.Timestamp tsDate,
			int nDay) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by Huang Ye
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(tsDate);

		calendar.add(Calendar.DATE, nDay);
		java.util.Date resDate = calendar.getTime();
		return new Timestamp(resDate.getTime());
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar c = new GregorianCalendar();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		int min = c.getActualMinimum(c.DAY_OF_MONTH);
		c.set(Calendar.DATE, min);
		return c.getTime();
	}

	/**
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMaxDayOfMonth(int year, int month) {
		Calendar c = new GregorianCalendar();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		int max = c.getActualMaximum(c.DAY_OF_MONTH);
		c.set(Calendar.DATE, max);
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 4); // Sunday
		return c.getTime();
	}

	/**
	 * 获得指定日期之后nDay日（nDay>0）的或者指定日期之前nDay日（nDay<0）的日期
	 * 
	 * @param tsDate
	 *            日期
	 * @param nDay
	 *            间隔日数
	 * @author xieshangwei
	 * @时间：2008-08-22
	 */
	static public String getDateByAddDate(Date tsDate, int nDay) {
		if (null == tsDate)
			return null;
		// Comment and Replaced by yangwentao
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.DATE, nDay);
		Date resDate = calendar.getTime();
		return dateToDateStr(resDate);
	}

	/**
	 * 格林威治时间转化为北京时间
	 * 
	 * @param strDate
	 *            传入的格林威治时间时间
	 * 
	 * @return String
	 */
	public static String getGLWZTtoBJT(String strDate) throws Exception {

		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = format1.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		GregorianCalendar ca = new GregorianCalendar(
				TimeZone.getTimeZone("GMT 00:00"));
		ca.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getDefault());

		return format.format(ca.getTime());

	}

	/**
	 * 
	 * getTime(Timestamp转化成"20121011"的字符串)
	 * 
	 * @param time
	 * @return 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String getTime(Timestamp time) {
		String timeTemp = getTimePrecMinute(time);
		return timeTemp.substring(0, 4) + timeTemp.substring(5, 7)
				+ timeTemp.substring(8, 10);
	}

	/**
	 * 功能: 转换时间为字符串，类似：xx天xx小时xx分钟
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getTimeFor(long time) {
		long minute = time / 1000 / 60;
		String timeStr = minute + "分钟";
		if (minute > 60) {
			minute = time / 1000 / 60 % 60;
			long hour = time / 1000 / 60 / 60;
			timeStr = hour + "小时" + minute + "分钟";
			if (hour > 24) {
				hour = time / 1000 / 60 / 60 % 24;
				long day = time / 1000 / 60 / 60 / 24;
				timeStr = day + "天" + hour + "小时" + minute + "分钟";
			}
		}
		return timeStr;
	}

	/**
	 * 功能: 转换时间为字符串，类似：xx天xx小时xx分钟
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getTimeForHour(long time) {
		// long start_time =
		// strToTimestamp("2012-08-13 10:41:29",PATTERN_DATETIME).getTime();
		// long end_time =
		// strToTimestamp("2012-11-01 14:08:32",PATTERN_DATETIME).getTime();
		// long total_time = end_time -start_time;
		BigDecimal minute = ((new BigDecimal(time).divide(new BigDecimal(1000),
				2, BigDecimal.ROUND_CEILING)).divide(new BigDecimal(60), 2,
				BigDecimal.ROUND_CEILING)).divide(new BigDecimal(60), 2,
				BigDecimal.ROUND_CEILING);
		String timeStr = String.valueOf(minute);
		// 如果大于60
		// if(minute.compareTo(new BigDecimal(60))==1){
		// // minute = time/1000/60%60;
		// long hour = time/1000/60/60;
		// BigDecimal fm = minute.divide(new
		// BigDecimal(60),2,BigDecimal.ROUND_CEILING);
		// timeStr = String.valueOf(new BigDecimal(hour).add(fm));
		// // System.out.println();
		// }
		return timeStr;
	}

	// public static void main(String[] args) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	// Date date = new Date();
	// java.util.Calendar calendar = Calendar.getInstance();
	// calendar.setTime(date);
	//
	//
	//
	// calendar.add(Calendar.MONTH,0);
	// String str=sdf.format(calendar.getTime());
	//
	// // return date;
	// // System.out.println(getTimeForHour(new Long("261832091").longValue()));
	// // System.out.println("*******************当前月份是："+getMonth());
	// System.out.println(str);
	// }
	/**
	 * 功能: 获得当月第一天(例：2013-03-01 00:00:00)
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str + " 00:00:00";
	}

	/**
	 * 功能: 获得上一个月的第一天(例：2013-03-01 00:00:00)
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getLastMonthDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 取前一月

		str = sdf.format(lastDate.getTime());
		return str + " 00:00:00";
	}

	/**
	 * 功能: 计算当月最后一天,返回字符串(例：2013-03-31 00:00:00)
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());

		return str + " 00:00:00";
	}

	/**
	 * 功能: 计算上月最后一天,返回字符串(例：2013-03-31 00:00:00)
	 *
	 * @param time
	 *            long
	 * @return String
	 */
	public static String getLastMonthDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		// lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());

		return str + " 00:00:00";
	}

	/**
	 * 功能: 计算前天,返回字符串
	 *
	 * @param
	 * @return String
	 */
	public static String getTheDayBeforeYesterday() {
		Calendar cal = Calendar.getInstance();
		Date dateTime = new Date(cal.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = format.format(dateTime);
		long time = cal.getTimeInMillis() - 2 * 24 * 60 * 60 * 1000;
		Date dateTime2 = new Date(time);
		dateString = format.format(dateTime2);
		return dateString;
	}

	public static String getOneDay(String dateString, int number) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, number);
		date = calendar.getTime();
		String returndateString = df.format(date);
		return returndateString;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 
	 * getCurrentYearFirst(获得本年第一天的日期,例：2013-1-1 00:00:00)
	 * 
	 * @return 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// DateFormat df = DateFormat.getDateInstance();
		String preYearDay = sdf.format(yearDay);
		return preYearDay + " 00:00:00";
	}

	/**
	 * 
	 * 获取当前系统时间的前一天，返回格式yyyy-MM-dd
	 * 
	 * @return 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String getYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		String yesterday = sdf.format(calendar.getTime());
		return yesterday;
	}
	/**
	 * 功能: 得到前 d 天
	 * @author wushqd
	 * @version 创建时间: 2015年5月13日 上午11:18:22
	 *
	 * @param d
	 * @return
	 */
	public static String getPreviousday(int d) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, d);
		String yesterday = sdf.format(calendar.getTime());
		return yesterday;
	}

	/**
	 * 获取当前月份的阿拉伯数字 如 2013-07-17获取 7
	 */
	public static Integer getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	public static Date changeStringToDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String getDefaultTime(int length) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date date = new Date();
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.MONTH, length);
		String str = sdf.format(calendar.getTime());

		// return date;
		// System.out.println(getTimeForHour(new
		// Long("261832091").longValue()));
		// System.out.println("*******************当前月份是："+getMonth());
		return str;
	}

	// 20120920080233
	public static String getStringDate() {
		String date = PlatDateUtils.getSysDateTime();
		date = date.replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
		return date.substring(0, 8);
	}

	/**
	 * 计算两个日期的时间差
	 * 
	 * @param formatTime1
	 * @param formatTime2
	 * @return 相差的秒数
	 */
	public static int getTimeDifference(Timestamp formatTime1,
			Timestamp formatTime2) {
		SimpleDateFormat timeformat = new SimpleDateFormat(
				"yyyy-MM-dd,HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1))
					.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2))
					.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
		/*
		 * int hours=(int) ((t1 - t2)/3600000); int minutes=(int) (((t1 -
		 * t2)/1000-hours*3600)/60); int second=(int) ((t1 -
		 * t2)/1000-hours*3600-minutes*60);
		 */
		int second = (int) ((t1 - t2) / (1000));
		return second;
		/* return ""+hours+"小时"+minutes+"分"+second+"秒"; */
	}

	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 * 
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss",
				new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}
	/**
	 * 将ts转成yyyy-mm-dd格式的字符串
	 * @param ts
	 * @return
	 */
	public static String transTstoString(Timestamp ts){
		  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		  return sdf.format(ts);
	}
	
	/**
     * 获取当前日期是周几
     * @param dt
     * @return 当前日期是周几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    
    /**
     * 
     * @param date
     * @param month
     * @return
     */
    public static Date getAfterMonth(Date date,Integer month) {		
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);		
		return calendar.getTime();
	}
    /**
     * 取前几个月时间
     * @param date
     * @param month
     * @return
     */
    public static Date getBeforeMonth(Date date,Integer month) {		
		java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -month);		
		return calendar.getTime();
	}
    public static Date getAfterDay(Date date,Integer day){
    	java.util.Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);		
		return calendar.getTime();
    }
    /**
     * 是否为同一天
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameDate(Date d1, Date d2){         
    	   return DateUtils.isSameDay(d1, d2);  
    }
    
}

