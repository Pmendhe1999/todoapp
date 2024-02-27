package com.demo.todo.app.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Category;

import jakarta.validation.Valid;

public interface CategoryService {

	Page<Category> getAllCategory(@Valid RequestParamDto paramDto, Category category);

	Optional<Category> getCategoryById(Integer categoryId);

	void deletecategoryById(Integer categoryId);

	List<Category> saveAndUpdateCategory(List<Category> categories);

}
