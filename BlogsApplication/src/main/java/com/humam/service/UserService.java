package com.humam.service;

import java.util.List;
import java.util.Set;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.UserDto;
import com.humam.model.Role;

public interface UserService {
	
	
    UserDto registerNewUser(UserDto userDto);
    UserDto registerNewAdmin(UserDto userDto);
	
	UserDto updateUser(UserDto user) throws ResourceNotFoundException ;
	UserDto getUserById(Integer userId) throws ResourceNotFoundException ;
	List<UserDto> getAllUsers();
	UserDto deleteUser(Integer userId) throws ResourceNotFoundException ;
	Set<Role> getRolesOfUserById(Integer userId);


}
