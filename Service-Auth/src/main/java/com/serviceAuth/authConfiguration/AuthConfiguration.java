package com.serviceAuth.authConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.serviceAuth.exception.GLobalException;
import com.serviceAuth.filter.AuthFilter;
import com.serviceAuth.studentDetail.StudentDetail;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private GLobalException exception;
	
	@Autowired
	private StudentDetail studentDetail;
	
	@Autowired
	private AuthFilter filters;
	
	
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception
	{
		
		return super.authenticationManager();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(studentDetail);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors(c->c.disable())
		.csrf(cr->cr.disable())
		.authorizeHttpRequests(auth->auth.antMatchers("/auth/login","/auth/userRegister","/auth/forgot-password/**","/auth/send-otp/**","/auth/URLPassReset/**","/auth/URL-forgot-password/**").permitAll().anyRequest().authenticated())
		.exceptionHandling(ex->ex.authenticationEntryPoint(exception))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		http.addFilterBefore(filters, UsernamePasswordAuthenticationFilter.class);
	}
	
}
