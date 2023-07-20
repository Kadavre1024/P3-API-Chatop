package com.chatop.api.service;

import com.chatop.api.dtos.RegisterRequest;
import com.chatop.api.dtos.UserDTO;

public interface AuthService {

	UserDTO createUser(RegisterRequest registerRequest);

}
