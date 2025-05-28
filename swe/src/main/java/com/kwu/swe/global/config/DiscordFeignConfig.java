package com.kwu.swe.global.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DiscordFeignConfig{

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Content-Type", "application/json;charset=UTF-8");
    }
}
