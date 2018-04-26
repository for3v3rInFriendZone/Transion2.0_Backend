package com.example.Transion.app.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Transion.app.model.TransionUser;
import com.example.Transion.app.service.TransionUserService;

@Component
public class JWTAuthenticatinProvider implements AuthenticationProvider{

	private final BCryptPasswordEncoder encoder;
	
	@Autowired
	TransionUserService userService;
	
	@Autowired
    public JWTAuthenticatinProvider(final BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        TransionUser user = userService.findByUsername(username);
        
        if(user == null || !user.getIsActivated() || !encoder.matches(password, user.getPassword())) {
        	return null;
        }
        
        List<GrantedAuthority> authorities = user.getAuthorities()
        											.stream()
        											.map(authority -> new SimpleGrantedAuthority(authority))
        											.collect(Collectors.toList());
        
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
