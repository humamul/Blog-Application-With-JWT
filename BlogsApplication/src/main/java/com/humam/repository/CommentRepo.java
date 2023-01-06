package com.humam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humam.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
 List<Comment> findByPostId(Integer postId);

}
