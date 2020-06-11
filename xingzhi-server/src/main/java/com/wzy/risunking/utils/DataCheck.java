package com.wzy.risunking.utils;

public class DataCheck {
    public static Boolean containsEmptyString(String ...args){
        Boolean isEmpty = false;
        for (String str : args) {
            if (str == null || str.equals("")){
                isEmpty = true;
            }
        }
        return isEmpty;
    }

}
