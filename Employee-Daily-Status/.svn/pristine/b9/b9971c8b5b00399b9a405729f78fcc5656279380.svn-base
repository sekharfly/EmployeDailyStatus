package com.emp.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/",
	                            "/js/**",
	                            "/css/**",
	                            "/img/**",
	                            "/webjars/**",
	                            "/h2/**",
	                            "/console/**",
	                            "/page-signup",
	                            "/signup").permitAll()
               /* .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll()*/
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
                
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("sekharfly@fly1.com").password("password").roles("USER")
                .and()
                .withUser("sekharfly@fly.com").password("123456789").roles("ADMIN");
    }

  }