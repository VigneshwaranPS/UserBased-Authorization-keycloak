package com.keycloak.UserIdBasedAuthentication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
		name = "Keycloak",
		openIdConnectUrl = "http://127.0.0.1:8081/realms/DemoRealm/.well-known/openid-configuration",
		scheme = "bearer",
		type = SecuritySchemeType.OPENIDCONNECT,
		in = SecuritySchemeIn.HEADER
)
public class UserIdBasedAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserIdBasedAuthenticationApplication.class, args);
	}

}
