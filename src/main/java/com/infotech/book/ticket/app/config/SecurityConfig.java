package com.infotech.book.ticket.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/api/tickets/**")
                .hasAnyRole("admin", "user")
                .and().authorizeRequests().antMatchers("/api/admin/**")
                .hasAnyRole("admin")
                .and()
                .formLogin();

        //Another way of implementing this
		/*http.csrf().disable().authorizeRequests().antMatchers("/api/tickets/**")
				.hasAnyRole("admin","user")
				.and()
				.formLogin();
		http.csrf().disable().authorizeRequests().antMatchers("/api/admin/**")
				.hasAnyRole("admin")
				.and()
				.formLogin();*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("sean").password("{noop}pass@123").roles("user")
                .and().withUser("martin").password("{noop}pass@123").roles("user", "admin");
    }
}
