package com.wzy.risunking.common.service;

import org.quartz.Job;
import java.util.Map;

public interface TimeTaskService {
    /**
     * 启动定时任务
     *
     * @param taskName 任务名字
     * @return void
     * @Create Wangzy 2018/12/19 14:59
     * @Update Wangzy 2018/12/19 14:59
     */
    void startTask(String taskName, Class<Job> taskExeClass, Map params);

    /**
     * 更改定时任务的时间规则/开关状态/描述
     *
     * @param taskName 任务名称(唯一标志)
     * @param timeRule 时间规则字符串
     * @param taskDesc 任务描述
     * @param taskSwitch 任务开关
     * @param isPermanent 是否永久生效(true会保存设置到数据库中，服务下次启动不会还原设置)
     * @return void
     * @Create Wangzy 2018/12/19 15:04
     * @Update Wangzy 2018/12/19 15:04
     */
    void resetTask(String taskName, String timeRule, String taskDesc, String taskSwitch, Map<String,String> params,Boolean isPermanent);

    /**
     * 停止定时任务
     * @param taskName 定时任务名称
     * @param isPermanent 是否永久生效(true会保存设置到数据库中，服务下次启动不会还原设置)
     * @return void
     * @Create Wangzy 2018/12/19 15:09
     * @Update Wangzy 2018/12/19 15:09
     */
    void stopTask(String taskName, Boolean isPermanent);
}
