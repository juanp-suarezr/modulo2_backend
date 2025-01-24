package com.mf.mf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MfApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfApplication.class, args);
	}

}
