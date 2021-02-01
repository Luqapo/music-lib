package com.progspringinit.musiclib1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.progspringinit.musiclib1.services.MyUserDetailsService;
import com.progspringinit.musiclib1.config.JwtRequestFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.cors().and().csrf().disable();
         
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/authenticate")
                .permitAll()
                .anyRequest().authenticated()
        		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(myUserDetailsService);
	 }
}
