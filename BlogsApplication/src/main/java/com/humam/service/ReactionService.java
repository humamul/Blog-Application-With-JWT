package com.humam.service;

import java.util.List;

import com.humam.io.ReactionDto;
import com.humam.model.Reaction;

public interface ReactionService {
	
	Reaction addReaction(Integer postId,ReactionDto reactionDto);
	
	Reaction deleteReaction(Integer reactionId);
	
	List<Reaction> getByPostId(Integer postId);
	
	List<Reaction> getByUserId(Integer userId);

}
