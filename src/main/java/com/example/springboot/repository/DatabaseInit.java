package com.example.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springboot.entities.User;

@Component
public class DatabaseInit implements CommandLineRunner {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		userRepository.save(new User("joska", "joska@pista.hu", this.passwordEncoder.encode("pwd")));
		userRepository.save(new User("julis", "juliska@mariska.hu", this.passwordEncoder.encode("pwd")));
	}

}
