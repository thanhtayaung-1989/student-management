package com.student.student_management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.student_management.entity.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByUsername(String username);
	
}
