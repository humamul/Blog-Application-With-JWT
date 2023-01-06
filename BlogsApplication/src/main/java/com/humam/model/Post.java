package com.humam.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@CreatedDate
	private LocalDate date_added;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
    private  Category category;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private User user;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Comment> comments=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
	@JsonIgnore
	private List<Reaction> reactions=new ArrayList<>();


}
