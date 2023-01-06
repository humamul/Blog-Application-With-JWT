package com.humam.payloads.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humam.model.User;
import com.humam.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo ur;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found: "+username));
		return UserDetailsImpl.build(user);
	}

}
