package com.spring.CrudSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args);

		System.out.println("Hello World");
	}

}
