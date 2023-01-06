package com.humam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humam.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
