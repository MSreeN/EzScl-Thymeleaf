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
                        .ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests((requests) -> requests.requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/courses")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        http.headers(headersConfigurer -> headersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

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
                .roles("user", "admin")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
