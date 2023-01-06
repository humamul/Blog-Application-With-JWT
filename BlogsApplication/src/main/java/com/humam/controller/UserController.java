package com.humam.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.UserDto;
import com.humam.model.Role;
import com.humam.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

    
	@PutMapping("/update")
//	@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.updateUser(userDto);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@Valid @PathVariable Integer userId) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.getUserById(userId);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> list=userService.getAllUsers();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<UserDto> deleteUser(@Valid @PathVariable Integer userId) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.deleteUser(userId);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getRolesOfUser/{userId}")
	public ResponseEntity<Set<Role>> getRolesOfUser(@Valid @PathVariable Integer userId)
	{
		Set<Role> list=userService.getRolesOfUserById(userId);
		return new ResponseEntity<Set<Role>>(list,HttpStatus.OK);
	}

}
