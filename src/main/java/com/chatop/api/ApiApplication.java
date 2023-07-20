package com.chatop.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.chatop.api.configuration.RsaKeyProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@OpenAPIDefinition
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
