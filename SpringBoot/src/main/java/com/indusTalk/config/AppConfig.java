package com.indusTalk.config;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
       http.authorizeHttpRequests(Authorize ->Authorize.requestMatchers("/api/**").authenticated()
        .anyRequest().permitAll())
        .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .cors(cors->cors.configurationSource(corsConfigurationSource()));
    
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource(){
        return new CorsConfigurationSource(){

            public CorsConfiguration getCorsConfiguration(HttpServletRequest request){

                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList(
                    "hhtp://localhost:8086/"));
                    cfg.setAllowedMethods(Collections.singletonList("*"));
                    cfg.setAllowCredentials(true);
                    cfg.setAllowedHeaders(Collections.singletonList("*"));
                    cfg.setExposedHeaders(Arrays.asList(
                    "Authorization"));
                    cfg.setMaxAge(3600L);
              return cfg;
            }

        };
        
    }

    @Bean
    PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
    }
}

// .httpBasic().and()

// http.sessionManagement( management -> management.sessionCreationPolicy(
//     SessionCreationPolicy.STATELESS)).authorizeHttpRequests(Authorize ->Authorize.requestMatchers("/api/**").authenticated()
//     .anyRequest().permitAll()).httpBasic().and().csrf(csrf -> csrf.disable());