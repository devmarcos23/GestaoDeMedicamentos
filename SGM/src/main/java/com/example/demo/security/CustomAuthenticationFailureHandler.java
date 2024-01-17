package com.example.demo.security;
/*@Author https://github.com/devmarcos23*/
import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	        AuthenticationException exception) throws IOException, ServletException {
	   if (exception instanceof BadCredentialsException) {
	        // Se a exceção for BadCredentialsException, redirecione com o parâmetro 'badCredentials'
	        getRedirectStrategy().sendRedirect(request, response, "/login?error=badCredentials");
	    } else {
	        super.onAuthenticationFailure(request, response, exception);
	    }
	}
}
