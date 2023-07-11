package com.stg.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.user.entity.Roles;
import com.stg.user.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("role")
public class RoleController {
	
	@Autowired
	private  RoleService roleService;
	
	@PostMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Roles> createUser(@RequestBody Roles role) {
		return new ResponseEntity<Roles>(roleService.createRole(role),
				HttpStatus.CREATED);
	}

}
