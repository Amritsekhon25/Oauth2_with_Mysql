package com.example.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth2.model.User;
import com.example.oauth2.repository.UserRepository;


@RestController
public class TestController {	
	
	@Autowired
	private UserRepository userRepository;

		
	@PostMapping("/user/saveUser")
	public User createDept(@RequestBody User user) {
		userRepository.save(user);
		return user;
	}
	
	
	
}
