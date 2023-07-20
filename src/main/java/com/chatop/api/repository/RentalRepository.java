package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatop.api.model.Rental;

public interface RentalRepository extends CrudRepository<Rental, Long>{

	Rental findFirstByOwnerId(long ownerId);
}
