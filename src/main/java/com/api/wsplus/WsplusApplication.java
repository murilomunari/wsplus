package com.api.wsplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.api.wsplus.entity")
@EnableJpaRepositories("com.api.wsplus.Repository")
public class WsplusApplication {
	public static void main(String[] args) {
		SpringApplication.run(WsplusApplication.class, args);
	}
}
