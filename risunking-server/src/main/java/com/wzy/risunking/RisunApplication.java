package com.wzy.risunking;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;

@MapperScan({"com.wzy.risunking.common.dao","com.wzy.risunking.resource.dao"})
@SpringBootApplication(scanBasePackages = {"com.wzy.risunking"})
public class RisunApplication {

    public static String baseAppDir;
    public static Logger logger = LoggerFactory.getLogger(RisunApplication.class);

    public static void main(String[] args) {
        if (!initRisunApplication()){
            logger.error("初始化失败");
        }else {
            SpringApplication.run(RisunApplication.class, args);
        }
    }
    public static boolean initRisunApplication(){
        boolean isSuccess = false;
        File directory	= new File(".");
        try {
            baseAppDir = directory.getCanonicalPath();
            isSuccess = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
