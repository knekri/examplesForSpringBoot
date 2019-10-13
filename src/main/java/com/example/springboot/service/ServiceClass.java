package com.example.springboot.service;

import com.example.springboot.entities.User;

import java.util.List;
import java.util.Optional;

public interface ServiceClass {

	User create(User user);
	
	User delete(int id);
	
	List<User> findAll();
	
	Optional<User> findById(int id);
	
	User update(User user);

}
