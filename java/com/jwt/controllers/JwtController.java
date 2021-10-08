package com.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value ="/token", method= RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		try {
			authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		UserDetails userDetails =this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token =this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT :" +token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String userName , String password) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName , password));
			
		}catch(DisabledException e) {
			e.printStackTrace();
			throw new Exception("user disable");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials dee");
		}
	}
}
