package com.wzy.risunking.resource.dao;

import com.wzy.risunking.resource.entity.BaseInfo;
import com.wzy.risunking.resource.entity.DailyWordInfo;
import com.wzy.risunking.resource.entity.GuestInfo;
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
public interface GuestsDao {

    /**
     * 获取留言（列表 分页）
     *
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    List<GuestInfo> guestList(@Param("searchInfo") BaseInfo searchInfo);
    int guestListCount(@Param("searchInfo") BaseInfo searchInfo);
    /**
     * 插入留言数据
     *
     * @param guestInfo
     * @return int
     * @author Wangzy
     * @date 2020/4/23 19:31
     */
    int insertGuest(@Param("guestInfo") GuestInfo guestInfo);

}
