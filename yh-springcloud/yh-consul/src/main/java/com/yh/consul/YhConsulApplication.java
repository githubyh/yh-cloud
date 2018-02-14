package com.yh.consul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class YhConsulApplication {
    public static void main(String[] args) {
		new SpringApplicationBuilder(YhConsulApplication.class).web(true).run(args);
	}
}