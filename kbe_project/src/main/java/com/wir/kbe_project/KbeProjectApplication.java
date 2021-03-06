package com.wir.kbe_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;


@SpringBootApplication
@EnableSwagger2
public class KbeProjectApplication {

	public static void main(String... args) {
		SpringApplication.run(KbeProjectApplication.class, args);
	}

}
