package com.demo.todo.app.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.app.todoapp.constants.MasterConstants;
import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.CategoryDto;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.entity.Category;
import com.demo.todo.app.todoapp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@GetMapping(path = "/category")
	public ResponseEntity<Object> getAllCategory(@Valid RequestParamDto paramDto, @Valid CategoryDto categoryDto) {
		LOGGER.info("Controller method to fetch all category");

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			Category category = objectMapper.readValue(objectMapper.writeValueAsString(categoryDto), Category.class);

			Page<Category> pageOfUsers = categoryService.getAllCategory(paramDto, category);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfUsers.getPageable().getPageNumber());
			dto.setPageSize(pageOfUsers.getPageable().getPageSize());
			dto.setTotalPages(pageOfUsers.getTotalElements());
			dto.setContent(pageOfUsers.getContent());

			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_CATEGORY, "",
					true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all category in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_CATEGORY, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/category/{categoryId}")
	public ResponseEntity<Object> getCategoryById(@PathVariable Integer categoryId) {
		LOGGER.info("Controller method to fetch category by Id");

		try {
			Optional<Category> categoryById = categoryService.getCategoryById(categoryId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_FETCH_CATEGORY + categoryId + MessageConstants.COMMON_SUCCESS_FETCH, "",
					true, categoryById);
			return new ResponseEntity<Object>(responseclass, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by  userId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_CATEGORY + categoryId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(path = "/category/{categoryId}")
	public ResponseEntity<Object> deletecategoryById(@PathVariable Integer categoryId) {
		LOGGER.info("Controller method to delete category by Id");

		try {
			categoryService.deletecategoryById(categoryId);

			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_CATEGROY + categoryId + MessageConstants.COMMON_HAS_DELETED, "", true, "");
			return new ResponseEntity<>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting category by categoryId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_DELETE_CATEGORY + categoryId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/category")
	public ResponseEntity<Object> saveAndUpdateCategory(@RequestBody List<Category> category) {
		LOGGER.info("Controller method to save or update category");
		try {
			List<Category> savedCategory = categoryService.saveAndUpdateCategory(category);
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_SAVE_OR_UPDATE_CATEGEORY,
					"", true, savedCategory);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate category in category ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_SAVE_OR_UPDATE_CATEGORY, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
