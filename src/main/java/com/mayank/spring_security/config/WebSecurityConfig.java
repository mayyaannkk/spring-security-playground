package com.mayank.spring_security.config;

import com.mayank.spring_security.repositories.UserRepository;
import com.mayank.spring_security.services.JpaUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new JpaUserDetailsService(userRepository);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
