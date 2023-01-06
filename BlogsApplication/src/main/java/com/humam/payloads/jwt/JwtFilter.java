package com.humam.payloads.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.humam.payloads.security.UserDetailsServiceImpl;

public class JwtFilter extends OncePerRequestFilter{

	
	@Autowired
	private JwtUtils ju;
	
	@Autowired
	private UserDetailsServiceImpl usImpl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Logger logger = LoggerFactory.getLogger(JwtFilter.class);
		try {
		String token = getTokenFromRequest(request);
		if(token!=null && ju.validateJwtToken(token)) {
			UserDetails userDetails = usImpl.loadUserByUsername(ju.getUserNameFromJwtToken(token));
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			token2.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(token2);
		}
		
		}catch(Exception e) {
			logger.error("Not able to set Filter");
		}
		
		filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}
	public String getTokenFromRequest(HttpServletRequest req) {
		String jwtFromCookies = ju.getJwtFromCookies(req);
		return jwtFromCookies;
	}

}
