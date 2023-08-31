package com.khanhvo.expensetracking.security;

import com.khanhvo.expensetracking.auth.exceptionhandler.UnauthenticatedHandler;
import com.khanhvo.expensetracking.auth.exceptionhandler.UnauthorizedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
//    private final ExceptionHandlerFilter exceptionHandlerFilter;

    private final AuthenticationProvider authenticationProvider;

    private final UnauthenticatedHandler unauthenticatedHandler;
    private final UnauthorizedHandler unauthorizedHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeRequests(auth -> auth
//                        .requestMatchers("/api/test/**").hasAnyAuthority("ADMIN", "USER")
//                        .requestMatchers("/api/group/**").permitAll()
//                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().permitAll())
//                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exc) -> exc
                        .authenticationEntryPoint(unauthenticatedHandler)
                        .accessDeniedHandler(unauthorizedHandler));
//                .addFilterBefore(exceptionHandlerFilter, FilterSecurityInterceptor.class);
        return http.build();
    }

}
