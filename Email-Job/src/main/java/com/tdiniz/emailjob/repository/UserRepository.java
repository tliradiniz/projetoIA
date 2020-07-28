package com.tdiniz.emailjob.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdiniz.emailjob.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
		@Query(value = " SELECT u.username FROM User u WHERE u.isAdmin = 'Y' OR u.id = :id ")
		List<String> queryUsers(@Param("id") Long id);
}
