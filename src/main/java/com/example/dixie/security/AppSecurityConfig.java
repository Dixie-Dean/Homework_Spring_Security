package com.example.dixie.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests (authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/greet").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/farewell").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ban").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/delete-message").hasAnyRole("ADMIN", "MODERATOR")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withDefaultPasswordEncoder().username("User").password("1234").roles("USER").build());
        manager.createUser(
                User.withDefaultPasswordEncoder().username("Admin").password("1234").roles("ADMIN").build());
        manager.createUser(
                User.withDefaultPasswordEncoder().username("Moder").password("1234").roles("MODERATOR").build());

        manager.createUser(
                User.withDefaultPasswordEncoder().username("Frank").password("5678").roles("READ", "WRITE").build());
        manager.createUser(
                User.withDefaultPasswordEncoder().username("Thomas").password("5678").roles("READ").build());
        manager.createUser(
                User.withDefaultPasswordEncoder().username("Kevin").password("5678").roles("DELETE", "READ", "WRITE").build());

        return manager;
    }
}
