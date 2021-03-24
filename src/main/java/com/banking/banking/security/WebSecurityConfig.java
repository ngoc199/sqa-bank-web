package com.banking.banking.security;

import com.banking.banking.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    /**
     * User authentication configuration
     * 
     * @author Ngoc
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // The in memory authentication will be remove in production
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder()).and().inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("ADMIN");
    }

    /**
     * User authorization configuration
     * 
     * @author Ngoc
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/", "/index", "/css/**", "/js/**").permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/")
                .failureUrl("/login?error").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and().exceptionHandling()
                .accessDeniedPage("/403");
    }

    /**
     * Password encoder used in authentication request
     * 
     * @author Ngoc
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Can't use SHA-256 as the SRS version 0.2 because the SHA-256 is deprecated
        // which can cause insecurity
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return passwordEncoder;
    }
}
