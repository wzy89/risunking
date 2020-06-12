package com.wzy.risunking.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @description: 定时任务数据库操作
 * @author: Wangzy
 * @create: 2018-10-12 17:39
 **/
@Repository
public interface TimeTaskDao {

    /**
     * 获取所有有效的定时任务
     *
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @Create Wangzy 2018/12/19 15:21
     * @Update Wangzy 2018/12/19 15:21
     */
    List<Map> getAllEffectiveTasks();

    /**
     * 根据任务名称获取有效的定时任务
     *
     * @param taskName 任务名称
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @Create Wangzy 2018/12/19 16:02
     * @Update Wangzy 2018/12/19 16:02
     */
    List<Map> getEffectiveTask(@Param("taskName") String taskName);

    /**
     * 更新定时任务的时间规则
     * @param taskName 任务名称
     * @param timeRule 新的时间规则
     * @param taskSwitch 新的开关状态
     * @param taskDesc 新的任务描述
     * @return int
     * @Create Wangzy 2018/12/19 15:21
     * @Update Wangzy 2018/12/19 15:21
     */
    int updateTask(@Param("taskName") String taskName,
                   @Param("timeRule") String timeRule,
                   @Param("taskSwitch") String taskSwitch,
                   @Param("taskDesc") String taskDesc);
}
