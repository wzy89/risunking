package com.wzy.risunking.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzy.risunking.resource.dao.DailyWordDao;
import com.wzy.risunking.resource.entity.DailyWordInfo;
import com.wzy.risunking.storage.service.FileUpDownLoadService;
import com.wzy.risunking.utils.HttpClientUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * GetDailyWordTask
 *
 * @author Wangzy
 * @date 2020/4/23 11:42
 */
public class GetDailyWordTask implements Job {

    private Logger logger = LoggerFactory.getLogger(GetDailyWordTask.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String dailyWordUrl = jobExecutionContext.getJobDetail().getJobDataMap().get("dailyWordUrl").toString();
            FileUpDownLoadService fileUpDownLoadService = (FileUpDownLoadService) jobExecutionContext.getJobDetail().getJobDataMap().get("upDownLoadService");
            DailyWordDao dailyWordDao = (DailyWordDao) jobExecutionContext.getJobDetail().getJobDataMap().get("dailyWordDao");
            String jsonStr = HttpClientUtil.sendPhpGet(dailyWordUrl,null);
            JSONObject jsonResult = JSON.parseObject(jsonStr);
            JSONArray dataArray = jsonResult.getJSONArray("data");
            DailyWordInfo latestInfo = dailyWordDao.getLatestDailyWord();
            Integer maxTrdId = 0;
            if (latestInfo != null){
                maxTrdId = Integer.valueOf(latestInfo.getTrdId());
            }
            List<DailyWordInfo>insertList = new ArrayList<>(7);
            if (dataArray != null && dataArray.size()>0){
                Collections.sort(dataArray, new Comparator<Object>(){
                    @Override
                    public int compare(Object a , Object b)
                    {
                        JSONObject info1 = (JSONObject)a;
                        JSONObject info2 = (JSONObject)b;

                        return info1.getString("day").compareTo(info2.getString("day"));
                    }
                });
                for (Object infoObjc : dataArray){
                    JSONObject info = (JSONObject)infoObjc;
                    String dayStr = info.get("day").toString();
                    Integer startIndex = dayStr.indexOf("VOL.");
                    Integer endIndex = dayStr.indexOf("\n");
                    String trdIdStr = dayStr.substring(startIndex+4,endIndex);
                    Integer trdId = Integer.valueOf(trdIdStr);
                    if (trdId-maxTrdId>0){
                        DailyWordInfo newInfo = new DailyWordInfo();
                        newInfo.setSrc(fileUpDownLoadService.downloadPicture(info.getString("src"),"png"));
                        newInfo.setText(info.getString("text"));
                        newInfo.setTrdId(trdId);
                        insertList.add(newInfo);
                    }
                }
                if (insertList.size()>0){
                    dailyWordDao.insertDailyWord(insertList);
                }
            }
        } catch (Exception e){
            logger.error("GetDailyWordTask.execute报错："+e.getCause()+"->"+e.getMessage());
        }

    }
}
