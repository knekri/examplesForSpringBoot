package com.example.springboot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@PropertySource("classpath:cors-mapping.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${origin.url:http://localhost:4200}")
    private String originUrl;
    @Value("${origin.maxage:3600}")
    private int originMaxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**")
                .allowedOrigins(originUrl)
                .maxAge(originMaxAge)
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
}
