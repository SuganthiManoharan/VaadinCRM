package com.example.application.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;

    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public UserDetails getAuthenticatedUser() {
        System.out.println("authenticated");
        return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
    }

    public void logout() {
        authenticationContext.logout();
    }

}