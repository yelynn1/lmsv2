package com.ci6225.assignment.lmsv2.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Lecture;
import com.ci6225.assignment.lmsv2.repository.CourseRepository;
import com.ci6225.assignment.lmsv2.repository.LectureRepository;

@Service
public class LectureService {
	
	@Autowired
	LectureRepository repository;
	
	@Autowired
	CourseRepository courseRepo;
	
	public Lecture saveLecture(int course_id,String name,String description, MultipartFile file) {
		
		String filename = file.getOriginalFilename();
		try {
			byte[] bytes = file.getBytes();

			File dir = new File("upload");
			if (!dir.exists())
				dir.mkdirs();

			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + name + "_"+ filename);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			String url = name + "_"+ filename;
			
			Lecture lecture = new Lecture();
			lecture.setName(name);
			Optional<Course> course = courseRepo.findById(course_id);
			
			lecture.setCourse(course.get());
			lecture.setDescription(description);
			lecture.setLink(url);
			
			lecture.setDate(new Date());
			
			return repository.save(lecture);
		} 
		catch (Exception e) {
			return null;
		}
		
		
		
	}
}
