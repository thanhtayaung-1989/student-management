package com.student.student_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.student_management.dto.CourseDto;
import com.student.student_management.entity.Course;
import com.student.student_management.repo.ICourseRepository;
import com.student.student_management.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepository courseRepository;

	@Override
	public CourseDto registerCourse(CourseDto courseDto) {
		Course course = new Course();

		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());

		// Register course info
		int courseId = courseRepository.save(course).getCourseId();
		courseDto.setCourseId(courseId);

		return courseDto;
	}

	@Override
	public CourseDto findCourseById(int courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (!course.isEmpty()) {
			CourseDto courseDto = new CourseDto();
			courseDto.setCourseId(course.get().getCourseId());
			courseDto.setCourseName(course.get().getCourseName());
			courseDto.setDuration(course.get().getDuration());
			return courseDto;
		}
		return null;
	}

	@Override
	public CourseDto updateCourse(CourseDto courseDto) {
		if (courseRepository.existsById(courseDto.getCourseId())) {
			Course course = new Course();
			course.setCourseId(courseDto.getCourseId());
			course.setCourseName(courseDto.getCourseName());
			course.setDuration(courseDto.getDuration());

			courseRepository.save(course);
			return courseDto;
		}
		return null;
	}

	@Override
	public int deleteCourse(int courseId) {
		if (courseRepository.existsById(courseId)) {
			courseRepository.deleteById(courseId);
			return 1;
		}
		return 0;
	}

	@Override
	public List<CourseDto> getAllCourse() {
		List<Course> courseList = courseRepository.findAll();
		if (!courseList.isEmpty()) {
			return getCousrseDtoList(courseList);
		}
		return null;
	}

	// Set Course Entity list to Course Dto list.
	private List<CourseDto> getCousrseDtoList(List<Course> courseList) {
		return courseList.stream().map(course -> {
			CourseDto courseDto = new CourseDto();

			courseDto.setCourseId(course.getCourseId());
			courseDto.setCourseName(course.getCourseName());
			courseDto.setDuration(course.getDuration());

			return courseDto;
		}).collect(Collectors.toList());
	}

}
