package com.wzy.risunking.global.aop;

import com.wzy.risunking.global.entity.Response;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.*;

/**
 * @description: 返回值统一处理
 * @author: Wangzy
 * @create: 2018-10-23 14:22
 **/
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        Response response;
        if(null == returnValue) {
            response = new Response();
            response.setCode(Response.EMPTY_RESULT);
            response.setMsg(Response.EMPTY_RESULT_MSG);
            response.setResult("");
        }else {
            if(returnValue instanceof Response) {
                response = (Response) returnValue;
            }else if(returnValue instanceof Map) {
                response = new Response();
                if (((Map) returnValue).isEmpty()){
                    response.setCode(Response.EMPTY_RESULT);
                    response.setMsg(Response.EMPTY_RESULT_MSG);
                    response.setResult("{}");
                }else {
                    response.setResult(returnValue);
                }
            }else if (returnValue instanceof Collection){
                response = new Response();
                if (((Collection) returnValue).isEmpty()){
                    response.setCode(Response.EMPTY_RESULT);
                    response.setMsg(Response.EMPTY_RESULT_MSG);
                    response.setResult("[]");
                }else {
                    response.setResult(returnValue);
                }
            }else {
                response = new Response();
                response.setResult(returnValue);
            }
        }
        delegate.handleReturnValue(response, returnType, mavContainer, webRequest);
    }
}
