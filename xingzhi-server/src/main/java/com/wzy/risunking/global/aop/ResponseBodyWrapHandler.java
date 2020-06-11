package com.wzy.risunking.global.aop;

import com.wzy.risunking.global.entity.Response;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

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
        Response response = new Response();
        response.setType(returnType.getMethod().getName() + "_ACK");
        if(null == returnValue) {
            response.setCode(Response.INNER_ERROR);
            response.setOther(Response.EXCEPTIOMKEY,"返回结果为空");
        }else {
            if(returnValue instanceof Response) {
                response = (Response) returnValue;
            }else {
                response.setResult(returnValue);
            }
        }
        delegate.handleReturnValue(response, returnType, mavContainer, webRequest);
    }
}
