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
    public static void addJobAndStart(String jobName,Class<Job> jobClass,String timeRule,Map<String,String> paramMap)
            throws SchedulerException, ParseException {
        //任务名，任务组，任务执行类
        JobDetail jobDetail = new JobDetail(jobName, HAI_JOB_GROUP_NAME, jobClass);
        if (paramMap!=null){
            for(Map.Entry<String,String> entry : paramMap.entrySet()){
                jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
            }
        }
        //触发器
        CronTrigger trigger = new CronTrigger(jobName, HAI_TRIGGER_GROUP_NAME);//触发器名,触发器组
        trigger.setCronExpression(timeRule);//触发器时间设定
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
    public static void modifyJobTime(String jobName, String timeRule,Map<String,String>paramMap) {
        try {
            Scheduler sched = haiSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName, HAI_TRIGGER_GROUP_NAME);
            if(trigger == null) {
                return;
            }
            String oldTimeRule = trigger.getCronExpression();
            if (!oldTimeRule.equalsIgnoreCase(timeRule)) {
                JobDetail jobDetail = sched.getJobDetail(jobName, HAI_JOB_GROUP_NAME);
                removeJob(jobName);
                addJobAndStart(jobName, jobDetail.getJobClass(), timeRule,paramMap);
            }
        } catch (Exception e) {
            logger.error("修改定时任务触发时间失败");
        }
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
            Scheduler sched = haiSchedulerFactory.getScheduler();
            sched.pauseTrigger(jobName, HAI_TRIGGER_GROUP_NAME);// 停止触发器
            sched.unscheduleJob(jobName, HAI_TRIGGER_GROUP_NAME);// 移除触发器
            sched.deleteJob(jobName, HAI_JOB_GROUP_NAME);// 删除任务
        } catch (Exception e) {
            logger.error("移除定时任务失败",e);
            throw new RuntimeException(e);
        }
    }
}
