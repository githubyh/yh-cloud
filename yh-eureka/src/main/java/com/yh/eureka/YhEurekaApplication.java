package com.yh.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class YhEurekaApplication {
    public static void main(String[] args) {

		new SpringApplicationBuilder(YhEurekaApplication.class).web(true).run(args);
    }
}