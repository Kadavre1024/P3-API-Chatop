package com.chatop.api.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.dtos.RentalResponse;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;
//import org.springframework.web.multipart.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public RentalDTO getRentalByID(@PathVariable Long id) {
		return rentalService.getRentalById(id);
	}
	
	@PutMapping(value="/{id}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public RentalResponse updateRentalByID(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam float surface,
			@RequestParam float price,
			@RequestParam MultipartFile picture,
			@RequestParam String description
			) throws IllegalStateException, IOException {
		
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setSurface(surface);
		rentalRequest.setPrice(price);
		rentalRequest.setDescription(description);
		
		//File pictureFile = new File(picturePath);
		//picture.transferTo(pictureFile);
		
		//rentalRequest.setPicture(picturePath);
		
		rentalService.updateRental(rentalRequest, id);
		
		return new RentalResponse("Rental updated !");
	}
	
	@GetMapping
	public List<RentalDTO> getAllRentals(){
		System.out.println(123);
		List<Rental> rentalList = rentalService.getAllRentals();
		System.out.println(123);
		System.out.println(rentalList);
		return rentalService.getAllRentals().stream().map(rental -> modelMapper.map(rental, RentalDTO.class)).collect(Collectors.toList());
	}
	
	@PostMapping(value="/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RentalResponse createNewRental(
			HttpServletRequest request,
			@RequestParam String name,
			@RequestParam float surface,
			@RequestParam float price,
			@RequestParam MultipartFile picture,
			@RequestParam String description
			) throws IllegalStateException, IOException {
		
		Principal principal = request.getUserPrincipal();
		User userAuth = userService.getUserByEmail(principal.getName());
		
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setName(name);
		rentalRequest.setSurface(surface);
		rentalRequest.setPrice(price);
		rentalRequest.setDescription(description);
		
		String pictureFileName = "Rental-ID" + (rentalService.rentalsCount()+1) + "_" + name + ".jpg";
		String picturePath = "/src/main/resources/static/RentalsImage/" + pictureFileName;
		File pictureFile = new File(picturePath);
		picture.transferTo(pictureFile);
		
		rentalRequest.setPicture(picturePath);
		
		rentalService.createRental(rentalRequest, userAuth.getId());
		
		System.out.println("Hello /api/rentals/create");
		return new RentalResponse("Rental created !");
	}
}
