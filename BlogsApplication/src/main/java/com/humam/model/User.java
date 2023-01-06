package com.humam.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,length=100,unique = false)
	private String name;
	@Column(unique = true)
	private String username;
	
	private String password;
	private String about;
	
	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
	inverseJoinColumns  = @JoinColumn(name="role",referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts=new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Comment> comments=new HashSet<>();
	
	public User(String username,String password) {
		this.username = username;
		this.password = password;
	}
}
