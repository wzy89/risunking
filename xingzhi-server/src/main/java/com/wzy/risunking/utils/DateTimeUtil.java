package com.wzy.risunking.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    /**
     * 获取当前时间的毫秒时间戳
     *
     * @return 当前时间的毫秒时间戳
     */
    public static long getCurrentDateTimestamp(){
        return (new Date()).getTime();
    }

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getCurrentDateTimeString(String dateFormat){
        SimpleDateFormat ft = new SimpleDateFormat (dateFormat);
        return ft.format(new Date());
    }
}
