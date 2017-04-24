package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EnableScheduling
public class SpringbootApplication {

	/**
	 * Test number 7.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
