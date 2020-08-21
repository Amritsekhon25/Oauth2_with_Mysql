package com.example.oauth2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	public User  findByusername(String username);

}
