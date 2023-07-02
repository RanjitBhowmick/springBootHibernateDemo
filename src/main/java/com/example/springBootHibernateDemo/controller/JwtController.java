package com.example.springBootHibernateDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootHibernateDemo.entities.JwtRequest;
import com.example.springBootHibernateDemo.entities.JwtResponse;
import com.example.springBootHibernateDemo.services.CustomUserDetailService;
import com.example.springBootHibernateDemo.services.JwtUtil;

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/token")
	public ResponseEntity<?> getToken(@RequestBody JwtRequest request) throws Exception {
		System.out.println("Inside JwtController controller for generating token..");
		try {
			this.authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("Exception: Sorry! User not found.");
		}
		System.out.println("Authmanager's user authentication completed internally..");
		UserDetails userdetails = this.customUserDetailService.loadUserByUsername(request.getUsername());
		String token = this.jwtUtil.generateToken(userdetails);
		System.out.println("Token Generated : [" + token + "]");
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
