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
import com.student.student_management.dto.StudentDto;
import com.student.student_management.dto.StudentSearchDto;
import com.student.student_management.form.StudentRegisterForm;
import com.student.student_management.form.StudentUpdateForm;
import com.student.student_management.service.IStudentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	// Register student info
	@PostMapping(path = "/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<StudentDto> registerStudent(@Valid @RequestBody StudentRegisterForm studentForm) {
		StudentDto studentDto = new StudentDto();
		
		studentDto.setAddress(studentForm.getAddress());
		studentDto.setAge(studentForm.getAge());
		studentDto.setCourseId(studentForm.getCourseId());
		studentDto.setEmail(studentForm.getEmail());
		studentDto.setPhoneNo(studentForm.getPhoneNo());
		studentDto.setStudentName(studentForm.getStudentName());
		
		studentDto = studentService.registerStudent(studentDto);
		return new ResponseEntity<StudentDto>(studentDto,HttpStatus.CREATED);
	}

	// Find Student info by id
	@GetMapping(value = "/findById")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public StudentSearchDto findById(@RequestParam(name = "studentId") Integer studentId) {
		return studentService.findStudentById(studentId);
	}

	@PutMapping(path = "/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentUpdateForm studentForm) {
		StudentDto studentDto = new StudentDto();
		
		studentDto.setAddress(studentForm.getAddress());
		studentDto.setAge(studentForm.getAge());
		studentDto.setCourseId(studentForm.getCourseId());
		studentDto.setEmail(studentForm.getEmail());
		studentDto.setPhoneNo(studentForm.getPhoneNo());
		studentDto.setStudentId(studentForm.getStudentId());
		studentDto.setStudentName(studentForm.getStudentName());
		studentDto.setCourseId(studentForm.getCourseId());
		
		studentDto = studentService.updateStudent(studentDto);
		return new ResponseEntity<StudentDto>(studentDto,HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStudent(@RequestParam(name = "studentId") Integer studentId) {
		int result = studentService.deleteStudent(studentId);
		if (result != 0) {
			String body = "Delete Successful";
			return new ResponseEntity<String>(body,HttpStatus.OK);
		}
		String body = "Delete failed";
		return new ResponseEntity<String>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Find All Student info
	@GetMapping(value = "/getAllStudents")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public List<StudentSearchDto> getAllStudents(@RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "1") int pageNo) {
		List<StudentSearchDto> studentDtoList = studentService.getAllStudents(pageSize, pageNo);
		return studentDtoList;
	}

}
