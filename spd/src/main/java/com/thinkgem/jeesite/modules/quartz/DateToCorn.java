package com.thinkgem.jeesite.modules.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToCorn {
	
	 public static String getCron(java.util.Date  date){  
	        String dateFormat="ss mm HH dd MM ? yyyy";  
	        return fmtDateToStr(date, dateFormat);  
	    } 

	 /** 
	     * Description:格式化日期,String字符串转化为Date 
	     *  
	     * @param date 
	     * @param dtFormat 
	     *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd 
	     * @return 
	     */  
	    public static String fmtDateToStr(Date date, String dtFormat) {  
	        if (date == null)  
	            return "";  
	        try {  
	            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);  
	            return dateFormat.format(date);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return "";  
	        }  
	    } 
	    
	    public static void main(String[] args) {
	    	Date date = new Date();
	    	System.out.println(getCron(date));
		}
}
