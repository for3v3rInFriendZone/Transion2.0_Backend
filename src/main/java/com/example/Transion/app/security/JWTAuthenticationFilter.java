package com.example.Transion.app.security;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.Transion.app.model.TransionUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	protected JWTAuthenticationFilter(String requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	
	static final long EXPIRATIONTIME = 432_000_000; // 5 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer ";
	static final String HEADER_STRING = "Authorization";

	private AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		 try {
			 TransionUser creds = new ObjectMapper()
					 .readValue(req.getInputStream(), TransionUser.class);
	            
			 UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword());
	            
			 Authentication a = this.getAuthenticationManager().authenticate(upat);
	            
			 return a;
		 } catch (IOException e) {
			 throw new RuntimeException(e);
		 }
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

    	Claims claims = Jwts.claims().setSubject(auth.getPrincipal().toString());
    	claims.put("authorities", auth.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
		res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Origin, Content-Type, Version");
	    res.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        
        JSONArray authorities = new JSONArray();
        
        auth.getAuthorities().stream().forEach(s -> authorities.put(s.toString()));
        
        JSONObject user = new JSONObject();
        try {
			user.put("username", auth.getPrincipal());
			user.put("authorities", authorities);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        res.getOutputStream().write(user.toString().getBytes());
        res.getOutputStream().flush();
    }
}
