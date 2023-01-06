package com.humam.io;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	
	@Column(length=100)
	@NotNull
	private String name;
	@NotNull
	private String description;


}
