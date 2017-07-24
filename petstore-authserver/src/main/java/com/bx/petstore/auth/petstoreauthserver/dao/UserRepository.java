package com.bx.petstore.auth.petstoreauthserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bx.petstore.auth.petstoreauthserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
