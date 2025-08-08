package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // セキュリティ設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll() // ログイン・静的ファイルは認証不要
                        .anyRequest().authenticated() // それ以降は認証必須
                )
                .formLogin(form -> form
                        .loginPage("/login") // GETで表示するページ
                        .defaultSuccessUrl("/demo_list", true) // 成功後の遷移先
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // ログアウト後の画面
                        .permitAll());
        return http.build();
    }

    // テスト用のメモリ内ユーザー定義
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("testuser")
                        .password(passwordEncoder().encode("test"))
                        .roles("USER")
                        .build());
        return manager;
    }

    // パスワード暗号化
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
