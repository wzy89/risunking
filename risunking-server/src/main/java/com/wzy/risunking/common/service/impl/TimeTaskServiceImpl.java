package com.wzy.risunking.common.service.impl;

import com.wzy.risunking.common.dao.TimeTaskDao;
import com.wzy.risunking.common.service.TimeTaskService;
import com.wzy.risunking.utils.TimeTaskUtil;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:定时任务实现
 * @author: Wangzy
 * @create: 2018-12-19 15:50
 **/
@Service
public class TimeTaskServiceImpl implements TimeTaskService {

    Logger logger = LoggerFactory.getLogger(TimeTaskServiceImpl.class);

    @Autowired
    TimeTaskDao timeTaskDao;

    /**
     * 启动定时任务
     *
     * @param taskName 任务名字
     * @return void
     * @Create Wangzy 2018/12/19 14:59
     * @Update Wangzy 2018/12/19 14:59
     */
    @Override
    public void startTask(String taskName, Class<Job> taskExeClass, Map params) {
        List<Map> taskInfos = timeTaskDao.getEffectiveTask(taskName);
        if (taskInfos==null || taskInfos.isEmpty()){
            logger.error("定时任务不存在：" + taskName);
            return;
        }
        Map<String,Object>taskInfo = taskInfos.get(0);
        try {
            TimeTaskUtil.addJobAndStart(taskName,taskExeClass,taskInfo.get("timeRule").toString(), params);
        }catch (Exception e){
            logger.error("启动定时任务失败：" + taskName);
            e.printStackTrace();
        }
    }

    /**
     * 更改定时任务的时间规则/开关状态/描述
     *
     * @param taskName   任务名称(唯一标志)
     * @param timeRule   时间规则字符串
     * @param taskDesc   任务描述
     * @param taskSwitch 任务开关
     * @return void
     * @Create Wangzy 2018/12/19 15:04
     * @Update Wangzy 2018/12/19 15:04
     */
    @Override
    public void resetTask(String taskName, String timeRule, String taskDesc, String taskSwitch, Map params,Boolean isPermanent) {
        if (isPermanent){
            int updatedNum = timeTaskDao.updateTask(taskName,timeRule,taskSwitch,taskDesc);
            if (updatedNum > 0){
                TimeTaskUtil.modifyJobTime(taskName,timeRule,params);
            }else {
                logger.error("定时任务不需要更新：" + taskName);
            }
        }else {
            TimeTaskUtil.modifyJobTime(taskName,timeRule,params);
        }
    }

    /**
     * 停止定时任务
     *
     * @param taskName 定时任务名称
     * @return void
     * @Create Wangzy 2018/12/19 15:09
     * @Update Wangzy 2018/12/19 15:09
     */
    @Override
    public void stopTask(String taskName ,Boolean isPermanent) {
        if (isPermanent){
            int updatedNum = timeTaskDao.updateTask(taskName,null,"0",null);
            if (updatedNum > 0){
                TimeTaskUtil.removeJob(taskName);
            }else {
                logger.error("定时任务已经关闭，不需要重复关闭：" + taskName);
            }
        }else {
            TimeTaskUtil.removeJob(taskName);
        }
    }
}
