package com.humam.io;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.humam.model.Emoji;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReactionDto implements Serializable{
	
	@Enumerated(EnumType.STRING)
	private Emoji emojiString;
	

}
