package com.example.Transion.app.security;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class UserDetailsSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JWTAuthenticatinProvider jwtAuthenticationProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	protected JWTAuthenticationFilter buildJWTAuthenticationFilter() throws Exception {
		JWTAuthenticationFilter filter = new JWTAuthenticationFilter("/login");
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable().authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/login").permitAll()
        	.antMatchers(HttpMethod.GET, "/api/transionUser/save").permitAll()
        	.antMatchers(HttpMethod.POST, "/api/transionUser").permitAll()
        	.antMatchers(HttpMethod.GET, "/api/transionUser/activateAccount/{userId}").permitAll()
        	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        	.anyRequest().authenticated()
        	.and()
        	.addFilterBefore(buildJWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.addFilter(new JWTAuthorizationFilter(authenticationManager()));
    	//http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration customCorsConf = new CorsConfiguration();
    	customCorsConf.addAllowedOrigin("*");
    	customCorsConf.setAllowedMethods(Arrays.asList(
				HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
    	customCorsConf.addAllowedHeader("*");
    	customCorsConf.setAllowCredentials(true);
    	customCorsConf.setMaxAge(1800L);
	    source.registerCorsConfiguration("/**", customCorsConf);
	    
	    return source;
    }
    
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
