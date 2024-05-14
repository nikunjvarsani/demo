package com.urekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FreeMarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeMarkerApplication.class, args);
	}

}
