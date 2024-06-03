package com.examen.bibliotheque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.examen.bibliotheque.guard.SanctionInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SanctionInterceptor sanctionInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sanctionInterceptor)
                .addPathPatterns("/emprunt/**")
                .excludePathPatterns("/admin/**");
    }
}