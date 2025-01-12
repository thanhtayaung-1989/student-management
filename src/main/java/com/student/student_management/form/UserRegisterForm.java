package com.student.student_management.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegisterForm {

	@NotBlank(message = "User name is required")
	private String userName;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "Role is required")
	private String role;
}
