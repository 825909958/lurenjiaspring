package com.example.lurenjiaspring.aop.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;

@RestControllerAdvice
@Slf4j
public class UnifiedAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //若加了@ResponseNotIntercept 则该方法不用做统一的拦截
        AnnotatedElement annotatedElement = returnType.getParameterType();
//        ResponseNotIntercept annotation = AnnotationUtils.findAnnotation(annotatedElement, ResponseNotIntercept.class);
//        return annotation == null;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResult) return body;
        if(returnType.getExecutable().getAnnotatedReturnType().getType().getTypeName().equals("java.lang.String")){
            return body;
        }
        CommonResult<Object> objectCommonResult = new CommonResult<>(ResultCode.SUCCESS, body);
        //若未封装 则对其进行封装
        return objectCommonResult;
    }

    public static void main(String[] args) {
        CommonResult<Object> objectCommonResult = new CommonResult<>(ResultCode.SUCCESS, "1");
        System.out.println(objectCommonResult);
    }
}
