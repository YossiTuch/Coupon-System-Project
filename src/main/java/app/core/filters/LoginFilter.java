package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import app.core.services.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;

//@Service
public class LoginFilter implements Filter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// Getting the token
		String token = req.getHeader("token");
		if(token != null) {
			
			try {
				
				jwtUtil.extractAllClaims(token); // Check if token is valid
				
				chain.doFilter(request, response); 
				return;
				
			}catch(JwtException e) { 
				// CORS policy
				resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200"); // 4200 for angular
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token: " + e.getMessage());
				return;
			}
		}else {
			 // Preflight
			if(req.getMethod().equalsIgnoreCase("OPTIONS")) {
				chain.doFilter(request, response);
				
			}else {
				 // Case if not logged in
				resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
			}
		}
	}
}
