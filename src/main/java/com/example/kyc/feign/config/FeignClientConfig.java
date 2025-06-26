package com.example.kyc.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.auth-token}")
    private String authToken;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("accept", "application/json");
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Token", authToken);
            requestTemplate.header("authorisedkey", apiKey);
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
