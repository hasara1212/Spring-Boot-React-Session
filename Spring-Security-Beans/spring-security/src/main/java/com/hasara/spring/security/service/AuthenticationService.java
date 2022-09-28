package com.hasara.spring.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Configuration
public class AuthenticationService implements UserDetailsService {



            @Bean
            public InMemoryUserDetailsManager userDetailsService() {
                UserDetails user = User.withDefaultPasswordEncoder()
                        .username("hasara")
                        .password("abc")
                        .roles("USER")
                        .build();
                return new InMemoryUserDetailsManager(user);
            }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}


