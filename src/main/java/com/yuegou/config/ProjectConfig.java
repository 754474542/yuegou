package com.yuegou.config;

import com.yuegou.controller.pretreatment.ControllerInterceptor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    @Autowired
    private ControllerInterceptor controllerInterceptor;
    @Autowired
    private Logger logger;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("配置静态资源放行");
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/static/pages/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] str = {"/pages/**"};
        registry.addInterceptor(controllerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(str)
                .excludePathPatterns("/resources/**");
        logger.info("拦截器启动");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("http://localhost:5173");
    }

}
