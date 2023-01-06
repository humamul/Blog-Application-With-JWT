package com.humam.payloads.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.humam.model.User;

public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	  private String username;


	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	  
	  
		public UserDetailsImpl() {
			// TODO Auto-generated constructor stub
		}
		
		public UserDetailsImpl(Integer id,String username,String password,Collection<? extends GrantedAuthority> authorities) {
			this.id = id;
			this.authorities = authorities;
			this.password = password;
			this.username = username;
		}
		
		 public static UserDetailsImpl build(User user) {
			    List<GrantedAuthority> authorities = user.getRoles().stream()
			    		.map(i -> new SimpleGrantedAuthority(i.getRole()))
			    		.collect(Collectors.toList());

			    return new UserDetailsImpl(
			        user.getId(), 
			        user.getUsername(), 
			        user.getPassword(), 
			        authorities);
			  }

		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return authorities;
		}
		
		public Integer getId() {
			return this.id;
		}
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}
	
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return username;
		}
	
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
	
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
	
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
	
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

}
