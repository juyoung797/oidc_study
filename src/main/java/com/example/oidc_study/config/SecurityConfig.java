package com.example.oidc_study.config;

import com.example.oidc_study.service.CustomOidcUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public AuthenticationSuccessHandler oidcAuthenticationSuccessHandler () {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                log.info("OIDC 인증 성공!");
                log.info("인증 타입: {}", authentication.getClass().getSimpleName());
                log.info("Principal 타입: {}", authentication.getPrincipal().getClass().getSimpleName());

                response.sendRedirect("/dashboard");
            }
        };
    }
}
