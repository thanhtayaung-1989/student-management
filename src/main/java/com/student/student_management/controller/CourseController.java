package com.student.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.student.student_management.dto.CourseDto;
import com.student.student_management.form.CourseRegisterForm;
import com.student.student_management.form.CourseUpdateForm;
import com.student.student_management.service.ICourseService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	// Register course info
	@PostMapping(path = "/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CourseDto> registerCourse(@Valid @RequestBody CourseRegisterForm courseForm) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName(courseForm.getCourseName());
		courseDto.setDuration(courseForm.getDuration());
		courseDto = courseService.registerCourse(courseDto);
		return new ResponseEntity<CourseDto>(courseDto, HttpStatus.OK);
	}

	// Find course info by id
	@GetMapping(value = "/findById")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public CourseDto findById(@RequestParam(name = "courseId") Integer courseId) {
		return courseService.findCourseById(courseId);
	}

	@PutMapping(path = "/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody CourseUpdateForm courseUpdateForm) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(courseUpdateForm.getCourseId());
		courseDto.setCourseName(courseUpdateForm.getCourseName());
		courseDto.setDuration(courseUpdateForm.getDuration());
		courseDto = courseService.updateCourse(courseDto);
		return new ResponseEntity<CourseDto>(courseDto, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCourse(@RequestParam(name = "courseId") Integer courseId) {
		int result = courseService.deleteCourse(courseId);
		if (result != 0) {
			String body = "Delete Successful";
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}
		String body = "Delete failed";
		return new ResponseEntity<String>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Find All Course info
	@GetMapping(path = "/getAllCourse")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ResponseBody
	public List<CourseDto> getAllCourse() {
		List<CourseDto> courseDtoList = courseService.getAllCourse();
		return courseDtoList;
	}

}
