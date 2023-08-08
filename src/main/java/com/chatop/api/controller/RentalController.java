package com.chatop.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.configuration.FileStorageProperties;
import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.dtos.RentalResponse;
import com.chatop.api.dtos.RentalsList;
import com.chatop.api.model.User;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api/rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileStorageProperties storageProperties;

	@PutMapping(value="/{id}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Update rental", description="Update a rental by its ID")
	public RentalResponse updateRentalByID(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam float surface,
			@RequestParam float price,
			@RequestParam(required=false) String picture,
			@RequestParam String description
			){

		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setSurface(surface);
		rentalRequest.setPrice(price);
		rentalRequest.setDescription(description);
		rentalRequest.setPicture(picture);

		rentalService.updateRental(rentalRequest, id);

		return new RentalResponse("Rental updated !");
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get rental", description="Get a rental by its ID")
	public RentalDTO getRentalByID(@PathVariable Long id) {
		return rentalService.getRentalById(id);
	}

	@GetMapping
	@Operation(summary = "Get rental list", description="Get a rental list of all rentals in database")
	public RentalsList getAllRentals(){
		return rentalService.getAllRentals();
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Create rental", description="Create a new rental")
	public RentalResponse createNewRental(
			HttpServletRequest request,
			@RequestParam String name,
			@RequestParam float surface,
			@RequestParam float price,
			@RequestParam(required=false) MultipartFile picture,
			@RequestParam String description
			){

		Principal principal = request.getUserPrincipal();
		User userAuth = userService.getUserByEmail(principal.getName());

		String picturePrefixPath ="Owner-ID" + userAuth.getId() + "_Rental-Id" + (rentalService.getLastRentalId()+1) + "_";

		// Picture storage : return the URL storage
		String picturePath = FileStorageService.storePicturePath(picture, picturePrefixPath, storageProperties.getUploadDir(), storageProperties.getWebServerUrl());

		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setSurface(surface);
		rentalRequest.setPrice(price);
		rentalRequest.setDescription(description);
		rentalRequest.setPicture(picturePath);

		rentalService.createRental(rentalRequest, userAuth.getId());

		return new RentalResponse("Rental created !");
	}
}
