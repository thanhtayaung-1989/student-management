package com.student.student_management.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseRegisterForm {

	@NotBlank(message = "Course name is required")
	private String courseName;
	
	@NotBlank(message = "Duration is required")
	private String duration;
}
