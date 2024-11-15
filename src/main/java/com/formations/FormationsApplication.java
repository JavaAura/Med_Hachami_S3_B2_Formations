package com.formations;

import com.formations.model.Learner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(scanBasePackages = "com.formations")
public class FormationsApplication {

	public static void main(String[] args) {

		SpringApplication.run(FormationsApplication.class, args);
	}

}
