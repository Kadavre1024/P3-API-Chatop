package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{

	Rental findFirstByOwnerId(long owner_id);
}
