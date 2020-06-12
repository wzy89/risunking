package com.wzy.risunking.resource.dao;

import com.wzy.risunking.resource.entity.DailyWordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DailyWordDao
 *
 * @author Wangzy
 * @date 2020/4/23 17:47
 */
@Repository
public interface DailyWordDao {

    /**
     * 获取每日一言（列表）
     *
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    List<DailyWordInfo> dailyWordList();

    /**
     * 插入多条每日数据
     *
     * @param dailyWordInfos
     * @return int
     * @author Wangzy
     * @date 2020/4/23 19:31
     */
    int insertDailyWord(@Param("dailyWordInfos") List<DailyWordInfo> dailyWordInfos);

    /**
     * 查询最新一条每日数据
     *
     * @return com.wzy.risunking.resource.entity.DailyWordInfo
     * @author Wangzy
     * @date 2020/4/23 19:31
     */
    DailyWordInfo getLatestDailyWord();
}
