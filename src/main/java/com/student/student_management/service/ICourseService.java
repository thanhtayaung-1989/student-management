package com.student.student_management.service;

import java.util.List;
import com.student.student_management.dto.CourseDto;

public interface ICourseService {

	// Register course info
	public CourseDto registerCourse(CourseDto courseDto);

	// Find course by Id
	public CourseDto findCourseById(int courseId);

	// Update course info
	public CourseDto updateCourse(CourseDto courseDto);

	// Delete course info
	public int deleteCourse(int courseId);

	// Get all courses
	public List<CourseDto> getAllCourse();

}
