package com.thinkgem.jeesite.modules.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob implements Job {
	
	private String message;
	
	public String getMyTriger() {
		return message;
	}

	public void setMyTriger(String message) {
		this.message = message;
	}



	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		// 打印当前的执行时间
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Exec Time Is : " + sf.format(date));
		System.out.println("Hello World");
		
		Trigger trigger = arg0.getTrigger();
		
		System.out.println(trigger.getStartTime());
		System.out.println(trigger.getEndTime());
		JobKey jobKey = trigger.getJobKey();
		System.out.println(jobKey);
		
		TriggerKey triggerKey = arg0.getTrigger().getKey();
		
		JobDataMap datamap = arg0.getJobDetail().getJobDataMap();
		JobDataMap trimap = arg0.getTrigger().getJobDataMap();
		
		JobDataMap totalMap = arg0.getMergedJobDataMap();
		
		System.out.println(trimap.getString("message")+"======================================");
		System.out.println(message+"=====================");
		
	}

}
