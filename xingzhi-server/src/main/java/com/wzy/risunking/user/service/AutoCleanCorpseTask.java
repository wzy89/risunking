package com.wzy.risunking.user.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutoCleanCorpseTask implements Job{
    private int i = 0;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        i++;
        System.out.println("hello" + i + "time:" + new Date());
    }
}
