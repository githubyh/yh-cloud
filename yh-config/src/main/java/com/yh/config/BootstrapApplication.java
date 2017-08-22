package com.yh.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootstrapApplication {
    public static void main(String[] args) {
		new SpringApplicationBuilder(BootstrapApplication.class).web(true).run(args);
	}
}