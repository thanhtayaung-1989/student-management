package com.student.student_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.student_management.entity.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {

}
