package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter @Getter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class User {
	
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;
	    
	    @Column(name="first_name")
	    private String firstName;
	    
	    @Column(name="last_name")
	    private String lastName;
	   
	    @Column(name="email")
	    private String email;

}
