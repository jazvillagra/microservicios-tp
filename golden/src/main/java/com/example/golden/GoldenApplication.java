package com.example.golden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableCircuitBreaker
// @EnableDiscoveryClient
// @EnableEurekaClient
public class GoldenApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenApplication.class, args);
	}

}
