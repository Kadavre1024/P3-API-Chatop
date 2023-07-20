package com.chatop.api.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.AuthRequest;
import com.chatop.api.dtos.AuthToken;
import com.chatop.api.service.TokenService;
import com.chatop.api.service.jwt.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

//@RestController
//@CrossOrigin
//@RequestMapping("/api/auth/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
	
    //@PostMapping()
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
		return new AuthToken(jwt);
	}

    /**private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/login")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }**/
    
}
