package com.chatop.api.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.AuthRequest;
import com.chatop.api.dtos.AuthToken;
import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.service.AuthService;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;
import com.chatop.api.service.jwt.UserDetailsServiceImpl;
import com.chatop.api.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public AuthToken createUser(@RequestBody RegisterRequest registerRequest){
		
		UserDTO createdUser = authService.createUser(registerRequest);
		
		if(createdUser == null)
			return null;
		
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
		authManager.authenticate(userAuth);
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuth.getName());
		AuthToken jwt = new AuthToken(jwtUtil.generateToken(userDetails.getUsername())); 
		System.out.println(jwt);
		
		return jwt; // response format '{"token":"jwt_created"}'
	}
	
	@PostMapping("/login")
	public AuthToken createAuthToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
		
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword());
		
		try {
			System.out.println("111" + userAuth);
			Authentication auth = authManager.authenticate(userAuth);
			if(auth.isAuthenticated()) {
				System.out.println("Authentication success : " + auth);
			}else {
				System.out.println("Authentication fail : " + userAuth);
			}
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect email or password");
		}catch (DisabledException de) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
			return null;
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
		
		AuthToken jwt = new AuthToken(jwtUtil.generateToken(userDetails.getUsername())); 
		
		System.out.println(jwt);
		System.out.println(userDetails);

		return jwt;
	}
	
	@GetMapping("/me")
	public UserDTO getUserDetails(Authentication auth){
		UserDTO userAuth = authService.copyUserToDTO(userService.getUserByEmail(auth.getName()))   ;
		return userAuth;
	}
	
	
	
}
