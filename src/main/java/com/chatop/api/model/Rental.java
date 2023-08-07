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
@Table(name = "rentals")		// indique le nom de la table associée.
public class Rental {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Float surface;
	private Float price;
	private String picture;
	private String description;

	@Column(name="owner_id")
	private Long ownerId;

	@Column(name="created_at")
	private LocalDateTime created_at;

	@Column(name="updated_at")
	private LocalDateTime updated_at;
}
