package com.humam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humam.io.ReactionDto;
import com.humam.model.Reaction;
import com.humam.service.ReactionService;

@RestController
@RequestMapping("/api/reaction")
public class ReactionController {
	
	@Autowired
	private ReactionService reactionService;

	
	@PostMapping("/create/{postId}")
	public ResponseEntity<Reaction> createReaction(@Valid @PathVariable Integer postId,@RequestBody ReactionDto reactionDto)
	{
		Reaction reaction=reactionService.addReaction(postId, reactionDto);
		return new ResponseEntity<Reaction>(reaction,HttpStatus.CREATED);
	}

	
	@DeleteMapping("/delete/{reactionId}")
	public ResponseEntity<Reaction> deleteReactionById(@PathVariable Integer reactionId)
	{
		Reaction reaction=reactionService.deleteReaction(reactionId);
		return new ResponseEntity<Reaction>(reaction,HttpStatus.OK);
	}
	
	@GetMapping("/getBypost/{postId}")
	public ResponseEntity<List<Reaction>> getByPostId(@Valid @PathVariable Integer postId)
	{
		List<Reaction> list=reactionService.getByPostId(postId);
		return new ResponseEntity<List<Reaction>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getByUser/{userId}")
	public ResponseEntity<List<Reaction>> getByUserId(@Valid @PathVariable Integer userId)
	{
		List<Reaction> list=reactionService.getByUserId(userId);
		return new ResponseEntity<List<Reaction>>(list,HttpStatus.OK);
	}

}
