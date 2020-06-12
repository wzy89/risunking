package com.wzy.risunking.user.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

/**
 * AutoCleanCorpseTask
 *
 * @author Wangzy
 * @date 2020/4/23 11:42
 */
public class AutoCleanCorpseTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello! time:" + new Date());
    }
}
