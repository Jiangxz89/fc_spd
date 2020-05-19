package com.thinkgem.jeesite.modules.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class QuartzTest {
	 
	 @Autowired
     private QuartzManager quartzManager;
	
	    @Test
	    public void quartz() {
		 
	    	String startDate = "2017-08-21 18:11:50";
	    	String updateDate = "2017-08-21 16:06:20";
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date date1 = null;;
	    	Date date2 = null;
			try {
				date1 = sdf.parse(startDate);
				date2 = sdf.parse(updateDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	    	
	    	String str1 = DateToCorn.getCron(date1);
	    	String str2 = DateToCorn.getCron(date2);
	    	
		    String cornStr = "0/1 * * * * ?";
	        
		    try {
	            SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	            Scheduler sche = gSchedulerFactory.getScheduler();
	            String job_name = "动态任务调度";
	            System.out.println("【系统启动】开始(每1秒输出一次)...");
	          //  quartzManager.addJob(sche, "jobName1", "jobGroupName1","triggerName1", "triggerGroupName1", QuartzJobExample.class, str1);
	            
	            
	         /*   quartzManager.addJob(sche, "jobName1","jobGroupName1"," triggerName1","triggerGroupName1", QuartzJobExample.class, cornStr);
	         
	            quartzManager.modifyJobTime(sche, "jobName1", "jobGroupName1"," ","triggerGroupName1", cornStr);*/
	            
	            Thread.sleep(40000);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
}
