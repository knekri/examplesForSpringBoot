package com.example.springboot.service;

import com.example.springboot.entities.User;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceClassImpl implements ServiceClass {

  private final UserRepository repository;

  @Autowired
  public ServiceClassImpl(final UserRepository repo) {
    this.repository = repo;
  }

  @Override
  public final User create(final User user) {
    return repository.save(user);
  }

  @Override
  public final User delete(final int id) {
    Optional<User> user = findById(id);
    if (user != null) {
      repository.delete(user.get());
    }
    return user.get();
  }

  @Override
  public final List<User> findAll() {
    return repository.findAll();
  }

  @Override
  public final Optional<User> findById(final int id) {
    return repository.findById(id);
  }

  @Override
  public final User update(final User user) {

    User originalUser = repository.getOne(user.getId());

    originalUser.setEmail(user.getEmail());
    originalUser.setFirstName(user.getFirstName());
    originalUser.setLastName(user.getLastName());

    return repository.save(originalUser);
  }


}
