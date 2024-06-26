package com.yuegou.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoggerConfig {

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger(this.getClass());
    }

}
