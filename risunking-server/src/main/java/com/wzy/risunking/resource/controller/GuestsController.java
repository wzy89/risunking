package com.wzy.risunking.resource.controller;

import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.entity.BaseInfo;
import com.wzy.risunking.resource.entity.GuestInfo;
import com.wzy.risunking.resource.service.GuestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * GuestsController
 *
 * @author Wangzy
 * @date 2020/9/28 18:59
 */
@RestController
@CrossOrigin
@RequestMapping(value = "web/resource/guests")
public class GuestsController {

    @Autowired
    private GuestsService guestsService;

    /**
     * 获取留言列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response<List<GuestInfo>> guestList(@RequestBody BaseInfo searchInfo) {

        List<GuestInfo> data = guestsService.guestList(searchInfo);
        int count = guestsService.guestListCount(searchInfo);
        return new Response<>(Response.SUCCESS_RESULT, Response.SUCCESS_RESULT_MSG, data, count);
    }

    /**
     * 留言插入
     *
     * @param guestInfo
     * @return java.lang.Integer
     * @author Wangzy
     * @date 2020/9/28 22:18
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer guestAdd(@RequestBody GuestInfo guestInfo) {
        return guestsService.guestAdd(guestInfo);
    }
}
