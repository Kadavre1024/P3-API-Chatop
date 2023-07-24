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
		User createdUser = userRepository.save(u);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setName(createdUser.getName());
		userDTO.setEmail(createdUser.getEmail());
		userDTO.setPassword(createdUser.getPassword());
		userDTO.setCreatedAt(createdUser.getCreatedAt());
		userDTO.setUpdatedAt(createdUser.getUpdatedAt());
		return userDTO;
	}
	
	@Override
	public UserDTO copyUserToDTO(User u) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(u.getId());
		userDTO.setName(u.getName());
		userDTO.setEmail(u.getEmail());
		userDTO.setPassword(u.getPassword());
		userDTO.setCreatedAt(u.getCreatedAt());
		userDTO.setUpdatedAt(u.getUpdatedAt());
		return userDTO;
	}
	
}
