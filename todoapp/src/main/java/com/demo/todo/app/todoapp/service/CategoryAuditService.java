package com.demo.todo.app.todoapp.service;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.CategoryAudit;

import jakarta.validation.Valid;

public interface CategoryAuditService {

	Page<CategoryAudit> getAllCategoryAudit(@Valid RequestParamDto paramDto, CategoryAudit getCategoryAudit);

}
