package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dtos.MessageRequest;
import com.chatop.api.dtos.MessageResponse;
import com.chatop.api.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping
	@Operation(summary = "Create message", description="Create a message for a rental to its owner")
	public MessageResponse createNewMessage(@RequestBody MessageRequest message
			) {
		if(messageService.createNewMessage(message)==null) {
			return new MessageResponse("Message not sent. Something occured");
		}
		return new MessageResponse("Message send with success");
	}
}
