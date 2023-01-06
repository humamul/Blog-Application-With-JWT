package com.humam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.humam.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username)throws UsernameNotFoundException;

	public boolean existsByUsername(String username);
}
