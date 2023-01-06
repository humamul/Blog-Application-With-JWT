package com.humam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.ReactionDto;
import com.humam.model.Post;
import com.humam.model.Reaction;
import com.humam.model.User;
import com.humam.repository.PostRepo;
import com.humam.repository.ReactionRepo;
import com.humam.repository.UserRepo;

@Service
public class ReactionServiceImpl implements ReactionService{
	
	@Autowired
	private ReactionRepo reactionRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	public User getCurrentUser()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
	
		String username = userDetails.getUsername();
		Optional<User> optUser=userRepo.findByUsername(username);
		
		return optUser.get();
	}

	@Override
	public Reaction addReaction(Integer postId, ReactionDto reactionDto) {
		
        User user=getCurrentUser();
        
        Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		
		Post post=optional.get();
		if(post.getUser()!=user)
		{
			throw new ResourceNotFoundException("User", "Id", user.getId());
		}
		
		Reaction reaction=new Reaction();
		reaction.setUser(user);
        reaction.setPost(post);
		reaction.setEmoji(reactionDto.getEmojiString());
		
		Reaction newReaction=  reactionRepo.save(reaction);
		
		postRepo.save(post);
		
		return newReaction;
		
	}

	@Override
	public Reaction deleteReaction(Integer reactionId) {
		
		
		User user=getCurrentUser();
		
		Optional<Reaction> optional=reactionRepo.findById(reactionId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Reaction", "Id", reactionId);
		}

		Reaction reaction=optional.get();
		Post post=reaction.getPost();
		
	    post.getReactions().remove(reaction);
	   
		reactionRepo.delete(reaction);
	
		return reaction;
	}

	@Override
	public List<Reaction> getByPostId(Integer postId) {
		
		    User user=getCurrentUser();
	        
	        Optional<Post> optional=postRepo.findById(postId);
			if(!optional.isPresent())
			{
				throw new ResourceNotFoundException("Post", "Id", postId);
			}

			
			Post post=optional.get();
			
			return post.getReactions();
	}

	@Override
	public List<Reaction> getByUserId(Integer userId) {
		
		    Optional<User> optional=userRepo.findById(userId);
			if(!optional.isPresent())
			{
				throw new ResourceNotFoundException("User", "Id", userId);
			}
			
			List<Reaction> list=reactionRepo.findByUserId(userId);
			return list;
	}

}
