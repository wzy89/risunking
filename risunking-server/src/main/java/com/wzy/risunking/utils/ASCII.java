package com.wzy.risunking.utils;

public class ASCII {
    /**
     * 字符串转换为ascii
     * @param content
     * @return
     */
    public static String StringToASCII(String content){
        String result = "";
        int max = content.length();
        for (int i=0; i<max; i++){
            char c = content.charAt(i);
            int b = (int)c;
            result = result + b;
        }
        return result;
    }

    /**
     * ascii转字符串
     * @param i
     * @return
     */
    public static String ASCIIToString(int i){
        return Character.toString((char)i);
    }
}
