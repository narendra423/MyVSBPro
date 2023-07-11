package com.stg.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.user.entity.Permissions;
import com.stg.user.service.PermissionService;


@RestController
@RequestMapping("permissions")
public class PermissionsController {
	
	@Autowired
	private PermissionService permissionService;
	
	@PostMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Permissions> createpermission(@RequestBody Permissions permissions){
		return new ResponseEntity<Permissions>(permissionService.createPermission(permissions),HttpStatus.CREATED);
	}
}
