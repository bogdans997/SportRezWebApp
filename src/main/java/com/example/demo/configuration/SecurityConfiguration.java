package com.example.demo.configuration;

import com.example.demo.model.Role;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Role[] roles = Role.values();
        final String ADMIN = roles[0].getCode();
        final String PLAYER = roles[1].getCode();
        final String GUEST = roles[2].getCode();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/results").permitAll()
                .antMatchers(HttpMethod.POST, "/results").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/results/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/results/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/results/info" , "/results/info/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/results/search").permitAll()
                .antMatchers(HttpMethod.GET, "/sports", "/sports/*").hasAnyRole(ADMIN, PLAYER)
                .antMatchers(HttpMethod.POST, "/sports").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/sports/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/sports/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/teams", "/teams/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.GET, "/teams/info/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.POST, "/teams").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/teams/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/teams/*").hasAuthority(ADMIN)
                .antMatchers("/user", "/user/", "/user/search*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/user/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/user/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.POST,"/user").hasAnyAuthority(ADMIN, PLAYER)
                .antMatchers(HttpMethod.POST, "/user/team").hasAuthority(ADMIN)
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/user/register", "/user/register/")
                .antMatchers("/user/change-password/**")
                .antMatchers("/user/reset-password/**")
                .antMatchers("/api-docs", "/api-docs.yaml", "/api-docs/**");
    }


}
