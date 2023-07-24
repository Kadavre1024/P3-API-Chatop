package com.chatop.api.service;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.User;

public interface AuthService {

	UserDTO createUser(RegisterRequest registerRequest);

	UserDTO copyUserToDTO(User u);

}
