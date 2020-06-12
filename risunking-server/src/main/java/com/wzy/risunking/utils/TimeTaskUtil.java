package com.wzy.risunking.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Map;

/**
 * @description: 定时任务工具类
 * @author: Wangzy
 * @create: 2018-12-19 14:21
 **/
public class TimeTaskUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeTaskUtil.class);

    private static SchedulerFactory haiSchedulerFactory = new StdSchedulerFactory();
    private static String HAI_JOB_GROUP_NAME = "HAI_EXTJWEB_JOBGROUP_NAME";
    private static String HAI_TRIGGER_GROUP_NAME = "HAI_EXTJWEB_TRIGGERGROUP_NAME";

    /**
     * 添加定时任务
     *
     * @param jobName 定时任务类
     * @param jobClass 定时的任务
     * @param timeRule  时间表达式
     * @param paramMap  定时任务需要的参数(可以不传)
     * @return void
     * @Create Wangzy 2018/12/19 11:01
     * @Update Wangzy 2018/12/19 11:01
     */
    public static void addJobAndStart(String jobName, Class<? extends Job> jobClass, String timeRule, Map<String,Object> paramMap)
            throws SchedulerException, ParseException {
        //任务名，任务组，任务执行类
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,HAI_JOB_GROUP_NAME).build();
        if (paramMap!=null){
            for(Map.Entry<String,Object> entry : paramMap.entrySet()){
                jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
            }
        }
        //触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(timeRule);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName,HAI_TRIGGER_GROUP_NAME).withSchedule(cronScheduleBuilder).build();
        //任务执行器
        Scheduler sched = haiSchedulerFactory.getScheduler();
        sched.scheduleJob(jobDetail,trigger);
        //启动
        if(!sched.isShutdown()){
            sched.start();
        }
    }

    /**
     * 更新定时任务时间规则
     *
     * @param jobName 任务名称
     * @param timeRule 时间规则
     * @return void
     * @Create Wangzy 2018/12/19 13:02
     * @Update Wangzy 2018/12/19 13:02
     */
    public static void modifyJobTime(String jobName, String timeRule,Map<String,Object>paramMap) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,HAI_TRIGGER_GROUP_NAME);
            Scheduler sched = haiSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if(trigger == null) {
                return;
            }
            String oldTimeRule = trigger.getCronExpression();
            if (!oldTimeRule.equalsIgnoreCase(timeRule)) {
                JobKey jobKey = JobKey.jobKey(jobName,HAI_JOB_GROUP_NAME);
                JobDetail jobDetail = sched.getJobDetail(jobKey);
                removeJob(jobName);
                addJobAndStart(jobName, jobDetail.getJobClass(), timeRule,paramMap);
            }
        } catch (Exception e) {
            logger.error("修改定时任务触发时间失败");
        }
    }

    /**
     * 判断某个定时任务是否存在
     *
     * @param jobName
     * @return
     */
    public static boolean jobIsExist(String jobName) {
        boolean isExist = false;
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,HAI_TRIGGER_GROUP_NAME);
            Scheduler sched = haiSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger != null){
                isExist = true;
            }
        }catch (Exception e){
            logger.error("获取定时任务失败：" + jobName);
        }
        return isExist;
    }

    /**
     * 移除定时任务
     *
     * @param jobName 定时任务名称
     * @return void
     * @Create Wangzy 2018/12/19 13:02
     * @Update Wangzy 2018/12/19 13:02
     */
    public static void removeJob(String jobName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,HAI_TRIGGER_GROUP_NAME);
            JobKey jobKey = JobKey.jobKey(jobName,HAI_JOB_GROUP_NAME);
            Scheduler sched = haiSchedulerFactory.getScheduler();
            // 停止触发器
            sched.pauseTrigger(triggerKey);
            // 移除触发器
            sched.unscheduleJob(triggerKey);
            // 删除任务
            sched.deleteJob(jobKey);
        } catch (Exception e) {
            logger.error("移除定时任务失败",e);
        }
    }
}
