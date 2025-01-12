package com.student.student_management.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {

	private int userId;
	
	private String userName;
	
	private String password;
	
	private String role;
	
}
