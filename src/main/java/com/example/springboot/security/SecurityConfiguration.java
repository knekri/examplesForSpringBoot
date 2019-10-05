package com.example.springboot.security;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springboot.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private MyUserDetailsService userDetailsService;
	private UserRepository userRepository;

	@Autowired
	public SecurityConfiguration(MyUserDetailsService userDetails, UserRepository userRepository) {
		this.userDetailsService = userDetails;
		this.userRepository = userRepository;
	}
	

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		
    	http
    		 // remove csrf and state in session because in jwt we do not need them (useful in case of form based auth)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // add jwt filters (1. authentication, 2. authorization)
            .addFilter(new JwtAuthenticationFilter(authenticationManager())) // the order is vital
            .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
            .authorizeRequests()
            // configure access rules
            .antMatchers(HttpMethod.POST, "/login").permitAll()
//            .antMatchers("/api/public/management/*").hasRole("MANAGER")
//            .antMatchers("/api/public/admin/*").hasRole("ADMIN")
            .anyRequest().authenticated();
    }


	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
