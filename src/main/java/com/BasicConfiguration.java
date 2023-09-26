package com;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;


@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfiguration {
    
	@Autowired
	private DataSource dataSource;

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return
		 new BCryptPasswordEncoder();
	}


	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// neste método que vamos tratar os usuários

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from funcionario where email=?")
				.authoritiesByUsernameQuery(
						"select funcionario.email as username, papel.nome as authority from permissoes inner join funcionario on funcionario.id=permissoes.funcionario_id inner join papel on permissoes.papel_id=papel.id where funcionario.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());

	}

/* 
     @Override
	protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()
		.antMatchers("/administrativo/entrada/**").hasAuthority("gerente")
		.antMatchers("/administrativo/**").hasAuthority("gerente", "vendedor")
		.and().formLogin()
		.loginPage("/login").permitAll().and().logout()
		.logoutRequestMatcher(new AntPathMatcher("/logout")).logoutSuccessUrl("/administrativo").and()
		.execeptionHandLing().accessDeniedPage("/negado");

	}
	 */



}

