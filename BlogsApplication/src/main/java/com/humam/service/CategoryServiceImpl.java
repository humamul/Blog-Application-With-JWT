package com.humam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humam.exception.ResourceNotFoundException;
import com.humam.io.CategoryDto;
import com.humam.model.Category;
import com.humam.repository.CategoryRepo;
//import com.humam.repository

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category createCategory(CategoryDto categoryDto) {

		Category category=new Category();
		category.setCategoryDescription(categoryDto.getDescription());
		category.setCategoryName(categoryDto.getName());
		
		return categoryRepo.save(category);

	}

	@Override
	public Category updateCategory(CategoryDto categoryDto, Integer categoryId) throws ResourceNotFoundException
	{

		Optional<Category> optional=categoryRepo.findById(categoryId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		 Category categoryGot=optional.get();
		 categoryGot.setCategoryName(categoryDto.getName());
		 categoryGot.setCategoryDescription(categoryDto.getDescription());

		 return categoryRepo.save(categoryGot);
	}

	@Override
	public Category deleteCategory(Integer categoryId) throws ResourceNotFoundException{

		Optional<Category> optional=categoryRepo.findById(categoryId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		Category category=optional.get();

		categoryRepo.delete(category);
		return category;

	}

	@Override
	public Category getCategoryById(Integer categoryId) throws ResourceNotFoundException{

		Optional<Category> optional=categoryRepo.findById(categoryId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		Category category=optional.get();
		return category;

	}

	@Override
	public List<Category> getAllCategory() {

		List<Category> list= categoryRepo.findAll();

		return list;
	}

}
