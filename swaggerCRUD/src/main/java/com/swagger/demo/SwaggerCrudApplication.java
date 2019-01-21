package com.swagger.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(value="*")
@SpringBootApplication
public class SwaggerCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerCrudApplication.class, args);
	}
}
