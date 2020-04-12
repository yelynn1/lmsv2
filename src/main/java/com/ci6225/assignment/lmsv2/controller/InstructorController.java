package com.ci6225.assignment.lmsv2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.repository.InstructorRepository;
import com.ci6225.assignment.lmsv2.services.InstructorService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/instructor")
@RestController
public class InstructorController {
	
	@Autowired
	InstructorService service;
	


	@GetMapping
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public Iterable<Instructor> getInstructor(){
		return service.findAll();
	}
	
	@PostMapping
	public Instructor saveInstructor(@RequestBody Instructor instructor){
		return service.saveInstructor(instructor);
	}

	@GetMapping("{id}/courses")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public List<Course> getCourses(@PathVariable("id") int id) {
		return service.findCourseById(id);
	}
	
	
	

}
