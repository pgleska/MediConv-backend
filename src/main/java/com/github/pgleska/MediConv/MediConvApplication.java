package com.github.pgleska.MediConv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.github.pgleska.MediConv.daos")
public class MediConvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediConvApplication.class, args);
	}

}
