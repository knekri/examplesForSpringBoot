package com.example.springboot.controller;

import com.example.springboot.entities.User;
import com.example.springboot.service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class UserController {

  private ServiceClass userService;

  @Autowired
  public UserController(ServiceClass serviceClass) {
    this.userService = serviceClass;
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  @GetMapping(path = {"/{id}"})
  public User findOne(@PathVariable("id") int id) {
    return userService.findById(id).get();
  }

  @PutMapping(path = {"/{id}"})
  public User update(@RequestBody User user) {
    return userService.update(user);
  }

  @DeleteMapping(path = {"/{id}"})
  public User delete(@PathVariable("id") int id) {
    return userService.delete(id);
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

}
