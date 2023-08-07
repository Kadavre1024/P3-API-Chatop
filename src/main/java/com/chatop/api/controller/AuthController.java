package com.chatop.api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.AuthRequest;
import com.chatop.api.dtos.AuthToken;
import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.service.AuthService;
import com.chatop.api.service.UserService;
import com.chatop.api.service.jwt.UserDetailsServiceImpl;
import com.chatop.api.utils.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
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
	@Operation(summary = "Create user", description="Create user by name, email and password, and authenticate it")
	public ResponseEntity<?> createUser(@RequestBody RegisterRequest registerRequest){

		UserDTO createdUser = authService.createUser(registerRequest);

		if(createdUser == null) {return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);}
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
		try {
			authManager.authenticate(userAuth);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuth.getName());
		AuthToken jwt = new AuthToken(jwtUtil.generateToken(userDetails.getUsername()));

		return new ResponseEntity<>(jwt, HttpStatus.OK); // response format '{"token":"jwt_created"}'
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate user", description="Authenticate user by email and password, and return a JWT")
	public AuthToken createAuthToken(
			@RequestBody AuthRequest authRequest,
			HttpServletResponse response
			) throws BadCredentialsException {

		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()); //Create authentication profil with requested user params

		try {
			authManager.authenticate(userAuth); //try to authenticate the new profil
		}catch (BadCredentialsException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new BadCredentialsException("Incorrect email or password");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
		AuthToken jwt = new AuthToken(jwtUtil.generateToken(userDetails.getUsername()));

		return jwt;
	}

	@GetMapping("/me")
	@Operation(summary = "Authenticated user details", description="Return details of authenticated user")
	public UserDTO getUserDetails(Authentication auth){
		UserDTO userAuth = authService.copyUserToDTO(userService.getUserByEmail(auth.getName()))   ;
		return userAuth;
	}



}
