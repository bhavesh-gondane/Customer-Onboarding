package com.example.kyc.feign.config;

import com.example.kyc.util.JwtUtil;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig implements RequestInterceptor{
    @Value("${api.key}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate template){
        template.header("accept", "application/json");
        template.header("Content-Type", "application/json");
        template.header("Token", JwtUtil.getToken());
        template.header("authorisedkey", apiKey);
    }
}
