package com.demo.todo.app.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.app.todoapp.constants.MasterConstants;
import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.CategoryAuditDto;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.entity.CategoryAudit;
import com.demo.todo.app.todoapp.entity.UserAudit;
import com.demo.todo.app.todoapp.service.CategoryAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class CategoryAuditController {

	@Autowired
	private CategoryAuditService categoryAuditService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuditController.class);

	@GetMapping(path = "/categoryAudit")
	public ResponseEntity<Object> getAllCategoryAudit(@Valid RequestParamDto paramDto,
			@Valid CategoryAuditDto categoryAuditDto) {
		LOGGER.info("Controller method to fetch all CategoryAudit audit");

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			CategoryAudit getCategoryAudit = objectMapper.readValue(objectMapper.writeValueAsString(categoryAuditDto),
					CategoryAudit.class);

			Page<CategoryAudit> pageOfCategoryAudit = categoryAuditService.getAllCategoryAudit(paramDto,getCategoryAudit);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfCategoryAudit.getPageable().getPageNumber());
			dto.setPageSize(pageOfCategoryAudit.getPageable().getPageSize());
			dto.setTotalPages(pageOfCategoryAudit.getTotalElements());
			dto.setContent(pageOfCategoryAudit.getContent());
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_CATEGORY_AUDIT, "",
					true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all category audit data in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_CATEGORY, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
