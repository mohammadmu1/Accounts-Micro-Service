package com.micro.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

@EnableJpaAuditing(auditorAwareRef = "auditAware")
@OpenAPIDefinition(info = @Info(
		title = "Accounts MicroService Rest Api Doc",
		version = "v1",
		contact = @Contact(
				url = "https://www.linkedin.com/in/mohammad-mustafa1/"
		)
))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
