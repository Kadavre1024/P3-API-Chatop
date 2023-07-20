package com.chatop.api.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.chatop.api.model.User;
import com.chatop.api.service.AuthService;
import com.chatop.api.service.TokenService;
import com.chatop.api.service.UserService;
import com.chatop.api.service.jwt.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class SignUpUserController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserService userService;
	
	private final TokenService tokenService;
	
	public SignUpUserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
	
	/**@PostMapping()
	public ResponseEntity<?> createUser(@RequestBody RegisterRequest registerRequest){
		
		UserDTO createdUser = authService.createUser(registerRequest);
		
		if(createdUser == null)
			return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}**/
	
	@PostMapping("/register")
	public AuthToken createUser(@RequestBody RegisterRequest registerRequest){
		
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
		UserDTO createdUser = authService.createUser(registerRequest);
		
		if(createdUser == null)
			return null;
		
		authManager.authenticate(userAuth);
		final String jwt = tokenService.generateToken(userAuth);
		System.out.println(jwt);
		return new AuthToken(jwt);
	}
	
	@PostMapping("/login")
	public AuthToken createAuthToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
		UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
		
		try {
			authManager.authenticate(userAuth);
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect email or password");
		}catch (DisabledException de) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found, please register first");
			return null;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
		final String jwt = tokenService.generateToken(userAuth);
		System.out.println(jwt);
		return new AuthToken(jwt);
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getUserDetails(HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		User userAuth = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<>(userAuth, HttpStatus.OK);
	}
}
