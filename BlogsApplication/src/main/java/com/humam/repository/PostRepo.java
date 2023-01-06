package com.humam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humam.model.Category;
import com.humam.model.Post;
import com.humam.model.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

//	@Query("select a from Post a where a.category:?")
	List<Post> findByCategory(Category category);

//	@Query("select a from Post a where a.user:?")
	List<Post> findByUser(User user);

}
