package com.chatop.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository props;

	@Autowired
	private ModelMapper modelMapper;

	public Iterable<User> getAllUsers(){
		return props.findAll();
	}

	@Override
	public UserDTO getUserById(final Long id) {
		return modelMapper.map(props.findById(id).orElse(null), UserDTO.class);
	}

	public User saveUser(User u){
		System.out.println("789" + u);
		return props.save(u);
	}

	@Override
	public User getUserByEmail(String email) {
		return props.findFirstByEmail(email);
	}

	@Override
	public void deleteUserById(final Long id) {
		props.deleteById(id);
	}
}
