package com.example.springboot.repository;

import com.example.springboot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {


    private UserRepository userRepository;

    @Autowired
    public DbInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //DB initialization
    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        userRepository.save(new User("joska", "pista", "joska@pista.hu"));
        userRepository.save(new User("lajcsi", "béla", "lajos.bela.hu"));
        userRepository.save(new User("okos", "toni", "okoska@smart.com"));
        userRepository.save(new User("harry", "potter", "hp@roxfort.com"));
        userRepository.save(new User("darth", "vader", "anakin@lucasarts.com"));
    }
}
