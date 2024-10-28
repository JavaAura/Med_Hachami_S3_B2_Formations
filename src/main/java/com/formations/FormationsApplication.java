package com.formations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class FormationsApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

        // Set system properties for Spring to read
        System.setProperty("spring.datasource.url","jdbc:postgresql://localhost:5432/formations");
        System.setProperty("spring.datasource.username","root");
        System.setProperty("spring.datasource.password","root");
        System.setProperty("spring.jpa.database-platform","org.hibernate.dialect.PostgreSQLDialect");

		
		SpringApplication.run(FormationsApplication.class, args);
	}

}
