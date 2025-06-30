package com.example.backend_auth0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendAuth0Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendAuth0Application.class, args);
	}

}
