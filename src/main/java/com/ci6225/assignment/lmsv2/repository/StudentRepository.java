package com.ci6225.assignment.lmsv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Query("FROM Student WHERE email = ?1 AND password=?2")
	public Student validateStudent(String username, String password);
	
	@Query("FROM Student WHERE email = ?1")
	public Student getStudentByEmail(String username);
	
	@Query("SELECT courses FROM Student i WHERE i.id = ?1")
	public List<Course> getCoursesById(int id);

}
