package com.yh.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class YhServiceApplication {
    public static void main(String[] args) {
		new SpringApplicationBuilder(YhServiceApplication.class).web(true).run(args);
	}
}