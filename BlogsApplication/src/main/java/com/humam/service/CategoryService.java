package com.humam.service;

import java.util.List;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.CategoryDto;
import com.humam.model.Category;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);

	Category updateCategory(CategoryDto categoryDto,Integer categoryId) throws ResourceNotFoundException;

	Category deleteCategory(Integer categoryId ) throws ResourceNotFoundException;

	Category getCategoryById(Integer categoryId ) throws ResourceNotFoundException;

	List<Category>  getAllCategory();


}
