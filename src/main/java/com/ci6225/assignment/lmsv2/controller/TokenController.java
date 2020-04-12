package com.ci6225.assignment.lmsv2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ci6225.assignment.config.Iconstants;
import com.ci6225.assignment.lmsv2.entity.Instructor;
import com.ci6225.assignment.lmsv2.entity.Login;
import com.ci6225.assignment.lmsv2.entity.Student;
import com.ci6225.assignment.lmsv2.services.InstructorService;
import com.ci6225.assignment.lmsv2.services.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TokenController {
	
	@Autowired
	InstructorService insService;
	
	@Autowired
	StudentService stdService;
	
	
	@PostMapping(value = "/instructor/login")
    public ResponseEntity<String> instructorLogin(@RequestBody Login login) throws ServletException {
		System.out.println("token received");
		//Login login = new Login(username,password);
 
        String jwttoken = "";
 
        // If the username and password fields are empty -> Throw an exception!
        if(login.getUsername().isEmpty() || login.getPassword().isEmpty()) {
        	System.out.println("got empty");
            return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);
        	
        }
 
        String name = login.getUsername();
        String password = login.getPassword();
        
        Instructor instructor = insService.instructorLogin(login);
        // If the username and password are not valid -> Thrown an invalid credentials exception!
        if(instructor == null)
            return new ResponseEntity<String>("Invalid credentials. Please check the username and password.", HttpStatus.UNAUTHORIZED);
        else {
            // Creating JWT using the user credentials.
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("usr", login.getUsername());
            claims.put("sub", "Authentication token");
            claims.put("iss", Iconstants.ISSUER);
            claims.put("rol", "ROLE_INSTRUCTOR");
            claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
 
            jwttoken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
            System.out.println("Returning the following token to the user= "+ jwttoken);
        }
 
        return new ResponseEntity<String>(jwttoken + "," + Integer.toString(instructor.getId()) + "," + instructor.getFull_name(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/login", method = RequestMethod.POST)
    public ResponseEntity<String> studentLogin(@RequestBody Login login) throws ServletException {
		System.out.println("token received");
		//Login login = new Login(username,password);
 
        String jwttoken = "";
 
        // If the username and password fields are empty -> Throw an exception!
        if(login.getUsername().isEmpty() || login.getPassword().isEmpty()) {
        	System.out.println("got empty");
            return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);
        	
        }
 
        String name = login.getUsername();
        String password = login.getPassword();
        
        Student student = stdService.studentLogin(login);
        // If the username and password are not valid -> Thrown an invalid credentials exception!
        if(student == null)
            return new ResponseEntity<String>("Invalid credentials. Please check the username and password.", HttpStatus.UNAUTHORIZED);
        else {
            // Creating JWT using the user credentials.
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("usr", login.getUsername());
            claims.put("sub", "Authentication token");
            claims.put("iss", Iconstants.ISSUER);
            claims.put("rol", "ROLE_STUDENT");
            claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
 
            jwttoken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
            System.out.println("Returning the following token to the user= "+ jwttoken);
        }
 
        return new ResponseEntity<String>(jwttoken + "," + Integer.toString(student.getId()) + "," + student.getFull_name(), HttpStatus.OK);
	}
}