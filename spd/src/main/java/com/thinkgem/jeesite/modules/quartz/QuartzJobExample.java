package com.thinkgem.jeesite.modules.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobExample implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		  System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
		
	}
	

}
