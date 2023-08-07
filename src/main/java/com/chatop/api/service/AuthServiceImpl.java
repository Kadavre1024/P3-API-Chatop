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
		u.setCreated_at(LocalDateTime.now());
		u.setUpdated_at(LocalDateTime.now());
		User createdUser = userRepository.save(u);
		return copyUserToDTO(createdUser);
	}

	@Override
	public UserDTO copyUserToDTO(User u) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(u.getId());
		userDTO.setName(u.getName());
		userDTO.setEmail(u.getEmail());
		userDTO.setPassword(u.getPassword());
		userDTO.setCreated_at(u.getCreated_at());
		userDTO.setUpdated_at(u.getUpdated_at());
		return userDTO;
	}

}
