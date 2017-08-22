package com.yh.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class YhConfigApplication {
    public static void main(String[] args) {
		new SpringApplicationBuilder(YhConfigApplication.class).web(true).run(args);
	}
}
