package com.example.springboot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor
public class User {

	public User(String firstName, String lastName,String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull
	@Column(name="first_name")
	private String firstName;

	@NotNull
	@Column(name="last_name")
	private String lastName;

	@NotNull(message = "Email field must not be empty field!!")
	@Column(name="email")
	private String email;

}
