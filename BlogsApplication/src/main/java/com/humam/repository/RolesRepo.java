package com.humam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humam.model.Role;
import com.humam.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Role, Integer>{
	public Optional<Role> findByRole(String role);
}
