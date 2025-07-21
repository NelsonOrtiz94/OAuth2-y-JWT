package com.lta.security.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1")
                        .password("{noop}password") // {noop} indicates no password encoder
                        .authorities("USER")
                        .build(),
                User.withUsername("user2")
                        .password("{noop}password") // {noop} indicates no password encoder
                        .authorities("USER")
                        .build(),
                User.withUsername("user3")
                        .password("{noop}password") // {noop} indicates no password encoder
                        .authorities("USER", "ADMIN")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/token/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(resourceServer -> resourceServer
                        // Aquí ya no invocamos el deprecated jwt(),
                        // sino jwt(...) con un Customizer
                        .jwt(withDefaults())
                )
                .httpBasic(withDefaults());

        return http.build();
    }

}
