package com.johnysoft.softwareplant_recruitment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Security configuration
 * <p>
 * required change encoding algorithm, for test app it is enough
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .formLogin()
                .successHandler((request, response, authentication) -> response.setStatus(ACCEPTED.value()))
                .failureHandler((request, response, exception) -> response.setStatus(UNAUTHORIZED.value()))
                .and()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.setStatus(UNAUTHORIZED.value()))
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("test")
                .authorities("test", "test2")
                .password("test")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .and();
    }
}
