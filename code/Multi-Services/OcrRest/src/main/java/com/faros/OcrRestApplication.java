package com.faros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OcrRestApplication {


	public static void main(String[] args) {
        if(args.length>0){
            System.getProperties().put( "server.port", args[0] );
            System.getProperties().put( "management.port", args[1] );
        }
		SpringApplication.run(OcrRestApplication.class, args);
	}
}

