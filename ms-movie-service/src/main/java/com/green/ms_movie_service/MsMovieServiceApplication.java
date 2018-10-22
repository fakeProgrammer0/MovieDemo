package com.green.ms_movie_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsMovieServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MsMovieServiceApplication.class, args);
    }
}
