package com.wzy.risunking.resource.service;

import com.wzy.risunking.resource.entity.DailyWordInfo;

import java.util.List;

/**
 * DailyWordService
 *
 * @author Wangzy
 * @date 2020/4/23 17:49
 */
public interface DailyWordService {
    /**
     * 获取每日一言（列表）
     *
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    List<DailyWordInfo> dailyWordList();
}
