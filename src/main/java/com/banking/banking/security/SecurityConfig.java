package com.banking.banking.security;

import com.banking.banking.model.user.Permission;
import com.banking.banking.service.userLoginService.UserLoginService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

/**
 * SecurityConfig
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginService userLoginService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                // Only the customers can access their account
                .antMatchers("/my-accounts/**").hasAuthority(Permission.MY_ACCOUNT_READ.getPermission())
                // Only the employees can access the bank accounts list of all users in the
                // database
                .antMatchers("/accounts/**").hasAuthority(Permission.ACCOUNTS_READ.getPermission())
                // Only the accountants and the managers can generate report
                .antMatchers("/reports/**").hasAuthority(Permission.REPORT_EXPORT.getPermission())
                // Only the manager can modify the configuration of the system
                .antMatchers("/config/**").hasAuthority(Permission.CONFIG_WRITE.getPermission())
                // Authenticate other requests
                .anyRequest().authenticated()
                // Config the login form
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").successForwardUrl("/").defaultSuccessUrl("/").failureUrl("/login?error").permitAll().and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "images/**");
    }

}