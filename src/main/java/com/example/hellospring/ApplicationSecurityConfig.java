package com.example.hellospring;

import com.example.hellospring.jwt.JwtTokenFilter;
import com.example.hellospring.jwt.JwtTokenUtil;
import com.example.hellospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;


    @Bean
    public PasswordEncoder encoderPassword(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> repository.findByEmail(username).
                orElseThrow(()->new UsernameNotFoundException("user "+username+" not found"))
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
        {
           response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        });
        http.authorizeHttpRequests().antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
