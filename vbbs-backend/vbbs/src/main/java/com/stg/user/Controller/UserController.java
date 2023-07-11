package com.stg.user.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.user.entity.Constants;
import com.stg.user.entity.User;
import com.stg.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user),
				HttpStatus.CREATED);
	}
	
	@GetMapping(value = "getalluser")
	public List<User> getAll(){
		return userService.getAllUsers();
	}
	
	@GetMapping(value = "login")
	public User login(@RequestParam String emailId,@RequestParam String password) {
		
		return userService.login(emailId, password);
	}
	
	@PostMapping(value = "authenticate")
    public Map<String, String> generateToken(@RequestBody User user) {
		
           long timestamp = System.currentTimeMillis();
           String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                        .setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                        .claim("emailId", user.getEmailId())
        				.claim("password", user.getPassword()).compact();
           Map<String, String> map = new HashMap<>();

           map.put("JWT", token);
           
          System.out.println(map);
           return map;  

    }

	
}
