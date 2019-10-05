package com.example.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springboot.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUserDetails myUserDetails = new MyUserDetails(this.userRepository.findByUserName(username));
		return myUserDetails;
	}

}
