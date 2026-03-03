package com.eightyeightdays.jobs_au_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JobsAuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsAuBackendApplication.class, args);
	}

}
