package com.wzy.risunking;

import com.wzy.risunking.common.service.TimeTaskService;
import com.wzy.risunking.global.config.Config;
import com.wzy.risunking.resource.dao.DailyWordDao;
import com.wzy.risunking.resource.service.impl.GetDailyWordTask;
import com.wzy.risunking.storage.service.FileUpDownLoadService;
import com.wzy.risunking.user.service.impl.AutoCleanCorpseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: spring启动后初始化操作
 * @author: Wangzy
 * @create: 2018-12-19 17:33
 **/
@Component
public class InitializerService implements CommandLineRunner {
    @Autowired
    TimeTaskService timeTaskService;

    @Autowired
    FileUpDownLoadService upDownLoadService;

    @Autowired
    DailyWordDao dailyWordDao;

    @Autowired
    Config config;

    /**
     * spring启动后，初始化处理(主要用于启动各个自启动的定时任务)
     *
     * @param strings
     * @return void
     * @Create Wangzy 2018/12/19 17:35
     * @Update Wangzy 2018/12/19 17:35
     */
    @Override
    public void run(String... strings) {

        //开启自动清理僵尸用户的定时任务
        String autoCleanCorpseTaskName = "AutoCleanCorpseTask";
        Class autoCleanCorpseTaskExeClass = AutoCleanCorpseTask.class;
        timeTaskService.startTask(autoCleanCorpseTaskName, autoCleanCorpseTaskExeClass,null);

        //开启获取每日一言的定时任务
        String getDailyWordTaskName = "GetDailyWordTask";
        Class getDailyWordTaskExeClass = GetDailyWordTask.class;
        Map params = new HashMap();
        params.put("upDownLoadService",upDownLoadService);
        params.put("dailyWordUrl",config.getDailyWordUrl());
        params.put("dailyWordDao",dailyWordDao);
        timeTaskService.startTask(getDailyWordTaskName, getDailyWordTaskExeClass, params);
    }
}
