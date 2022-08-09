package com.example.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@SpringBootApplication
public class HelloSpringApplication extends ApplicationSecurityConfig {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

	}
}
