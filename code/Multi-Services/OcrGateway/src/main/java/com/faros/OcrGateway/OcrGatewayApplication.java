package com.faros.OcrGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class OcrGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrGatewayApplication.class, args);
	}
}
