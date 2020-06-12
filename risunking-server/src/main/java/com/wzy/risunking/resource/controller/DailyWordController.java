package com.wzy.risunking.resource.controller;

import com.wzy.risunking.resource.entity.DailyWordInfo;
import com.wzy.risunking.resource.service.DailyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DailyWordController
 *
 * @author Wangzy
 * @date 2020/4/23 17:47
 */
@RestController
@CrossOrigin
@RequestMapping(value = "web/resource")
public class DailyWordController {

    @Autowired
    DailyWordService dailyWordService;

    /**
     * 获取每日一言
     *
     * @return
     */
    @RequestMapping(value = "/dailyWord",method = RequestMethod.POST)
    public List<DailyWordInfo> dailyWordList(){
        return dailyWordService.dailyWordList();
    }
}
