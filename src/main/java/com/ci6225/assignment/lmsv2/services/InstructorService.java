package com.ci6225.assignment.lmsv2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.Login;
import com.ci6225.assignment.lmsv2.repository.InstructorRepository;

@Service
public class InstructorService {
	
	@Autowired
	private InstructorRepository repository;
	

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Instructor instructorLogin(Login user) {
		Instructor instructor = repository.getInstructorByEmail(user.getUsername());
		if(passwordEncoder.matches(user.getPassword(), instructor.getPassword())) {
			return instructor;
		}
		else {
			return null;
		}
	}
	
	public Iterable<Instructor> findAll(){
		return repository.findAll();
	}
	
	public Instructor saveInstructor(@RequestBody Instructor instructor){
		instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
		return repository.save(instructor);
	}
	
	public List<Course> findCourseById(int id){
		return repository.getCoursesById(id);
	}
	
	
}
