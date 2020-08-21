package com.example.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Oauth2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServiceApplication.class, args);
	}

}
