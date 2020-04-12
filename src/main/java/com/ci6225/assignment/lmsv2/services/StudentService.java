package com.ci6225.assignment.lmsv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.Login;
import com.ci6225.assignment.lmsv2.entity.Student;
import com.ci6225.assignment.lmsv2.repository.InstructorRepository;
import com.ci6225.assignment.lmsv2.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Student studentLogin(Login user) {
		Student student = repository.getStudentByEmail(user.getUsername());
		if(passwordEncoder.matches(user.getPassword(), student.getPassword())) {
			return student;
		}
		else {
			return null;
		}
	}
	
//	public boolean studentLogin(Login user) {
//		Student student = repository.getStudentByEmail(user.getUsername());
//		return passwordEncoder.matches(user.getPassword(), student.getPassword());
//	}
	
	public Iterable<Student> findAll(){
		return repository.findAll();
	}
	
	public Student saveStudent(@RequestBody Student student){
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return repository.save(student);
	}
	
	public List<Course> findCourseById(int id){
		return repository.getCoursesById(id);
	}
	
	
}
