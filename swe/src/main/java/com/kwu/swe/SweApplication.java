package com.kwu.swe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SweApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweApplication.class, args);
	}

}
