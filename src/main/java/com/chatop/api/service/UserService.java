package com.chatop.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.Data;


public interface UserService {

	public UserDTO getUserById(final Long id);
	public User getUserByEmail(String email);
	public void deleteUserById(final Long id);
	
	
}
