package com.student.student_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.student_management.dto.CourseDto;
import com.student.student_management.dto.StudentDto;
import com.student.student_management.dto.StudentSearchDto;
import com.student.student_management.entity.Course;
import com.student.student_management.entity.Student;
import com.student.student_management.repo.ICourseRepository;
import com.student.student_management.repo.IStudentRepo;
import com.student.student_management.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private IStudentRepo studentRepo;
	
	@Autowired
	private ICourseRepository courseRepo;

	// Register student info
	@Override
	public StudentDto registerStudent(StudentDto studentDto) {
		Student student = new Student();
		
		student.setStudentName(studentDto.getStudentName());
		student.setAge(studentDto.getAge());
		student.setAddress(studentDto.getAddress());
		student.setEmail(studentDto.getAddress());
		student.setPhoneNo(studentDto.getPhoneNo());
		
		Optional<Course> course = courseRepo.findById(studentDto.getCourseId());
		if(!course.isEmpty()) {
			student.setCourse(course.get());
		}
		
		// Register student info
		int studentId = studentRepo.save(student).getStudentId();
		
		studentDto.setStudentId(studentId);
		
		return studentDto;
	}

	@Override
	public StudentSearchDto findStudentById(int studentId) {
		Optional<Student> student = studentRepo.findById(studentId);
		if(!student.isEmpty()) {
			StudentSearchDto studentDto = new StudentSearchDto();
			
			studentDto.setStudentId(student.get().getStudentId());
			studentDto.setStudentName(student.get().getStudentName());
			studentDto.setAge(student.get().getAge());
			studentDto.setAddress(student.get().getAddress());
			studentDto.setEmail(student.get().getEmail());
			studentDto.setPhoneNo(student.get().getPhoneNo());
			
			CourseDto courseDto = new CourseDto();
			courseDto.setCourseId(student.get().getCourse().getCourseId());
			courseDto.setCourseName(student.get().getCourse().getCourseName());
			courseDto.setDuration(student.get().getCourse().getDuration());
			
			studentDto.setCourseDto(courseDto);
			
			return studentDto;
		}
		return null;
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto) {
		if(studentRepo.existsById(studentDto.getStudentId())) {
			Student student = new Student();
			
			student.setStudentName(studentDto.getStudentName());
			student.setAge(studentDto.getAge());
			student.setAddress(studentDto.getAddress());
			student.setEmail(studentDto.getAddress());
			student.setPhoneNo(studentDto.getPhoneNo());
			student.setStudentId(studentDto.getStudentId());
			Optional<Course> course = courseRepo.findById(studentDto.getCourseId());
			if(!course.isEmpty()) {
				student.setCourse(course.get());
			}			
			
			// Update student info
			studentRepo.save(student);
			return studentDto;
			
		}
		return null;
	}

	@Override
	public int deleteStudent(int studentId) {
		if(studentRepo.existsById(studentId)) {
			studentRepo.deleteById(studentId);
			return 1;
		}
		return 0;
	}

	@Override
	@Transactional
	public List<StudentSearchDto> getAllStudents(int pageSize, int offset) {
		offset = (offset - 1) * pageSize;
		List<Student> studentList = studentRepo.getAllStudents(pageSize, offset);
		if(!studentList.isEmpty()) {
			return getStudentDtoList(studentList);
		}		
		return null;
	}
	
	// Set Student Entity list to Student Dto list.
	private List<StudentSearchDto> getStudentDtoList(List<Student> studentList) {
	    return studentList.stream()
	        .map(student -> {
	        	StudentSearchDto studentDto = new StudentSearchDto();
	        
	        studentDto.setStudentId(student.getStudentId());
	        studentDto.setStudentName(student.getStudentName());
			studentDto.setAge(student.getAge());
			studentDto.setAddress(student.getAddress());
			studentDto.setEmail(student.getEmail());
			studentDto.setPhoneNo(student.getPhoneNo());
			
			CourseDto courseDto = new CourseDto();
			courseDto.setCourseId(student.getCourse().getCourseId());
			courseDto.setCourseName(student.getCourse().getCourseName());
			courseDto.setDuration(student.getCourse().getDuration());
			
			studentDto.setCourseDto(courseDto);
			
	        return studentDto;
	    }).collect(Collectors.toList());
	}

	@Override
	public long getStudentCount() {
		return studentRepo.count();
	}

}
