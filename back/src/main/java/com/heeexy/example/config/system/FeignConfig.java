package com.heeexy.example.config.system;

import com.heeexy.example.feign.WxFeign;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${application.wx.url}")
    private String baseUrl;

    @Bean
    WxFeign wxFeign() throws InterruptedException {
        return HystrixFeign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .setterFactory((target, method) ->
                        new SetterFactory.Default().create(target, method).
                                andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter().
                                        withExecutionTimeoutInMilliseconds(50000)))
                .target(WxFeign.class, this.baseUrl);
    }
}
