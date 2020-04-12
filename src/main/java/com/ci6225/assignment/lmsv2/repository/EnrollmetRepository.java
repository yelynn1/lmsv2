package com.ci6225.assignment.lmsv2.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.StudentEnrollment;

public interface EnrollmetRepository extends CrudRepository<StudentEnrollment, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM StudentEnrollment WHERE student_id = ?1 and course_id = ?2")
	public void deleteEnrollment(int student_id, int course_id);

}
