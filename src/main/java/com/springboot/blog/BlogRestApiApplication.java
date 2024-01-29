package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BlogRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogRestApiApplication.class, args);
        System.out.println("                    *************************Blog Rest Api Started Successfully**************************");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
