package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor @RequiredArgsConstructor
public class User {
	
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;
	    
	    @NonNull @Column(name = "name")
	    private String userName;
	   
	    @NonNull @Column(name= "email", nullable = false)
	    private String email;
	    
	    @NonNull @Column(name = "password", nullable = false)
    	private String password;
}
