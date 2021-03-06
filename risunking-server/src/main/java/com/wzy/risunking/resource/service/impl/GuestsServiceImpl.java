package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.dao.GuestsDao;
import com.wzy.risunking.resource.entity.BaseInfo;
import com.wzy.risunking.resource.entity.GuestInfo;
import com.wzy.risunking.resource.service.GuestsService;
import com.wzy.risunking.utils.DataCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GuestsServiceImpl
 *
 * @author Wangzy
 * @date 2020/9/28 19:01
 */
@Service
public class GuestsServiceImpl implements GuestsService {

    @Autowired
    private GuestsDao guestsDao;

    /**
     * 获取留言（列表 分页）
     *
     * @param searchInfo
     * @author Wangzy
     * @date 2020/4/23 17:56
     */
    @Override
    public List<GuestInfo> guestList(BaseInfo searchInfo) {
        searchInfo.initFrom();
        return guestsDao.guestList(searchInfo);
    }

    @Override
    public int guestListCount(BaseInfo searchInfo) {
        return guestsDao.guestListCount(searchInfo);
    }

    /**
     * 留言插入
     *
     * @param guestInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/28 21:37
     */
    @Override
    public int guestAdd(GuestInfo guestInfo) {
        String content = guestInfo.getContent();
        String nickName = guestInfo.getNickName();
        if (DataCheck.containsEmptyString(content,nickName)){
            throw new CommandException(Response.PARAM_ERROR,"参数错误");
        }
        String expand = guestInfo.getExpand();
        if (DataCheck.isEmptyString(expand)){
            guestInfo.setExpand("");
        }
        String pid = guestInfo.getPid();
        if (DataCheck.isEmptyString(pid)){
            guestInfo.setPid("0");
        }
        String email = guestInfo.getEmail();
        if (DataCheck.isEmptyString(email)){
            guestInfo.setEmail("");
        }
        return guestsDao.insertGuest(guestInfo);
    }
}
