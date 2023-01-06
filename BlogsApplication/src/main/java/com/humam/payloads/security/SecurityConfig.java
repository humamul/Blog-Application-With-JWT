package com.humam.payloads.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.humam.payloads.jwt.JwtEntryPoint;
import com.humam.payloads.jwt.JwtFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl uis;
	
	
	@Autowired
	private JwtEntryPoint entryPoint;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(uis);
		dao.setPasswordEncoder(getEncoder());
		return dao;
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtFilter authenticationJwtTokenFilter() {
	    return new JwtFilter();
	}
	@Bean
	public AuthenticationManager getManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain getSecurity(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(entryPoint).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/api/auth/**").permitAll()
//        .antMatchers("/category/**").permitAll()
        .anyRequest().authenticated();
		
		
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
