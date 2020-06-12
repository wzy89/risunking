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
        //返回报错code
        if (e instanceof CommandException){
            CommandException cmdException = (CommandException)e;
            response.setCode(cmdException.getExceptionCode());
        } else {
            response.setCode(Response.INNER_ERROR);
        }
        //返回报错message
        if (e.getMessage()==null){
            response.setMsg("未知错误");
        }else {
            response.setMsg(e.getMessage());
        }
        //报错时返回报错methodName
        response.setOther(Response.ERRORSTACKTRACE,e.getStackTrace()[0].getMethodName()+"_ACK");
        return response;
    }
}
