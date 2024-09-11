

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

                .csrf(csrf -> csrf.disable()) //csrf 비활성화
                .authorizeHttpRequests((requests) -> requests
                        // 권한 없이 접근 가능한 URL 경로 설정
                        .requestMatchers("/accounts", "/", "/register","/login").permitAll()
                        .requestMatchers("/mypage").hasRole("ADMIN") // ADMIN 권한이 필요한 경로
                        .anyRequest().authenticated()
                )
                // 로그인 폼 설정 (기본 제공 로그인 페이지 사용)
                .formLogin((form) -> form
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                )
                //최대 세션수 1개로 설정
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true) //동시 로그인 차단, false인 경우 기존 세션 만료(default)
                )
                // 로그아웃 설정
                .logout((logout) -> logout
                        .logoutSuccessUrl("/") // 로그아웃 후 리디렉션 URL 설정
                        .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                        .permitAll() // 로그아웃 URL에 대한 접근을 모든 사용자에게 허용
                );

        return http.build();
    }
    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




