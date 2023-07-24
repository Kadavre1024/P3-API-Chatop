package com.chatop.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.MessageRequest;
import com.chatop.api.dtos.MessageResponse;
import com.chatop.api.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

	private MessageService messageService;
	
	@PostMapping
	public MessageResponse createNewMessage(@RequestBody MessageRequest message) {
		
		
		return new MessageResponse("Message send with success");
	}
}
