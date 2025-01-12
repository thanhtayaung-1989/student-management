package com.student.student_management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentDto {
	
	private int studentId;

	private String studentName;
	
	private int age;
	
	private String address;
	
	private String phoneNo;
	
	private String email;
	
	private int courseId;

}
