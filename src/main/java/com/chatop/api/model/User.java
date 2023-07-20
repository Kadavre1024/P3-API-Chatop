package com.chatop.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data		// annotation Lombok. Nul besoin d’ajouter les getters et les setters. La librairie Lombok s’en charge pour nous.
@Entity    //annotation qui indique que la classe correspond à une table de la base de données
@Table(name = "users")		// indique le nom de la table associée.
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String name;
	private String password;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
}
