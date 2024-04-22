package com.serviceAuth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.serviceAuth.studentDetail.StudentDetail;
import com.serviceAuth.utils.JwtUtil;



@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private StudentDetail studentDetail;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=null,username=null,header=null;
		
		 header = request.getHeader("Auth");
		 System.out.println("HEADER IS +++ "+header);
		if(header!=null && header.startsWith("ABC "))
		{System.out.println("inside");
			token= header.substring(4);
			username = jwtUtil.extractUsername(token);
			UserDetails userDetails = studentDetail.loadUserByUsername(username);
			
			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
