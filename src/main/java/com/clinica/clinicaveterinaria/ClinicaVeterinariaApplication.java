package com.clinica.clinicaveterinaria;

import com.clinica.clinicaveterinaria.security.JwtAuthorization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ClinicaVeterinariaApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ClinicaVeterinariaApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {


		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JwtAuthorization(),
							UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/user/login").permitAll()
					.antMatchers(HttpMethod.POST, "/users").permitAll();
					// .anyRequest().authenticated();
		}
	}
}
