package com.example.springboot.service;

import com.example.springboot.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ServiceClass {

  User create(User user);

  User delete(int id);

  List<User> findAll();

  Optional<User> findById(int id);

  User update(User user);

}
