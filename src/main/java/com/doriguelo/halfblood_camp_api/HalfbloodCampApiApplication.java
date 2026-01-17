package com.doriguelo.halfblood_camp_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class HalfbloodCampApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HalfbloodCampApiApplication.class, args);
	}
}