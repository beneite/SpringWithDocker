package com.beneite.SpringWithDocker;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Sprint boot REST API Simple Student Record Application (Docker)",
				description = "This application perform all the simple crud operations and also has been integrated with Docker",
				version = "v1.0",
				contact = @Contact(
						name = "Ashish Mishra",
						email = "im.ashish707@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "This is an external documentation",
				url = "http://localhost:8080/api/user/"
		)
)
public class SpringWithDockerApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWithDockerApplication.class, args);
	}

}
