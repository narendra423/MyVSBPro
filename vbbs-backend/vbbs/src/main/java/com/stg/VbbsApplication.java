package com.stg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SwaggerDefinition
@SpringBootApplication
public class VbbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VbbsApplication.class, args);
	}

}
