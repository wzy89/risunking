package com.wzy.risunking.global.config;

import com.wzy.risunking.RisunApplication;
import com.wzy.risunking.utils.ASCII;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @description: 全局设置
 * @author: Wangzy
 * @create: 2018-10-24 15:40
 **/
@Configuration
public class Config {
    //内部文件读取
    private static final String UPLOAD_PATH;    //上传路径
    private static final String EXPORT_PATH ;   //输出路径
    private static final String UPLOADDIR = "upload_dir";
    private static final String EXPORTDIR = "export_dir";

    static {
        UPLOAD_PATH = RisunApplication.baseAppDir + File.separator +UPLOADDIR+ File.separator;
        EXPORT_PATH = RisunApplication.baseAppDir + File.separator + EXPORTDIR + File.separator;
    }

    public static String getFileDirWithFileName(String fileName){
        if (fileName != null && !fileName.equals("")){
            String fileBaseDirFactor = fileName.substring(0,1).toLowerCase();
            String filePath = UPLOAD_PATH + ASCII.StringToASCII(fileBaseDirFactor) + File.separator;
            File dir = new File(filePath);
            if (! dir.exists()){
                dir.mkdirs();
            }
            return filePath;
        }
        return UPLOAD_PATH;
    }

    @Value("${daily.word.url}")
    private String dailyWordUrl;
    public String getDailyWordUrl() { return dailyWordUrl; }
}
