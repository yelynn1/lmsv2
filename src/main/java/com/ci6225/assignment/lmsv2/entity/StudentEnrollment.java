package com.ci6225.assignment.lmsv2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_enrollment")
public class StudentEnrollment {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	private int student_id;
	private int course_id;
	
	public StudentEnrollment() {
		
	}
	
	public StudentEnrollment(int studentId, int courseId) {
		this.student_id = studentId;
		this.course_id = courseId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	@Override
	public String toString() {
		return "enrollment: " + student_id + " : " + course_id;
	}
	
	
}
