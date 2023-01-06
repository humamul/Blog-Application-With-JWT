package com.humam.service;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.UserDto;
import com.humam.model.Role;
import com.humam.model.User;
import com.humam.repository.RolesRepo;
import com.humam.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RolesRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getCurrentUser()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
	
		String username = userDetails.getUsername();
		Optional<User> optUser=userRepo.findByUsername(username);
		
		return optUser.get();
	}



	@Override
	public UserDto updateUser(UserDto user) throws ResourceNotFoundException{

		User userGot=getCurrentUser();

		if(user.getAbout()!=null) userGot.setAbout(user.getAbout());
		if(user.getEmail()!=null) userGot.setUsername(user.getEmail());
		if(user.getName()!=null) userGot.setName(user.getName());
		if(user.getPassword()!=null) userGot.setPassword(passwordEncoder.encode(user.getPassword()));

		 User savedUser=userRepo.save(userGot);
		 return this.userToUserDto(savedUser);


	}

	@Override
	public UserDto getUserById(Integer userId) {

		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		return this.userToUserDto(optional.get());

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users=userRepo.findAll();

		List<UserDto>  list= users.stream().map(user-> this.userToUserDto(user)).collect(Collectors.toList());
		return list;
	}

	@Override
	public UserDto deleteUser(Integer userId) {
		

		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		UserDto userDto=this.userToUserDto(optional.get());
		userRepo.delete(optional.get());
		return userDto;

	}
	public User userDtotoUser(UserDto userDto)
	{
		User user=new User();
		user.setAbout(userDto.getAbout());
		user.setUsername(userDto.getEmail());
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;

	}
	public UserDto userToUserDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getUsername());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setId(user.getId());
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
	    User user=userDtotoUser(userDto);
	    
	    Optional<User> optional=userRepo.findByUsername(user.getUsername());
	    if(optional.isPresent())
	    {
	    	throw new ResourceNotFoundException("User", "Id", optional.get().getId());
	    }
	    
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    
	   
	    Role role=roleRepo.findById(2).get();
	    
	    user.getRoles().add(role);
	    
	    User newUser=userRepo.save(user);
	   
	     return userToUserDto(user);
	    
	}
	@Override
	public UserDto registerNewAdmin(UserDto userDto) {
		
	    User user=userDtotoUser(userDto);
	    
	    Optional<User> optional=userRepo.findByUsername(user.getUsername());
	    if(optional.isPresent())
	    {
	    	throw new ResourceNotFoundException("User", "Id", optional.get().getId());
	    }
	    
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    
	   
	    Role role=roleRepo.findById(1).get();
	    
	    user.getRoles().add(role);
	    
	    User newUser=userRepo.save(user);
	    
	   
	     return userToUserDto(user);
	    
	}

	@Override
	public Set<Role> getRolesOfUserById(Integer userId) {
		
		    Optional<User> optional=userRepo.findById(userId);
		    if(!optional.isPresent())
		    {
		    	throw new ResourceNotFoundException("User", "Id", userId);
		    }
		    
		   return optional.get().getRoles();
	}


}
