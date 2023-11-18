package com.smsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.smsys.system.mapper")
public class WebBasicApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebBasicApplication.class, args);
    }
    //外部Tomcat启动需要使用方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebBasicApplication.class);
    }

}
