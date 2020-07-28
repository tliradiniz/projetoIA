package com.tdiniz.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdiniz.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = " SELECT username "
	  		+ " FROM USERS u "
	  		+ " WHERE u.isAdmin = 'Y' "
	  		+ " OR id = \n-- #id\n", 
	  nativeQuery = true)
List<String> findAllAdminAndSender(Long id);
}
