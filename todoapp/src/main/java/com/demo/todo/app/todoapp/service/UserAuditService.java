package com.demo.todo.app.todoapp.service;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.UserAuditDto;
import com.demo.todo.app.todoapp.entity.User;
import com.demo.todo.app.todoapp.entity.UserAudit;

import jakarta.validation.Valid;

public interface UserAuditService {

	Page<UserAudit> getAllUserAudit(@Valid RequestParamDto paramDto, UserAudit getUserAudDto);

}
