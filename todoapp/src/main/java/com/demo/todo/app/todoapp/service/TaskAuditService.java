package com.demo.todo.app.todoapp.service;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.TaskAudit;

import jakarta.validation.Valid;

public interface TaskAuditService {

	Page<TaskAudit> getAllTaskAudit(@Valid RequestParamDto paramDto, TaskAudit getTaskAudit);

}
