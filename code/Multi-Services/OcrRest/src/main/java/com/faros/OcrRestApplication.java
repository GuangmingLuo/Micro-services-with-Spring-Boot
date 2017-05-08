package com.faros;

import com.faros.entity.Restaurant;
import com.faros.entity.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OcrRestApplication {


	public static void main(String[] args) {

		SpringApplication.run(OcrRestApplication.class, args);
	}
}
