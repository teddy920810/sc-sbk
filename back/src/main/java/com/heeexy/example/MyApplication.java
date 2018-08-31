package com.heeexy.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: hxy
 * @description: SpringBoot启动类
 * @date: 2017/10/24 11:55
 */
@SpringBootApplication
@MapperScan("com.heeexy.example.dao")
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.heeexy.*","com.heeexy.*"})
@EntityScan({"com.heeexy.*","com.heeexy.*"})
@EnableScheduling
public class MyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //SpringApplication application = new SpringApplication(MyApplication.class);
        //application.setBannerMode(Banner.Mode.OFF);
        //application.run(args);
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(MyApplication.class);
    }
}
