package com.humam.service;

import java.util.List;

import com.humam.io.CommentDto;
import com.humam.model.Comment;

public interface CommentService {

	Comment addComment(CommentDto commentDto,Integer postId);

	Comment deleteComment(Integer commentId);

	Comment updateComment(CommentDto commentDto,Integer commentId);
	
	List<Comment> findByPostId(Integer postId);


}
