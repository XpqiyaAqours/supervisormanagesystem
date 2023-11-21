package com.smsys.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

//全局返回值拦截响应器
@RestControllerAdvice(basePackages = "com.smsys")
public class ResponseAdvice implements ResponseBodyAdvice {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 检查是否是文件下载请求，如果是，则直接返回原始的响应内容
        String requestPath = request.getURI().getPath();
        if ("/system/user/export".equals(requestPath)) {
            return body;
        }
        if ("/system/supervisor/export".equals(requestPath)) {
            return body;
        }
        if ("/system/supervisor/exportlist".equals(requestPath)) {
            return body;
        }
        if(body instanceof String){	// 如果Controller直接返回String的话，SpringBoot是直接返回，故我们需要手动转换成json。
            return objectMapper.writeValueAsString(ResultData.success(body));
        }
        if(body instanceof ResultData){	// 如果返回的结果是ResultData对象，直接返回即可。
            return body;
        }
        System.out.println("拦截响应成功");
        return ResultData.success(body);
    }
}