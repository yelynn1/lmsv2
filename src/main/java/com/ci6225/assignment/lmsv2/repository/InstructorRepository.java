package com.ci6225.assignment.lmsv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
	
	@Query("FROM Instructor WHERE email = ?1 AND password=?2")
	public Instructor validateInstructor(String username, String password);
	
	@Query("FROM Instructor WHERE email = ?1")
	public Instructor getInstructorByEmail(String username);
	
	@Query("SELECT courses FROM Instructor i WHERE i.id = ?1")
	public List<Course> getCoursesById(int id);
}
