package com.sakamoto.frontend.config;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;

    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public boolean isUserAuthenticated() {
        return authenticationContext.isAuthenticated();
    }

    public boolean isAdmin() {
        return authenticationContext.isAuthenticated() && authenticationContext.getAuthenticatedUser(UserDetails.class).get().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public UserDetails getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class).orElse(null);
    }

    public void logout() {
        authenticationContext.logout();
    }
}