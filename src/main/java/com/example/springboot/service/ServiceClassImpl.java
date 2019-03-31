package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.User;
import com.example.springboot.repository.UserRepository;

@Service
public class ServiceClassImpl implements ServiceClass {

	UserRepository repository;

	@Autowired
	public ServiceClassImpl(UserRepository repo) {
		this.repository = repo;
	}

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(int id) {
        Optional<User> user = findById(id);
        if(user != null){
            repository.delete(user.get());
        }
        return user.get();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public User update(User user) {

    	User originalUser = repository.getOne(user.getId());
    	
    	originalUser.setEmail(user.getEmail());
    	originalUser.setFirstName(user.getFirstName());
    	originalUser.setLastName(user.getLastName());
    			
        return repository.save(originalUser);
    }
	

}
