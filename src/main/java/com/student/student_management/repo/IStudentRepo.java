package com.student.student_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student.student_management.entity.Student;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Integer> {
	
	@Procedure(procedureName = "get_students")
	public List<Student> getAllStudents(@Param("limit_value") int pageSize, 
			@Param("offset_value") int offset);

}
