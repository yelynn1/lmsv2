package com.ci6225.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
    public Jwtauthfilter jwtAuthenticationFilter() {
        return new Jwtauthfilter();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
        .authorizeRequests()
        .antMatchers("/**")
        .permitAll()
        .antMatchers("/instructor/login")
        .permitAll()
        .antMatchers("/courses")
        .permitAll()
        .antMatchers("/instructor").permitAll()
        .antMatchers("/student/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable()
        .addFilterBefore(jwtAuthenticationFilter(), 
                UsernamePasswordAuthenticationFilter.class);
        
    }
}
