package com.student.student_management.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentRegisterForm {

	@NotBlank(message = "Student name is required")
	private String studentName;
	
	@Min(value = 18, message = "Age must be at least {value}")
	private int age;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Phone no is required")
	private String phoneNo;
	
	@Email(message = "Please provide a valid email address")
	private String email;
	
	private int courseId;

}
