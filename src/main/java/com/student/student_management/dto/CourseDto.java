package com.student.student_management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseDto {

	private int courseId;

	private String courseName;
	
	private String duration;
}
