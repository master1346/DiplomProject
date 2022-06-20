package com.example.Cloudstorage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

//аш фильтр JWTFilter перед фильтром UsernamePasswordAuthenticationFilter.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JWTFilter jwtFilter;

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource, JWTFilter jwtFilter ) {
        this.dataSource = dataSource;
        this.jwtFilter = jwtFilter;
    }
    // еще поля и методы

    // Бин AuthenticationManager используется в контроллере аутентификации (см. выше)
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    // тут отключаем сессии и добавляем фильтр JWTFilter
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .and().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery(
                        "select login, password, 'true' from my_user " +
                                "where login=?")
                .authoritiesByUsernameQuery(
                        "select login, authority from my_user " +
                                "where login=?");
    }




}