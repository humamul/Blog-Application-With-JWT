package com.humam.io;

import javax.persistence.Column;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

		
		private Integer id;
		@Column(nullable = false,length=100)
		private String name;
		@Email(message="invalid email id")
		private String email;
		@JsonProperty(access = Access.WRITE_ONLY)
		private String password;
		private String about;



}
