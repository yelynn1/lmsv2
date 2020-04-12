package com.ci6225.assignment.lmsv2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.StudentEnrollment;
import com.ci6225.assignment.lmsv2.repository.CourseRepository;
import com.ci6225.assignment.lmsv2.repository.EnrollmetRepository;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EnrollmetRepository enroll_repo;
	
	public Iterable<Course> getCourses(){
		return repository.findAll();
	}
	
	public Course save(Course course) {
		return repository.save(course);
	}
	
	public Optional<Course> getProduct(int id) {
		return repository.findById(id);
	}
	
	public StudentEnrollment enroll(StudentEnrollment enrollment) {
		return enroll_repo.save(enrollment);
	}
	
	public void unEnroll(int student_id, int course_id) {
		//Optional<StudentEnrollment> enrollment = enroll_repo.getEnrollment(student_id, course_id);
		//enroll_repo.delete(enrollment.get());
		enroll_repo.deleteEnrollment(student_id, course_id);
		System.out.println("deleted: course-" + course_id + " student-" + student_id);
	}
}
