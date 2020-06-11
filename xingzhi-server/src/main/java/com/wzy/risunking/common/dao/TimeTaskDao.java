package com.wzy.risunking.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description: 定时任务数据库操作
 * @author: Wangzy
 * @create: 2018-10-12 17:39
 **/
@Repository
public class TimeTaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有有效的定时任务
     *
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @Create Wangzy 2018/12/19 15:21
     * @Update Wangzy 2018/12/19 15:21
     */
    public List<Map<String,Object>> getAllEffectiveTasks(){
        String sql = "SELECT task_name AS taskName," +
                     "task_expression AS timeRule," +
                     "task_desc AS taskDesc " +
                     "FROM risunking.time_task WHERE task_switch=1";
        List<Map<String,Object>> effectiveTasks = jdbcTemplate.queryForList(sql);
        if (effectiveTasks.size() >= 1){
            return effectiveTasks;
        }
        return null;
    }

    /**
     * 根据任务名称获取有效的定时任务
     *
     * @param taskName 任务名称
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @Create Wangzy 2018/12/19 16:02
     * @Update Wangzy 2018/12/19 16:02
     */
    public List<Map<String,Object>> getEffectiveTask(String taskName){
        String sql = "SELECT task_name AS taskName," +
                "task_expression AS timeRule," +
                "task_desc AS taskDesc " +
                "FROM risunking.time_task WHERE task_switch=1 AND task_name='"+taskName+"'";
        List<Map<String,Object>> effectiveTasks = jdbcTemplate.queryForList(sql);
        if (effectiveTasks.size() >= 1){
            return effectiveTasks;
        }
        return null;
    }

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
    public int updateTask(String taskName, String timeRule,String taskSwitch, String taskDesc){
        String sql = "UPDATE risunking.time_task SET ";
        if (timeRule != null && !"".equals(timeRule)){
            sql = sql + "task_expression = '" + timeRule + "', ";
        }
        if (taskSwitch != null && !"".equals(taskSwitch)){
            sql = sql + "task_switch = '" + taskSwitch + "', ";
        }
        if (taskDesc != null && !"".equals(taskDesc)){
            sql = sql + "task_desc = '" + taskDesc + "', ";
        }
        if (sql.contains(",")){
            sql = sql.substring(0,sql.length()-2);
            sql = sql + " where task_name = '"+taskName + "'";
            return jdbcTemplate.update(sql);
        }else {
            return 0;
        }
    }
}
