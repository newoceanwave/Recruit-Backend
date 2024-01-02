package com.smlikelion.webfounder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebfounderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfounderApplication.class, args);
	}

}
