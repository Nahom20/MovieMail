package com.org.moviemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.org.moviemail.entity")
@EnableJpaRepositories(basePackages = "com.org.moviemail.repository")
public class MoviemailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemailApplication.class, args);
	}

}
