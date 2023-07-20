package com.chatop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RentalController {
	
	RentalService rentalService;
	
	@GetMapping("/api/rentals")
	public Iterable<Rental> getAllRentals(){
		
		return rentalService.getAllRentals();
	}
	
	@PostMapping("/api/rentals")
	public ResponseEntity<?> createNewRental(HttpServletRequest request, @RequestBody RentalRequest rentalForm){
		Rental newRental = 
		return new ResponseEntity<>(, HttpStatus.OK);
	}
}
