package com.coviam.flightsearch.rest.web.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class Beans {

    @Bean
    public Mapper dozerMapper(){
        return new DozerBeanMapper();
    }
}
