package com.shiny.tenant.config;

import com.shiny.tenant.interceptor.AllInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class LxWebAppConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
