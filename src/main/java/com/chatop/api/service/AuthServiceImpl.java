package com.chatop.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO createUser(RegisterRequest reg) {
		User u = new User();
		u.setName(reg.getName());
		u.setEmail(reg.getEmail());
		u.setPassword(new BCryptPasswordEncoder().encode(reg.getPassword()));
		u.setCreatedAt(LocalDateTime.now());
		u.setUpdatedAt(LocalDateTime.now());
		System.out.println("456" + u);
		User createdUser = userRepository.save(u);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setName(createdUser.getName());
		userDTO.setEmail(createdUser.getEmail());
		userDTO.setPassword(createdUser.getPassword());
		userDTO.setCreated_at(createdUser.getCreatedAt());
		userDTO.setUpdated_at(createdUser.getUpdatedAt());
		return userDTO;
	}
}
