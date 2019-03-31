package com.example.springboot.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;


// If authentication is successful, the resulting Authentication object will beplaced into the SecurityContextHolder. 
// If authentication fails and ignoreFailure is false (thedefault), an AuthenticationEntryPoint implementation is called (this class)
@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException, ServletException {
		
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

	@Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("spring boot basic auth");
        super.afterPropertiesSet();
    }

}
