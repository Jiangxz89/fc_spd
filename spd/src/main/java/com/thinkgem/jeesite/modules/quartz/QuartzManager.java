package com.thinkgem.jeesite.modules.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class QuartzManager {
	 private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	 private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
	 
	 private Scheduler scheduler;

	 public Scheduler getScheduler() {
		return scheduler;
	  }

	 public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	 }


		/**
	     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @param jobName
	     *            任务名
	     * @param cls
	     *            任务
	     * @param time
	     *            时间设置，参考quartz说明文档
	     * 
	     * @Title: QuartzManager.java
	     */
	  public static void addJob(Scheduler sched, String jobName, @SuppressWarnings("rawtypes") Class cls, String time) {
	        try {
	        	//工作
	        	JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName,JOB_GROUP_NAME).build();
	        	
	            // 触发器
	        	CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
	        			.withIdentity(jobName, TRIGGER_GROUP_NAME)
	        			.withSchedule(CronScheduleBuilder.cronSchedule(time))
	        			.build();
	        	
	            sched.scheduleJob(jobDetail, trigger);
	            // 启动
	            if (!sched.isShutdown()) {
	                sched.start();
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    
	    /**
	     * @Description: 添加一个定时任务
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @param jobName
	     *            任务名
	     * @param jobGroupName
	     *            任务组名
	     * @param triggerName
	     *            触发器名
	     * @param triggerGroupName
	     *            触发器组名
	     * @param jobClass
	     *            任务
	     * @param time
	     *            时间设置，参考quartz说明文档
	     * 
	     * @Title: QuartzManager.java
	     */
	  public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, @SuppressWarnings("rawtypes") Class jobClass, String time) {
	        try {
	        	 JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
	        	
	            // 触发器
	        	  CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
	        			                .withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
	        	 
	        	  scheduler.scheduleJob(jobDetail, trigger);
	            if (!scheduler.isShutdown()) {
	            	scheduler.start();
	            }
	            
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 修改一个任务的触发时间
	     * 
	     * @param sched
	     *            调度器 *
	     * @param sched
	     *            调度器
	     * @param triggerName
	     * @param triggerGroupName
	     * @param time
	     * 
	     * @Title: QuartzManager.java
	     */
	    public void  modifyJobTime(String jobName, 
	            String jobGroupName, String triggerName, String triggerGroupName, String cron) {  
	        try {  
	            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
	            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
	            if (trigger == null) {  
	                return;  
	            }  

	            String oldTime = trigger.getCronExpression();  
	            if (!oldTime.equalsIgnoreCase(cron)) { 
	                /** 方式一 ：调用 rescheduleJob 开始 */
	                // 触发器  
	                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
	                // 触发器名,触发器组  
	                triggerBuilder.withIdentity(triggerName, triggerGroupName);
	                triggerBuilder.startNow();
	                // 触发器时间设定  
	                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
	                // 创建Trigger对象
	                trigger = (CronTrigger) triggerBuilder.build();
	                // 方式一 ：修改一个任务的触发时间
	                scheduler.rescheduleJob(triggerKey, trigger);
	                /** 方式一 ：调用 rescheduleJob 结束 */

	                
	                /** 方式二：先删除，然后在创建一个新的Job  */
	                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
	                //Class<? extends Job> jobClass = jobDetail.getJobClass();  
	                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
	                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
	                /** 方式二 ：先删除，然后在创建一个新的Job */
	            }  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	    
	    
	    /**
	     * @Description: 移除一个任务
	     * 
	     * @param sched
	     *            调度器
	     * @param jobName
	     * @param jobGroupName
	     * @param triggerName
	     * @param triggerGroupName
	     * 
	     * @Title: QuartzManager.java
	     */
	    /** 
	     * @Description: 移除一个任务 
	     *  
	     * @param jobName 
	     * @param jobGroupName 
	     * @param triggerName 
	     * @param triggerGroupName 
	     */  
	    public void removeJob(String jobName, String jobGroupName,  
	            String triggerName, String triggerGroupName) {  
	        try {  
	            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

	            scheduler.pauseTrigger(triggerKey);// 停止触发器  
	            scheduler.unscheduleJob(triggerKey);// 移除触发器  
	            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  

	    /**
	     * @Description:启动所有定时任务
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @Title: QuartzManager.java
	     */
	    public void startJobs() {  
	        try {  
	            scheduler.start();  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	    }
	    /**
	     * @Description:关闭所有定时任务
	     * 
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * 
	     * @Title: QuartzManager.java
	     */
	   public static void shutdownJobs(Scheduler sched) {
	        try {
	            if (!sched.isShutdown()) {
	                sched.shutdown();
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	   	    
}
