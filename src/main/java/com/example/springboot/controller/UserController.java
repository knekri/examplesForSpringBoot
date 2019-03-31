package com.example.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.User;
import com.example.springboot.service.ServiceClass;


@RestController
public class UserController {

	ServiceClass userService;

	@Autowired
	public UserController(ServiceClass service) {
		this.userService = service;
	}
	
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>>  listUser(){
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path="/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User>  listUserById(@PathVariable(value = "id") String id){
		User userById = userService.findById(Integer.valueOf(id)).get();
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}

	@RequestMapping(path="/user", method = RequestMethod.POST)
	public ResponseEntity<User>  listUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.create(user), HttpStatus.OK);
	}
	
	@RequestMapping(value="/logmeout", method = RequestMethod.POST)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login";
	}
	
}
