package com.chatop.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.UserDTO;
import com.chatop.api.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		
		return userService.getUserById(id);
	}

}
