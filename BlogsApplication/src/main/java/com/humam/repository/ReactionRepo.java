package com.humam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.humam.model.Reaction;

@Service
public interface ReactionRepo extends JpaRepository<Reaction, Integer>{
	
	
	List<Reaction> findByUserId(Integer userId);

}
