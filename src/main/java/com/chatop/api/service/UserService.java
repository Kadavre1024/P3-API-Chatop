package com.chatop.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {

	@Autowired
	private UserRepository props;
	
	public Iterable<User> getAllUsers(){
		return props.findAll();
	}
	
	public Optional<User> getUserById(final Long id) {
		return props.findById(id);
	}
	
	public User saveUser(User u){
		System.out.println("789" + u);
		return props.save(u);
	}
	
	public User registerUser(RegisterRequest request) {
		User u = new User();
		u.setName(request.getName());
		u.setEmail(request.getEmail());
		u.setPassword(request.getPassword());
		u.setCreatedAt(LocalDateTime.now());
		u.setUpdatedAt(LocalDateTime.now());
		return props.save(u);
	}
	
	public User getUserByEmail(String email) {
		return props.findFirstByEmail(email);
	}
	
	public void deleteUserById(final Long id) {
		props.deleteById(id);
	}
	
	
}
