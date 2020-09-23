package com.wzy.risunking.utils;

public class DataCheck {
    public static boolean containsEmptyString(String... args) {
        boolean isEmpty = false;
        for (String str : args) {
            isEmpty = isEmptyString(str);
            if (isEmpty) {
                break;
            }
        }
        return isEmpty;
    }

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }
}
