package com.wzy.risunking.global.aop;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 全局异常处理
 * @author: Wangzy
 * @create: 2018-10-23 10:25
 **/
@ControllerAdvice(annotations = {RestController.class,Controller.class})
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object handleError(Exception e) {
        Response response = new Response();
        response.setType(e.getStackTrace()[0].getMethodName()+"_ACK");
        if (e instanceof CommandException){
            CommandException cmdException = (CommandException)e;
            response.setCode(cmdException.getExceptionCode());
        } else {
            response.setCode(Response.INNER_ERROR);
        }
        if (e.getMessage()==null){
            response.setOther(Response.EXCEPTIOMKEY,"未知错误");
        }else {
            response.setOther(Response.EXCEPTIOMKEY,e.getMessage());
        }
        return response;
    }
}
