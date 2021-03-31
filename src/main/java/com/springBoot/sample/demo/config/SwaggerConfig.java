package com.springBoot.sample.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
	@Value("${springdoc.swagger-ui.enabled}")
	Boolean swaggerUiEnable;
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger Document Title")
				.description("文件說明").version("1.0").build();
	}
	
	@Bean
	public Docket docket() {
		// exculd path use PathSelectors.regex("(?!/model).+")
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(swaggerUiEnable)
				.select().apis(RequestHandlerSelectors.basePackage("com.springBoot.sample.demo.controller"))
				.paths(PathSelectors.any()).build();
	}
}
