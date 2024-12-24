package com.example.project3bankw10day3.Config;

import com.example.project3bankw10day3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/register-customer").permitAll()
                .requestMatchers("/api/v1/customer/get").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/deposit").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/withdraw").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/transfer-funds").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/block-my-account").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/get").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/register-employee").permitAll()
                .requestMatchers("/api/v1/employee/get").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/delete").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/activation-by-employee").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/user/activation-by-admin").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/user/get-all-users").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/user/block-account-by-admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
        return http.build();
    }
}
