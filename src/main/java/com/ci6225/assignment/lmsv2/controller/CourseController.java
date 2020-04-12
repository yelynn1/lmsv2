package com.ci6225.assignment.lmsv2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.StudentEnrollment;
import com.ci6225.assignment.lmsv2.repository.CourseRepository;
import com.ci6225.assignment.lmsv2.services.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
	
	@Autowired
	CourseService service;
	
	@GetMapping
	public Iterable<Course> getCourses(){
		return service.getCourses();
	}
	
	@PostMapping
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public Course create(@RequestBody Course course) {
		return service.save(course);
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('STUDENT')")
	public StudentEnrollment create(@RequestBody StudentEnrollment enrollment) {
		return service.enroll(enrollment);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('STUDENT')")
	public Optional<Course> getProduct(@PathVariable("id") int id) {
		return service.getProduct(id);
	}
	
	@DeleteMapping("/{course_id}/unenroll/{student_id}")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public void unEnrollStudent(@PathVariable("student_id") int student_id, @PathVariable("course_id") int course_id) {
		service.unEnroll(student_id, course_id);
	}

}
