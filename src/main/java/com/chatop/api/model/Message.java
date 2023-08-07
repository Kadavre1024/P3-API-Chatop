package com.chatop.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data		// annotation Lombok. Nul besoin d’ajouter les getters et les setters. La librairie Lombok s’en charge pour nous.
@Entity    //annotation qui indique que la classe correspond à une table de la base de données
@Table(name = "messages")		// indique le nom de la table associée.
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Long rental_id;

	private Long user_id;
	private String message;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;
}
