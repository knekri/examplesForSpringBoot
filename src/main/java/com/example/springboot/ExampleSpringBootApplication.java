package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.entities.User;
import com.example.springboot.repository.UserRepository;

@SpringBootApplication
public class ExampleSpringBootApplication implements CommandLineRunner {
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		userRepository.save(new User("joska", "pista", "joska@pista.hu"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringBootApplication.class, args);
	}


	
	
}
