package com.bbci.createuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbci.createuser.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByToken(String token);

}
