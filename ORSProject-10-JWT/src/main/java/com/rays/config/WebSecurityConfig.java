package com.rays.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//jitne bhi config kiye hai unko spring container mai jake store karega
@Configuration

@EnableWebSecurity
//method level prr security apply karta hai
@EnableGlobalMethodSecurity(prePostEnabled = true)


//securities ko manage karna padega
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//crrf tocken browser tocken
		http.csrf().disable().authorizeRequests().antMatchers("/Auth/**", "/User/profilePic/**").permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}
}