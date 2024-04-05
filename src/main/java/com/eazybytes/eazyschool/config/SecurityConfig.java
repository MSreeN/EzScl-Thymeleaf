package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.util.function.Function;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defalutSecurityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg")
//                .ignoringRequestMatchers(PathRequest.toH2Console()));
//        http.authorizeHttpRequests(request ->
//            request.requestMatchers("/courses").authenticated()
//                    .requestMatchers("/dashboard").authenticated()
//                    .anyRequest().permitAll()
//                    .requestMatchers(PathRequest.toH2Console()).permitAll()
//                    );
//            http.formLogin(loginConfig -> loginConfig.loginPage("/login").permitAll()
//                    .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true")
//                    .permitAll()
//
//            );
//            http.logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/login?logout=true")
//                    .invalidateHttpSession(true));
//            http.httpBasic(Customizer.withDefaults());
//           return http.build();
//    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/saveMsg"))
                        .ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests((requests) -> requests.requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers("/displayMessages").hasRole("admin")
                        .requestMatchers("/closeMsg/**").hasRole("admin")
                        .requestMatchers("").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").authenticated()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        Function<String, String> pe = passwordEncoder::encode;
//        UserDetails user = User.builder()
//                .passwordEncoder(pe)
//                .username("user")
//                .password("1234")
//                .roles("user")
//                .build();
//        UserDetails admin = User.builder()
//                .passwordEncoder(pe)
//                .username("admin")
//                .password("1234")
//                .roles("user", "admin")
//                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .roles("user")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
