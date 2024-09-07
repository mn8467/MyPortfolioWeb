
/*
package com.example.toyproject1_wst.Config.SpringSecurity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(); // 커스텀 핸들러 반환
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        String generatedPassword = "{noop}gimpo123"; // ...;
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password(generatedPassword).roles("ADMIN").build());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 권한 없이 접근 가능한 URL 경로 설정
                        .requestMatchers("/accounts", "/home", "/sign-up").permitAll()
                        // 그 외의 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                // 로그인 폼 설정 (기본 제공 로그인 페이지 사용)
                .formLogin((form) -> form
                        .loginPage("/login")  // 커스텀 로그인 페이지 설정 가능
                        .permitAll()
                )
                // 로그아웃 설정
                .logout((logout) -> logout
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }
}


 */