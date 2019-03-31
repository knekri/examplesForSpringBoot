package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot.entities.User;


@Service
public interface ServiceClass {

	public User create(User user);
	
	public User delete(int id);
	
	public List<User> findAll();
	
	public Optional<User> findById(int id);
	
	public User update(User user);

}
