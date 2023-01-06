package com.humam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer Id;
	@Column(length=100)
	@NotNull
	private String CategoryName;
	@NotNull
	private String CategoryDescription;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();


}
