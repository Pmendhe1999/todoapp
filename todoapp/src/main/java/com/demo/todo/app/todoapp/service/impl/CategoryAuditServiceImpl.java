package com.demo.todo.app.todoapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.todo.app.todoapp.controller.UserAuditController;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.CategoryAudit;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.CategoryAuditRepository;
import com.demo.todo.app.todoapp.repository.CategoryRepository;
import com.demo.todo.app.todoapp.service.CategoryAuditService;

import jakarta.validation.Valid;

@Service
public class CategoryAuditServiceImpl implements CategoryAuditService {

	@Autowired
	private CategoryAuditRepository categoryAuditRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryAuditServiceImpl.class);

	@Override
	public Page<CategoryAudit> getAllCategoryAudit(@Valid RequestParamDto paramDto, CategoryAudit getCategoryAudit) {
		LOGGER.info("Inside the getAllCategoryAudit method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<CategoryAudit> listOfSpecificationUserAudit = categoryAuditRepository.findAll(pageable);
			return listOfSpecificationUserAudit;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all category audit data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in category audit method");
		}
	}

}
