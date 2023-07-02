package com.example.springBootHibernateDemo.securityfilter;

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

import com.example.springBootHibernateDemo.services.CustomUserDetailService;
import com.example.springBootHibernateDemo.services.JwtUtil;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		System.out.println("Inside AuthenticationFilter filter..");
		
		String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String userName = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				logger.error("Exception : Error occured while validating username!");
			}

			UserDetails userDetails = this.customUserDetailService.loadUserByUsername(userName);

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				logger.debug("Token is not validated!");
			}

		}

		System.out.println("Proceeding from filter to respective controller..");
		filterChain.doFilter(request, response);
	}

}
