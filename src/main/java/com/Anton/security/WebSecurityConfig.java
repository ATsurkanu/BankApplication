package com.Anton.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Anton
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Memory Authentication

        auth.inMemoryAuthentication().withUser("admin").password("admin")
                .roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("user").password("user")
                .roles("USER");


		/*// JDBC authentication
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username as principal, password as credentials, active from users where username=?")
				.authoritiesByUsernameQuery(
						"select username as principal, role as role from users_roles where username =?")
				.rolePrefix("ROLE_").passwordEncoder(new Md5PasswordEncoder());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/home", "/getAccount").hasRole("USER");
        http.authorizeRequests().antMatchers("/saveAccountOperation", "/createCustomer", "/createAccount")
                .hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");

    }

}
