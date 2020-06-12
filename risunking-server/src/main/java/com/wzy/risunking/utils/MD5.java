package com.wzy.risunking.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private Logger logger = LoggerFactory.getLogger(MD5.class);
    private String md5_32;
    private String md5_16;
    public MD5(String sourceStr){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            String result = buf.toString();
            md5_32=result;
            md5_16= buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getStackTrace().toString());
        }
    }
    public String get16(){
        return md5_16;
    }
    public String get32(){
        return md5_32;
    }
}

