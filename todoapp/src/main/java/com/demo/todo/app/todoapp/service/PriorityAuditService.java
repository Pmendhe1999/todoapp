package com.demo.todo.app.todoapp.service;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.PrioriotyAudit;

import jakarta.validation.Valid;

public interface PriorityAuditService {

	Page<PrioriotyAudit> getAllPrioriotyAudit(@Valid RequestParamDto paramDto, PrioriotyAudit prioriotyAudit);

}
