package com.student.student_management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.student_management.entity.Roles;
import com.student.student_management.enums.RolesEnum;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRolesname(RolesEnum rolesname);
}
