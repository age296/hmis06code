package com.example.test.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.test.conection.Customer;
import com.example.test.conection.userconection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      
      userconection uc = new userconection();
      for(Customer custom : uc.findAll()) {
    	  if (custom.getFirstName().equals(username) &&  custom.getPassword().equals(password)) {
              return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());      }
          } 
      throw new BadCredentialsException("Authentication failed");
       
    }

    @Override
    public boolean supports(Class<?>aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}