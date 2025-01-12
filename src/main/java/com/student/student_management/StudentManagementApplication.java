package com.student.student_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(info = @Info(title = "Student management API", version = "1.0", 
					description = "API Documentation"))
@SpringBootApplication
@EnableScheduling
public class StudentManagementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);

	
	}

}
