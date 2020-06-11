package com.wzy.risunking.global.config;

import com.wzy.risunking.RisunApplication;
import com.wzy.risunking.utils.ASCII;

import java.io.File;

/**
 * @description: 全局设置
 * @author: Wangzy
 * @create: 2018-10-24 15:40
 **/
public final class Config {
    //内部文件读取
    public static final String UPLOAD_PATH;//上传路径
    public static final String EXPORT_PATH ;//输出路径

    private static final String UPLOADDIR = "upload_dir";
    private static final String EXPORTDIR = "export_dir";

    public Config(){}

    static {
        UPLOAD_PATH = RisunApplication.baseAppDir + File.separator +UPLOADDIR+ File.separator;
        EXPORT_PATH = RisunApplication.baseAppDir + File.separator + EXPORTDIR + File.separator;
    }

    public static String getFileDirWithFileName(String fileName){
        if (fileName != null && !fileName.equals("")){
            String fileBaseDirFactor = fileName.substring(0,1).toLowerCase();
            return UPLOAD_PATH + ASCII.StringToASCII(fileBaseDirFactor) + File.separator;
        }
        return UPLOAD_PATH;
    }
}
