package com.chatop.api.service;

import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.User;

public interface UserService {

	public UserDTO getUserById(final Long id);
	public User getUserByEmail(String email);
	public void deleteUserById(final Long id);


}
