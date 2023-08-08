package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatop.api.model.User;



public interface UserRepository extends CrudRepository<User, Long> {

	User findFirstByEmail(String email);


}
