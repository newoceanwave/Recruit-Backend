package com.smlikelion.webfounder.global.config;

import com.smlikelion.webfounder.security.AuthArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthArgumentResolver authArgumentResolver() {
        return new AuthArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        System.out.println("WebConfig is invoked!");
        resolvers.add(authArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}
