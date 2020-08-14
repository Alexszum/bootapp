package com.alena.litvinova.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
	
	 @Override
	 protected void configure(HttpSecurity htpp) throws Exception {
	        htpp
	                .authorizeRequests()
	                .antMatchers("/index").permitAll()
	                .antMatchers("/addbook").hasRole("EDITOR")
	                .antMatchers("/addwriter").hasRole("EDITOR")
	                .antMatchers("/admin").hasRole("EDITOR")
	                .and()
	                .authorizeRequests()
	                .antMatchers("/resources/**", "/registration").permitAll()
	                .anyRequest().permitAll()
	                .and()
	            .formLogin()
	                .loginPage("/signin")
	                .permitAll()
	                .and()
	            .logout()
	            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            	.logoutSuccessUrl("/signin")
	                .permitAll();
	    }

	    @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }
	}    	                
  


//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	 @Override
//	    protected void configure(HttpSecurity config) throws Exception {
//	        config
//	                .authorizeRequests()
//	                .antMatchers("/index").permitAll()
//	                .antMatchers("/addbook").hasRole("EDITOR")
//	                .antMatchers("/addwriter").hasRole("EDITOR")
//	                .antMatchers("/admin").hasRole("EDITOR")
//	                .and();
//	                .formLogin().loginPage("/auth").defaultSuccessUrl("/admin").permitAll()
//	                .and()
//	                .logout().logoutUrl("/logout").permitAll();
//	    }
//
//	    @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//	        builder.inMemoryAuthentication()
//	                .withUser("user").password("{noop}password").roles("EDITOR");
//	    }


