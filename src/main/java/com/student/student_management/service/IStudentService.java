package com.student.student_management.service;

import java.util.List;
import com.student.student_management.dto.StudentDto;
import com.student.student_management.dto.StudentSearchDto;

public interface IStudentService {

	// Register student info
	public StudentDto registerStudent(StudentDto studentDto);
	
	// Find student by Id
	public StudentSearchDto findStudentById(int studentId);
	
	// Update Student info
	public StudentDto updateStudent(StudentDto studentDto);
	
	// Delete Student info
	public int deleteStudent(int studentId);
	
	// Get all student
	public List<StudentSearchDto> getAllStudents(int pageSize, int offset);
	
	// Get all student count
	public long getStudentCount();

}
