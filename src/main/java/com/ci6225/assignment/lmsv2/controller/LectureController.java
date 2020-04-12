package com.ci6225.assignment.lmsv2.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.assignment.lmsv2.entity.Course;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.Lecture;
import com.ci6225.assignment.lmsv2.services.LectureService;

@RestController
@RequestMapping("/lectures")
@CrossOrigin(origins = "http://localhost:4200")
public class LectureController {
	
	@Autowired
	LectureService service;
	
	@Autowired
	ServletContext context;
	

	
	@PostMapping
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public Lecture create(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("course") int course, @RequestParam("file") MultipartFile myfile) {
		System.out.println("file received: " + myfile.getOriginalFilename());
		System.out.println("lecture rec: " + name);
		//storageService.store(myfile);
		return service.saveLecture(course, name, description,myfile);
		//return new Lecture();
	}
	
	@GetMapping("/material/{fileName:.+}")
	public void downloader(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) {
 
		System.out.println("Downloading file :- " + fileName);
 
		File dir = new File("upload");
		String downloadFolder = dir.getAbsolutePath();
		Path file = Paths.get(downloadFolder,fileName);
		System.out.println(file);
		if (Files.exists(file)) {
			//response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			try {
				// copies all bytes from a file to an output stream
				Files.copy(file, response.getOutputStream());
				// flushes output stream
				response.getOutputStream().flush();
			} catch (IOException e) {
				System.out.println("Error :- " + e.getMessage());
			}
		} else {
			System.out.println("Sorry File not found!!!!");
		}
	}
	
//	@GetMapping("/{filename:.+}")
//	//@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('STUDENT')")
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//		Resource file = storageService.loadAsResource(filename);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}

}
