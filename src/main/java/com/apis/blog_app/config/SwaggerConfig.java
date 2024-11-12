package com.apis.blog_app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customConfig() {
		return new OpenAPI()
				.info(
						new Info().title("Blogging application API documentation"))
				.servers(
						List.of(new Server().url("http://localhost:8080").description("Local")))
				.tags(List.of(new Tag().name("Login APIs"),
						new Tag().name("User APIs"),
						new Tag().name("Category APIs"),
						new Tag().name("Post APIs"),
						new Tag().name("Comment APIs")))
				.addSecurityItem(
						new SecurityRequirement().addList("bearerAuth")) 
				.components(
						new Components().addSecuritySchemes ("bearerAuth", 
								new SecurityScheme()
									.type(SecurityScheme.Type.HTTP)
									.scheme ("bearer")
									.bearerFormat("JWT")
									.in(SecurityScheme.In.HEADER)
									.name("Authorization")
						));
	}
}
