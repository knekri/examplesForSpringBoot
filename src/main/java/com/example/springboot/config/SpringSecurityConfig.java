package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

// https://www.devglan.com/spring-security/spring-boot-security-rest-basic-authentication
// https://www.baeldung.com/spring-security-basic-authentication

@Configuration
@EnableWebSecurity // enable Spring Security web security support
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	public SpringSecurityConfig(AuthenticationEntryPoint authEntryPoint) {
		this.authEntryPoint = authEntryPoint;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("john123")
			.password(passwordEncoder().encode("password"))
			.roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
					.authorizeRequests().anyRequest().authenticated()	
					.and()
					.httpBasic() // we want our every request to be authenticated using HTTP Basic authentication.
								//If authentication fails, the configured AuthenticationEntryPoint will be used to retry the authentication process.
					.authenticationEntryPoint(authEntryPoint);
		
//		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedMethods("GET", "POST", "PUT", "DELETE")
//						.allowedOrigins("*")
//						.allowedHeaders("*");
//			}
//		};
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
