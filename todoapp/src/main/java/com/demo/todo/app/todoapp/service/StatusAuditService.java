package com.demo.todo.app.todoapp.service;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.StatusAudit;

import jakarta.validation.Valid;

public interface StatusAuditService {

	Page<StatusAudit> getAllStatusAudit(@Valid RequestParamDto paramDto, StatusAudit getStatusAudit);

}
