package com.aggieStudy.AggieStudy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/**")
            .cors(cors -> cors.configure(http))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/**")).authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, "/**")).authenticated()
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, "/**")).authenticated()
            .anyRequest().authenticated()
        )
            .addFilterBefore(apiKeyFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public ApiKeyFilter apiKeyFilter() {
        return new ApiKeyFilter();
    }
}

@Component
class ApiKeyFilter extends OncePerRequestFilter {
    
    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        

        System.out.println("========================");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Received API Key: " + request.getHeader("X-API-Key"));
        System.out.println("Configured API Key: " + apiKey);
        System.out.println("Keys match: " + (request.getHeader("X-API-Key") != null && request.getHeader("X-API-Key").equals(apiKey)));
        System.out.println("========================");
        // Skip filter for GET requests
        if (request.getMethod().equals(HttpMethod.GET.name())) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestApiKey = request.getHeader("X-API-Key");

        if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}