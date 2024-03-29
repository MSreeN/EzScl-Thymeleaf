package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;
import java.util.function.Function;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defalutSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(request ->
            request.requestMatchers("/courses").authenticated()
                    .anyRequest().permitAll()
                    )
            .formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
           return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        PasswordEncoder passwordEncoder = passwordEncoder();
        Function<String, String> pe = passwordEncoder::encode;
        UserDetails user = User.builder()
                .passwordEncoder(pe)
                .username("user")
                .password("1234")
                .roles("user")
                .build();
        UserDetails admin = User.builder()
                .passwordEncoder(pe)
                .username("admin")
                .password("1234")
                .roles("user", "admin")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
