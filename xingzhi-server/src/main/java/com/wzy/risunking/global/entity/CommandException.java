package com.wzy.risunking.global.entity;

/**
 * @description: 全局运行时异常
 * @author: Wangzy
 * @create: 2018-10-23 10:28
 **/
public class CommandException extends RuntimeException{
    private int exceptionCode;

    public CommandException(int exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public CommandException(int exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode(){
        return this.exceptionCode;
    }
}
