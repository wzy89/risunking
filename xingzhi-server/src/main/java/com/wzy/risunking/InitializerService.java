package com.wzy.risunking;

import com.wzy.risunking.common.service.TimeTaskService;
import com.wzy.risunking.user.service.AutoCleanCorpseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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
    AutoCleanCorpseTask autoCleanCorpseTask;

    /**
     * spring启动后，初始化处理(主要用于启动各个自启动的定时任务)
     *
     * @param strings
     * @return void
     * @Create Wangzy 2018/12/19 17:35
     * @Update Wangzy 2018/12/19 17:35
     */
    @Override
    public void run(String... strings) throws Exception {

        //开启自动清理僵尸用户的定时任务
        //String taskName = "AutoCleanCorpseTask";
        //Class taskExeClass = autoCleanCorpseTask.getClass();
        //timeTaskService.startTask(taskName, taskExeClass,null);

        // 10s后重新设置执行时间表达式
        //Thread resetThread = new Thread(){
        //    public void run(){
        //        try {
        //            Thread.sleep(10000);
        //            String timeRule = "*/5 * * * * ?";
        //            System.out.println("重新设置定时任务时间表达式：*/2 * * * * ?");
        //            timeTaskService.resetTask(taskName,timeRule,null,null,null, false);
        //        } catch (InterruptedException e) { }
        //    }
        //};
        //resetThread.start();
        // 20s后关闭任务
        //Thread closeThread = new Thread(){
        //    public void run(){
        //        try {
        //            Thread.sleep(20000);
        //            System.out.println("停止定时任务！");
        //            timeTaskService.stopTask(taskName, false);
        //        } catch (InterruptedException e) { }
        //    }
        //};
        //closeThread.start();
    }
}
