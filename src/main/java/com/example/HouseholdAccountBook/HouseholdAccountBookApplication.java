package com.example.HouseholdAccountBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HouseholdAccountBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseholdAccountBookApplication.class, args);
	}

}
