package com.zadanie.zadanieweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zadanie.zadanieweb.dao")
public class ZadaniewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZadaniewebApplication.class, args);
	}

}
