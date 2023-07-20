package com.chatop.api.controller;



import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.model.User;
import com.chatop.api.service.UserService;



//@RestController
public class UserController {
	
	/**@Autowired
	private UserService userService;
	
	
	
	
	
	@GetMapping("/auth/users")
	public @ResponseBody Iterable<User> getUsers(){
		System.out.println("123" + userService.getAllUsers());
		return userService.getAllUsers();
	}
	
	**/

}
