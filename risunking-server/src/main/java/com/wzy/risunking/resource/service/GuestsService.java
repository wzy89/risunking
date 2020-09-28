package com.wzy.risunking.resource.service;

import com.wzy.risunking.resource.entity.BaseInfo;
import com.wzy.risunking.resource.entity.GuestInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DailyWordService
 *
 * @author Wangzy
 * @date 2020/4/23 17:49
 */
public interface GuestsService {
    /**
     * 获取留言（列表 分页）
     *
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    List<GuestInfo> guestList(BaseInfo searchInfo);

    /**
     * 留言插入
     *
     * @param guestInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/28 21:37
     */
    int guestAdd(GuestInfo guestInfo);
}
