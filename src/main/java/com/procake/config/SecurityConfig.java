package com.procake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.procake.exceptions.handler.ResponseEntityCustomizadaExcpetionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private FilterToken filter;

	public SecurityConfig(FilterToken filter) {
		this.filter = filter;
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests()

				.requestMatchers("/api/v1/auth/**").permitAll()
				.requestMatchers("/acess-denied/**").permitAll()
				.requestMatchers("/acess-denied").permitAll()

				.requestMatchers(HttpMethod.GET, "/api/v1/usuarios/**").hasAnyAuthority("ROLE_USUARIO", "ROLE_USUARIO_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/usuarios/**").hasAnyAuthority("ROLE_USUARIO")
				.requestMatchers(HttpMethod.PUT, "/api/v1/usuarios/**").hasAnyAuthority("ROLE_USUARIO")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/grupos/**").hasAnyAuthority("ROLE_GRUPO", "ROLE_GRUPO_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/grupos/**").hasAnyAuthority("ROLE_GRUPO")
				.requestMatchers(HttpMethod.PUT, "/api/v1/grupos/**").hasAnyAuthority("ROLE_GRUPO")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/grupos/**").hasAnyAuthority("ROLE_GRUPO")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/insumos/**").hasAnyAuthority("ROLE_INSUMO", "ROLE_INSUMO_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/insumos/**").hasAnyAuthority("ROLE_INSUMO")
				.requestMatchers(HttpMethod.PUT, "/api/v1/insumos/**").hasAnyAuthority("ROLE_INSUMO")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/insumos/**").hasAnyAuthority("ROLE_INSUMO")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/estoque/**").hasAnyAuthority("ROLE_ESTOQUE", "ROLE_ESTOQUE_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/estoque/**").hasAnyAuthority("ROLE_ESTOQUE")
				.requestMatchers(HttpMethod.PUT, "/api/v1/estoque/**").hasAnyAuthority("ROLE_ESTOQUE")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/estoque/**").hasAnyAuthority("ROLE_ESTOQUE")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/marcas/**").hasAnyAuthority("ROLE_MARCA", "ROLE_MARCA_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/marcas/**").hasAnyAuthority("ROLE_MARCA")
				.requestMatchers(HttpMethod.PUT, "/api/v1/marcas/**").hasAnyAuthority("ROLE_MARCA")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/marcas/**").hasAnyAuthority("ROLE_MARCA")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/fornecedores/**").hasAnyAuthority("ROLE_FORNECEDORES", "ROLE_FORNECEDORES_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/fornecedores/**").hasAnyAuthority("ROLE_FORNECEDORES")
				.requestMatchers(HttpMethod.PUT, "/api/v1/fornecedores/**").hasAnyAuthority("ROLE_FORNECEDORES")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/fornecedores/**").hasAnyAuthority("ROLE_FORNECEDORES")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/clientes/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_CLIENTE_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/clientes/**").hasAnyAuthority("ROLE_CLIENTE")
				.requestMatchers(HttpMethod.PUT, "/api/v1/clientes/**").hasAnyAuthority("ROLE_CLIENTE")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/clientes/**").hasAnyAuthority("ROLE_CLIENTE")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/nota-fiscal/**").hasAnyAuthority("ROLE_NOTA_FISCAL", "ROLE_NOTA_FISCAL_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/nota-fiscal/**").hasAnyAuthority("ROLE_NOTA_FISCAL")
				.requestMatchers(HttpMethod.PUT, "/api/v1/nota-fiscal/**").hasAnyAuthority("ROLE_NOTA_FISCAL")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/nota-fiscal/**").hasAnyAuthority("ROLE_NOTA_FISCAL")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/lancamentos/**").hasAnyAuthority("ROLE_LANCAMENTOS", "ROLE_LANCAMENTOS_LEITURA")
				.requestMatchers(HttpMethod.POST, "/api/v1/lancamentos/**").hasAnyAuthority("ROLE_LANCAMENTOS")
				.requestMatchers(HttpMethod.PUT, "/api/v1/lancamentos/**").hasAnyAuthority("ROLE_LANCAMENTOS")
				.requestMatchers(HttpMethod.DELETE, "/api/v1/lancamentos/**").hasAnyAuthority("ROLE_LANCAMENTOS")
				
				.requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAnyAuthority("ROLE_LEITURA_ROLES")
				
				.anyRequest().denyAll()
				.and().exceptionHandling().accessDeniedHandler(new ResponseEntityCustomizadaExcpetionHandler()).and()
				.cors().and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}
}