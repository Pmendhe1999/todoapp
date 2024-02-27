package com.demo.todo.app.todoapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Category;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.CategoryRepository;
import com.demo.todo.app.todoapp.service.CategoryService;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public Page<Category> getAllCategory(@Valid RequestParamDto paramDto, Category category) {
		LOGGER.info("Inside the getAllCategory method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());

			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);

			Page<Category> listOfCategory = categoryRepository.findAll(serchCriteriaForCategory(category), pageable);

			return listOfCategory;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all category data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in category service method");
		}
	}

	private Specification<Category> serchCriteriaForCategory(Category category) {

		Specification<Category> specification = Specification.where(null);

		if (category.getCategoryName() != null) {
			specification = specification.and(containsCategoryName(category.getCategoryName()));

		}
		return specification;
	}

	private Specification<Category> containsCategoryName(String categoryName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_CATEGORY_NAME)),
				"%" + categoryName.toUpperCase() + "%");
	}

	@Override
	public Optional<Category> getCategoryById(Integer categoryId) {
		LOGGER.info("Inside the getCategoryById method in Service");

		try {
			return Optional.ofNullable(categoryRepository.findById(categoryId))
					.orElseThrow(() -> new GlobalServiceException("user not found for id: " + categoryId));
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user category by categoryId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getCategoryById method: " + e.getMessage());
		}

	}

	@Override
	public void deletecategoryById(Integer categoryId) {
		LOGGER.info("Inside the deletecategoryById method in Service");
		try {
			if (categoryRepository.existsById(categoryId)) {
				categoryRepository.deleteById(categoryId);
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while deleting user by category in Service", e.getMessage());
			throw new GlobalServiceException("Exception in deletecategoryById method: " + e.getMessage());
		}
	}

	@Override
	public List<Category> saveAndUpdateCategory(List<Category> category) {
		LOGGER.info("Inside the saveAndUpdateCategory method in Service");

		try {
			return categoryRepository.saveAllAndFlush(category);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate category  in Service", e.getMessage());
			throw new GlobalServiceException("Exception in user saveAndUpdateCategory method: " + e.getMessage());
		}

	}

}
