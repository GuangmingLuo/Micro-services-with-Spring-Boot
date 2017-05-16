package com.faros.OcrEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class OcrEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrEurekaServerApplication.class, args);
	}
}
