package com.formations;

import com.formations.model.Learner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class FormationsApplication {

	public static void main(String[] args) {


        Learner learner = new Learner();
        learner.setFirstName("med");
        System.out.println("ler"+learner.getFirstName());
		SpringApplication.run(FormationsApplication.class, args);
	}

}
