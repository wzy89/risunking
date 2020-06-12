package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.resource.dao.DailyWordDao;
import com.wzy.risunking.resource.entity.DailyWordInfo;
import com.wzy.risunking.resource.service.DailyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DailyWordServiceImpl
 *
 * @author Wangzy
 * @date 2020/4/23 17:50
 */
@Service
public class DailyWordServiceImpl implements DailyWordService {

    @Autowired
    DailyWordDao dailyWordDao;

    /**
     * 获取每日一言（列表）
     *
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    @Override
    public List<DailyWordInfo> dailyWordList() {
        return dailyWordDao.dailyWordList();
    }
}
