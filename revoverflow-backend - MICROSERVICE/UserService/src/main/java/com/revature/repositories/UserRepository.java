package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByEmail(String string);
}